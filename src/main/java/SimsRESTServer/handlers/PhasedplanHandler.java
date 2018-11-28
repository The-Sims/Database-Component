package SimsRESTServer.handlers;

import SimsDal.repository.PhasedplanRepository;
import SimsRESTServer.response.*;
import com.google.gson.Gson;
import logging.Logger;
import models.Phasedplan;
import models.Task;

import java.util.ArrayList;
import java.util.List;

public class PhasedplanHandler implements IPhasedplanHandler {
    private PhasedplanRepository repository;
    private Gson gson;

    public PhasedplanHandler(PhasedplanRepository repository) {
        this.repository = repository;
        this.gson = new Gson();
    }

    @Override
    public Reply getPhasedplans() {
        try {
            addPhasedPlans();
            List<Phasedplan> phasedplans = repository.findAll();
            List<PhasedplanJson> phasedplanResponse = new ArrayList<>();

            for (Phasedplan p : phasedplans) {
                phasedplanResponse.add(new PhasedplanJson(p.getPhasedplanId(), p.getName(), p.getTasks()));
            }

            String json = gson.toJson(phasedplanResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply getPhasedplan(int phasedplanId) {
        Phasedplan phasedplan = repository.findOne(phasedplanId);

        if (phasedplan != null) {
            String json = gson.toJson(phasedplan);
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("Phasedplan doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    @Override
    public Reply savePhasedplan(Phasedplan phasedplan) {
        Phasedplan saved = repository.save(phasedplan);

        if (saved.getPhasedplanId() == phasedplan.getPhasedplanId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply getTasks(int phasedplanId) {
        try {
            Phasedplan p = repository.findOne(phasedplanId);
            List<TaskJson> tasksReponse = new ArrayList<>();

            for (Task t : p.getTasks()) {
                tasksReponse.add(new TaskJson(t.getTaskId(), t.getName(), t.getDescription()));
            }

            String json = gson.toJson(tasksReponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply saveTask(Phasedplan phasedplan) {
        Phasedplan saved = repository.save(phasedplan);

        if (phasedplan.getPhasedplanId() == phasedplan.getPhasedplanId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    private void addPhasedPlans() {
        Phasedplan phasedplan1 = new Phasedplan("Aardbeving");
        phasedplan1.addPhasedplanTask(new Task("Controleer op overlevenden", "Controleer de omgeving op overlevers"));
        phasedplan1.addPhasedplanTask(new Task("Berging", "Start bergingswerkzaamheden"));

        Phasedplan phasedplan2 = new Phasedplan("Bosbrand");
        phasedplan2.addPhasedplanTask(new Task("Blussen", "Start met blussen"));
        phasedplan2.addPhasedplanTask(new Task("Eigen veiligheid", "Controleer en zorg voor eigen veiligheid"));

        repository.save(phasedplan1);
        repository.save(phasedplan2);
    }
}