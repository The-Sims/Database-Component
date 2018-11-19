package SimsRESTServer.handlers;

import com.google.gson.Gson;
import dbal.repository.IncidentRepository;
import logging.Logger;
import models.Incident;
import restserver.response.*;

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
            List<Incident> recipes = repo.findAll();
            List<IncidentJson> incidentResponse = new ArrayList<IncidentJson>();
            for (Incident incident : recipes) {
                incidentResponse.add(new IncidentJson(incident.getId(), incident.getCategoryString(), incident.getPlace(), incident.getReinforceInfoStrings(), incident.isLive()));
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
            String json = gson.toJson(incident);
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("Recipe doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

}
