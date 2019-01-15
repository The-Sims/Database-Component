package SimsRESTServer.handlers;


import SimsRESTServer.response.IncidentJson;
import SimsRESTServer.response.Reply;
import models.Incident;

public interface IIncidentHandler {
    Reply getIncidents();

    Reply getIncident(int id);

    Reply saveIncident(IncidentJson incident);

    Reply deleteIncident(int indicentId);

    Reply concludeIncident(int indicentId);

    Reply confirmIncident(int indicentId);

    Reply confirmTipIncident(int tipId);

    Reply deleteTipIncident(int tipId);

}
