package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IPhasedplanHandler;
import SimsRESTServer.response.Reply;
import models.Phasedplan;

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
    public Response addPhasedplan(Phasedplan phasedplan) {
        Reply reply = handler.savePhasedplan(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @PUT
    @Path("/edit")
    public Response editPhasedplan(Phasedplan phasedplan) {
        Reply reply = handler.savePhasedplan(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}/task/all")
    public Response getAllPhasedplanTasks(@PathParam("id") int phasedplanId) {
        Reply reply = handler.getTasks(phasedplanId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/task/add")
    public Response addPhasedplanTask(Phasedplan phasedplan) {
        Reply reply = handler.saveTask(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @PUT
    @Path("/task/edit")
    public Response editPhasedplanTask(Phasedplan phasedplan) {
        Reply reply = handler.saveTask(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
