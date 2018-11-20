package SimsRESTServer.handlers;

import SimsDal.repository.IncidentRepository;
import SimsRESTServer.response.ErrorJson;
import SimsRESTServer.response.IncidentJson;
import SimsRESTServer.response.Reply;
import SimsRESTServer.response.Status;
import com.google.gson.Gson;
import logging.Logger;
import models.Incident;

import java.util.ArrayList;
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
            List<Incident> incidents = repo.findAll();
            List<IncidentJson> incidentResponse = new ArrayList<IncidentJson>();
            for (Incident incident : incidents) {
                incidentResponse.add(new IncidentJson(incident.getId(), incident.getCategoryString(), incident.getPlace(), incident.getReinforceInfoStrings(),incident.getDescriptionStrings(), incident.isLive(), incident.getCreateDate().toString(), incident.getModifyDate().toString()));
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
            IncidentJson incidentJson = new IncidentJson(incident.getId(),incident.getCategoryString(),incident.getPlace(),incident.getReinforceInfoStrings(),incident.getDescriptionStrings(), incident.isLive(),incident.getCreateDate().toString(), incident.getModifyDate().toString());
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

}
