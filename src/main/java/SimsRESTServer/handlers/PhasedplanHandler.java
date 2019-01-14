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
        //addPhasedPlans();

    }

    @Override
    public Reply getPhasedplans() {
        try {
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

    private void addPhasedPlans() {
        Phasedplan phasedplan1 = new Phasedplan("Brand");
        phasedplan1.addPhasedplanTask(new Task("Eigen veiligheid", "Controleer de omgeving voor eigen veiligheid"));
        phasedplan1.addPhasedplanTask(new Task("Blussen", "Start met blussen"));
        phasedplan1.addPhasedplanTask(new Task("Controleer op overlevenden", "Controleer de omgeving op overlevenden"));
        phasedplan1.addPhasedplanTask(new Task("Breng de overlevenden in veiligheid", "Breng de overlevenden in veiligheid. Draag ze desnoods over aan ambulance medewerkers"));
        phasedplan1.addPhasedplanTask(new Task("Na blussen", "Blijf blussen tot het brandgevaar volledig weg is"));
        phasedplan1.addPhasedplanTask(new Task("Vind oorkzaak", "Zoek naar de oorzaak van de brand"));
        phasedplan1.addPhasedplanTask(new Task("Omgeving vrijgeven", "Geef de omgeving vrij nadat het brandgevaar volledig weg is en de oorzaak van de brand vastgesteld is"));

        Phasedplan phasedplan2 = new Phasedplan("Bommelding");
        phasedplan2.addPhasedplanTask(new Task("Eigen veiligheid", "Controleer de omgeving voor eigen veiligheid"));
        phasedplan2.addPhasedplanTask(new Task("Omgevig ontruimen", "Ontruim de omgeving van de bom. Let hierbij op dat alles binnen de mogelijke ontploffingsradius van de bom ontruimd wordt"));
        phasedplan2.addPhasedplanTask(new Task("Bom ontmantelen", "Begin met het ontmantelen van de bom"));
        phasedplan2.addPhasedplanTask(new Task("Omgeving vrijgeven", "Indien de bom ontmanteld is kan de omgeving weer vrijgegeven worden"));

        Phasedplan phasedplan3 = new Phasedplan("Gijzeling");
        phasedplan3.addPhasedplanTask(new Task("Eigen veiligheid", "Controleer de omgeving voor eigen veiligheid"));
        phasedplan3.addPhasedplanTask(new Task("Controleer voor gijzelaars", "Controleer of er mogelijk gijzelaars aanwezig zijn en schat hun veiligheid in"));
        phasedplan3.addPhasedplanTask(new Task("Start onderhandelen met gijzelaar", "Begin met het onderhandelen met de gijzelaar. Let hiebij op de veiligheid van de gijzelaars"));
        phasedplan3.addPhasedplanTask(new Task("Gijzelaars uit de situatie helpen", "Probeer de gijzelaars uit de handen van de gijzelaar te krijgen. Let hierbij op hun veiligheid"));
        phasedplan3.addPhasedplanTask(new Task("Draag gijzelaars over", "Draag de gijzelaars over aan de juiste instanties"));
        phasedplan3.addPhasedplanTask(new Task("Arresteer gijzelaar", "Arresteer de gijzelaar. Let hierbij op eigen veiligheid"));

        Phasedplan phasedplan4 = new Phasedplan("Overstroming");
        phasedplan4.addPhasedplanTask(new Task("Eigen veiligheid", "Controleer de omgeving voor eigen veiligheid"));
        phasedplan4.addPhasedplanTask(new Task("Omgeving ontruimen", "Ontruim de omgeving van de overstroming"));
        phasedplan4.addPhasedplanTask(new Task("Controleer op overlevenden", "Controleer de omgeving op overlevenden"));
        phasedplan4.addPhasedplanTask(new Task("Breng de overlevenden in veiligheid", "Breng de overlevenden in veiligheid. Draag ze desnoods over aan ambulance medewerkers"));

        repository.save(phasedplan1);
        repository.save(phasedplan2);
        repository.save(phasedplan3);
        repository.save(phasedplan4);
    }
}