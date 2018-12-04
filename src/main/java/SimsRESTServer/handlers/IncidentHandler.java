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

public class IncidentHandler implements IIncidentHandler{
    private IncidentRepository repo;
    private TipRepository tipRepo;
    private Gson gson;

    public IncidentHandler(IncidentRepository repo){
        this.repo = repo;
        this.gson = new Gson();
    }

    @Override
    public Reply getIncidents(){
        try {
            addIncident();
            List<Incident> incidents = repo.findAll();
            List<IncidentJson> incidentResponse = new ArrayList<IncidentJson>();
            for (Incident incident : incidents) {
                incidentResponse.add(new IncidentJson(incident.getId(), incident.getCategory(), incident.getPlace(), incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(), incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed(), incident.getOrigin()));
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
            IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategory(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate(), incident.getModifyDate(), incident.getTips(), incident.isConfirmed(), incident.getOrigin());
            String json = gson.toJson(incidentJson);
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("Incident doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    @Override
    public Reply saveIncident(Incident incident) {
        Incident saved = repo.save(incident);
        if (saved.getId() == incident.getId()) {
            return new Reply(Status.OK, gson.toJson(saved));
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
                return new Reply(Status.OK, gson.toJson(saved));
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
                return new Reply(Status.OK, gson.toJson(saved));
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
                return new Reply(Status.OK, gson.toJson(saved));
            }
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        ErrorJson errorJson = new ErrorJson("Incident doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    private void addIncident(){
        ArrayList<IncidentDescription> incidentDescriptions = new ArrayList<>();
        incidentDescriptions.add(new IncidentDescription("Gas tank is geexplodeerd"));
        incidentDescriptions.add(new IncidentDescription("Stoute student"));

        ArrayList<ReinforceInfo> reinforceInfos = new ArrayList<>();
        reinforceInfos.add(new ReinforceInfo("Run like hell"));
        reinforceInfos.add(new ReinforceInfo("Het was Bas"));


        Incident incident = new Incident(incidentDescriptions, Origin.TWITTER, new Category("Explosie"), "Rachelsmolen 1, Eindhoven", false, reinforceInfos);

        repo.save(incident);
    }

}
