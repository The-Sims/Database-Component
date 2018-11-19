package SimsRESTServer.handlers;


import SimsRESTServer.response.Reply;

public interface IIncidentHandler {
    Reply getIncidents();

    Reply getIncident(int id);
}
