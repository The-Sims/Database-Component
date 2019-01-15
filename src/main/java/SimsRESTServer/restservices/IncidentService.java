package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IIncidentHandler;
import SimsRESTServer.response.IncidentJson;
import SimsRESTServer.response.Reply;
import com.google.gson.Gson;
import models.Incident;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/incident")
public class IncidentService {
    private static IIncidentHandler handler;

    public static void setHandler(IIncidentHandler handler) {
        IncidentService.handler = handler;
    }

    private Gson gson = new Gson();

    @GET
    @Path("/all")
    public Response getIncidents() {
        Reply reply = handler.getIncidents();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getIncident(@PathParam("id") int incidentId) {
        Reply reply = handler.getIncident(incidentId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/conclude/{id}")
    public Response concludeIncident(@PathParam("id") int incidentId) {
        Reply reply = handler.concludeIncident(incidentId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveIncident(String data) {
        IncidentJson incident = gson.fromJson(data, IncidentJson.class);
        Reply reply = handler.saveIncident(incident);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/delete/{id}")
    public Response deleteIncident(@PathParam("id") int incidentId) {
        Reply reply = handler.deleteIncident(incidentId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/confirm/{id}")
    public Response confirmIncident(@PathParam("id") int incidentId) {
        Reply reply = handler.confirmIncident(incidentId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/confirmTip/{id}")
    public Response confirmTipIncident(@PathParam("id") int tipId) {
        Reply reply = handler.confirmTipIncident(tipId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/deleteTip/{id}")
    public Response deleteTipIncident(@PathParam("id") int tipId) {
        Reply reply = handler.deleteTipIncident(tipId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
