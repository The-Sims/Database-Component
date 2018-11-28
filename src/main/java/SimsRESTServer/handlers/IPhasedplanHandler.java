package SimsRESTServer.handlers;

import SimsRESTServer.response.Reply;
import models.Phasedplan;
import models.Task;

public interface IPhasedplanHandler {
    Reply getPhasedplans();
    Reply getPhasedplan(int phasedplanId);
    Reply savePhasedplan(Phasedplan phasedplan);
}
