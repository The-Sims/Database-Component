package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IIncidentHandler;
import SimsRESTServer.response.Reply;
import com.google.gson.Gson;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public Response getRecipes() {
        Reply reply = handler.getIncidents();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getRecipe(@PathParam("id") int incidentId) {
        Reply reply = handler.getIncident(incidentId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
