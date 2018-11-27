package SimsRESTServer.handlers;

import SimsDal.repository.IncidentRepository;
import SimsRESTServer.response.ErrorJson;
import SimsRESTServer.response.IncidentJson;
import SimsRESTServer.response.Reply;
import SimsRESTServer.response.Status;
import com.google.gson.Gson;
import logging.Logger;
import models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncidentHandler implements IIncidentHandler{
    private IncidentRepository repo;
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
                incidentResponse.add(new IncidentJson(incident.getId(), incident.getCategoryString(), incident.getPlace(), incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(), incident.getCreateDate().toString(), incident.getModifyDate().toString()));
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
            IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategoryString(),incident.getPlace(),incident.getReinformentJson(),incident.getDescriptionJson(), incident.isLive(),incident.getCreateDate().toString(), incident.getModifyDate().toString());
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
