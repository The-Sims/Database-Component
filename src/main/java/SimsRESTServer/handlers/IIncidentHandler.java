package SimsRESTServer.handlers;


import SimsRESTServer.response.Reply;
import models.Incident;

public interface IIncidentHandler {
    Reply getIncidents();

    Reply getIncident(int id);

    Reply saveIncident(Incident incident);

    Reply deleteIncident(int indicentId);
}
