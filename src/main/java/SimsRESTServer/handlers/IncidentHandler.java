package SimsRESTServer.handlers;

import SimsDal.repository.IncidentRepository;
import SimsDal.repository.TipRepository;
import SimsRESTServer.response.ErrorJson;
import SimsRESTServer.response.IncidentJson;
import SimsRESTServer.response.Reply;
import SimsRESTServer.response.Status;
import com.google.gson.Gson;
import logging.Logger;
import models.*;
import java.util.ArrayList;
import java.util.List;

import static models.Origin.TWITTER;

public class IncidentHandler implements IIncidentHandler{
    private IncidentRepository repo;
    private TipRepository tipRepo;
    private Gson gson;

    public IncidentHandler(IncidentRepository repo, TipRepository tipRepo){
        this.repo = repo;
        this.tipRepo = tipRepo;
        this.gson = new Gson();
        //addIncident();
    }

    @Override
    public Reply getIncidents(){
        try {
            List<Incident> incidents = repo.findAll();
            List<IncidentJson> incidentResponse = new ArrayList<IncidentJson>();
            for (Incident incident : incidents) {
                incidentResponse.add(new IncidentJson(incident.getId(), incident.getCategory(), incident.getPlace(), incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(), incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed()));
            }
            String json = gson.toJson(incidentResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply getIncident(int id){
        Incident incident = repo.findOne(id);
        if (incident != null) {
            IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategory(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed());
            String json = gson.toJson(incidentJson);
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("Incident doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    @Override
    public Reply saveIncident(Incident incident) {
        incident.updateLists();
        incident.emptyModifyDate();
        Incident saved = repo.save(incident);
        if (saved.getId() == incident.getId()) {
            IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategory(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed());
            String json = gson.toJson(incidentJson);
            return new Reply(Status.OK, gson.toJson(json));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteIncident(int indicentId) {
        try {
            repo.delete(indicentId);
            ErrorJson messageJson = new ErrorJson("Deleted");
            return new Reply(Status.OK, gson.toJson(messageJson));
        } catch (Exception e){
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply concludeIncident(int indicentId) {
        Incident incident = repo.findOne(indicentId);
        if (incident != null) {
            incident.setLive(false);

            Incident saved = repo.save(incident);
            if (saved.getId() == incident.getId()) {
                IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategory(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed());
                String json = gson.toJson(incidentJson);
                return new Reply(Status.OK, gson.toJson(json));
            }
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        ErrorJson errorJson = new ErrorJson("Incident doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    @Override
    public Reply confirmIncident(int indicentId) {
        Incident incident = repo.findOne(indicentId);
        if (incident != null) {
            incident.setConfirmed(true);

            Incident saved = repo.save(incident);
            if (saved.getId() == incident.getId()) {
                IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategory(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed());
                String json = gson.toJson(incidentJson);
                return new Reply(Status.OK, gson.toJson(json));
            }
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        ErrorJson errorJson = new ErrorJson("Incident doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    @Override
    public Reply confirmTipIncident(int tipId) {
        Tip tip = tipRepo.findOne(tipId);
        if (tip != null) {
            tip.setConfirmed(true);

            Tip saved = tipRepo.save(tip);
            if (saved.getId() == tip.getId()) {
                Incident incident = tip.getIncident();
                IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategory(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed());
                String json = gson.toJson(incidentJson);
                return new Reply(Status.OK, gson.toJson(json));
            }
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        ErrorJson errorJson = new ErrorJson("Incident doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteTipIncident(int tipId) {
        try {
            tipRepo.delete(tipId);
            ErrorJson messageJson = new ErrorJson("Deleted");
            return new Reply(Status.OK, gson.toJson(messageJson));
        } catch (Exception e){
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    private void addIncident(){
        Category category = new Category("Bommelding");
        Category category1 = new Category("Brand");
        Category category2 = new Category("Schietpartij");
        Category category3 = new Category("Overval");

        ArrayList<IncidentDescription> incidentDescriptions = new ArrayList<>();
        incidentDescriptions.add(new IncidentDescription("Er is een verdacht pakketje gevonden in de aula van R1"));
        incidentDescriptions.add(new IncidentDescription("Het verdacht pakketje lijkt op een schooltas"));
        incidentDescriptions.add(new IncidentDescription("De tas is geel"));

        ArrayList<IncidentDescription> incidentDescriptions1 = new ArrayList<>();
        incidentDescriptions.add(new IncidentDescription("Een rode auto staat in brand"));
        incidentDescriptions.add(new IncidentDescription("De auto stond fout geparkeerd waarna tuig van de buurt de eigenaar van de auto een lesje wilde leren"));
        incidentDescriptions.add(new IncidentDescription("De brand kan een explosie veroorzaken"));

        ArrayList<IncidentDescription> incidentDescriptions2 = new ArrayList<>();
        incidentDescriptions.add(new IncidentDescription("3 jongeren zijn gesignaleerd met vuurwapens op de grote markt"));
        incidentDescriptions.add(new IncidentDescription("De verdachten dragen alle 3 een zwarte leren jas en een ski muts"));

        ArrayList<IncidentDescription> incidentDescriptions3 = new ArrayList<>();
        incidentDescriptions.add(new IncidentDescription("De Lidl op woenselsemarkt word overvallen door 2 mannen"));
        incidentDescriptions.add(new IncidentDescription("Er worden mensen gegijzeld"));

        ArrayList<ReinforceInfo> reinforceInfos = new ArrayList<>();
        reinforceInfos.add(new ReinforceInfo("Blijf uit de buurt van R1"));
        reinforceInfos.add(new ReinforceInfo("Laat spullen achter die nog in het gebouw liggen"));

        ArrayList<ReinforceInfo> reinforceInfos1 = new ArrayList<>();
        reinforceInfos.add(new ReinforceInfo("Houd ramen gesloten"));

        ArrayList<ReinforceInfo> reinforceInfos2 = new ArrayList<>();
        reinforceInfos.add(new ReinforceInfo("Blijf binnens huis"));
        reinforceInfos.add(new ReinforceInfo("Hou ramen en deuren gesloten"));

        ArrayList<ReinforceInfo> reinforceInfos3 = new ArrayList<>();
        reinforceInfos.add(new ReinforceInfo("Blijf weg bij de Lidl"));

        ArrayList<Tip> tips = new ArrayList<>();
        tips.add(new Tip(TWITTER,"@Geert","Ik had gezien dat het om een gele tas ging", "Eindhoven"));
        tips.add(new Tip(TWITTER,"@Dave","De tas ligt dicht bij de deur", "Eindhoven"));

        ArrayList<Tip> tips1 = new ArrayList<>();
        tips.add(new Tip(TWITTER,"@Geert","Een van de jongens had een shirt aan van een metal band", "Eindhoven"));
        tips.add(new Tip(TWITTER,"@Dave","Het gaat over een renault clio", "Eindhoven"));
        tips.add(new Tip(TWITTER,"@Kees","De auto stond er sinds gisteravond al", "Eindhoven"));

        ArrayList<Tip> tips2 = new ArrayList<>();
        tips.add(new Tip(TWITTER,"@Geert","De jongeren rijden op scooters", "Eindhoven"));
        tips.add(new Tip(TWITTER,"@Dave","Ik zag dat een van de jongens een grote tattoo had van een draak", "Eindhoven"));
        tips.add(new Tip(TWITTER,"@Kees","Ik hoorde een van de jongens de andere g noemen, waarschijnlijk is dit een afkorting voor zijn echte naam", "Eindhoven"));

        ArrayList<Tip> tips3 = new ArrayList<>();
        tips.add(new Tip(TWITTER,"@Geert","De mannen liepen met een groot mes naar binnen", "Eindhoven"));
        tips.add(new Tip(TWITTER,"@Dave","HELP ik zit verstopt, er zijn 3 mannen, geen 2. De 3e man was een jonge knul die hier werkte.", "Eindhoven"));

        Incident incident = new Incident(incidentDescriptions, category, "Rachelsmolen 1, Eindhoven", false, reinforceInfos,tips);
        Incident incident1 = new Incident(incidentDescriptions1, category1, "Generaal Bentinkslaan, Eindhoven", true, reinforceInfos1,tips1);
        Incident incident2 = new Incident(incidentDescriptions2, category2, "Grote markt, Eindhoven", true, reinforceInfos2,tips2);
        Incident incident3 = new Incident(incidentDescriptions3, category3, "Woenselsemarkt, Eindhoven", false, reinforceInfos3,tips3);

        repo.save(incident);
        repo.save(incident1);
        repo.save(incident2);
        repo.save(incident3);
    }

}
