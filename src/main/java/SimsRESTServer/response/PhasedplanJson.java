package SimsRESTServer.response;

import models.Task;

import java.util.ArrayList;
import java.util.List;

public class PhasedplanJson {
    private int phasedPlanId;
    private String name;
    private List<Task> tasks = new ArrayList<>();

    public int getPhasedPlanId() {
        return phasedPlanId;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public PhasedplanJson(int phasedPlanId, String name, List<Task> tasks) {
        this.phasedPlanId = phasedPlanId;
        this.name = name;
        this.tasks = tasks;
    }
}
