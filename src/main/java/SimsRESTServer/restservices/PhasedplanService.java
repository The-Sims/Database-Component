package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IPhasedplanHandler;
import SimsRESTServer.response.Reply;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Phasedplan;
import models.Task;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/phasedplan")
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
    @Path("/save")
    @Consumes("application/json")
    public Response addPhasedplan(String data) {
        Phasedplan phasedplan = Phasedplan.fromJson(data);

        Reply reply = handler.savePhasedplan(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @PUT
    @Path("/edit")
    @Consumes("application/json")
    public Response editPhasedplan(String data) {
        Phasedplan phasedplan = Phasedplan.fromJson(data);

        Reply reply = handler.savePhasedplan(phasedplan);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
