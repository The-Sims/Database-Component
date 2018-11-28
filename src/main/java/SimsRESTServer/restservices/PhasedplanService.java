package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IPhasedplanHandler;
import SimsRESTServer.response.Reply;
import models.Phasedplan;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/SIMS/phasedplan")
public class PhasedplanService {
    private static IPhasedplanHandler handler;

    public static void setHandler(IPhasedplanHandler handler) {
        PhasedplanService.handler = handler;
    }

    @GET
    @Path("/all")
    public Response getPhasedplans() {
        Reply reply = handler.getPhasedplans();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getPhasedplan(@PathParam("id") int phasedplanId) {
        Reply reply = handler.getPhasedplan(phasedplanId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addPhasedplan(String data) {
        Phasedplan phasedplan = GsonUtils.fromJson(data, Phasedplan.class);

        Reply reply = handler.savePhasedplan(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @PUT
    @Path("/edit")
    @Consumes("application/json")
    public Response editPhasedplan(String data) {
        Phasedplan phasedplan = GsonUtils.fromJson(data, Phasedplan.class);

        Reply reply = handler.savePhasedplan(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
