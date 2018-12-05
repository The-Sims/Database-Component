package models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "phasedplan")
public class Phasedplan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int phasedPlanId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "phasedplan_id")
    @Fetch(FetchMode.SELECT)
    private List<Task> tasks;

    // Getters and setters
    public int getPhasedplanId() {
        return phasedPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    public void addPhasedplanTask(Task task) {
        tasks.add(task);
    }

    public void editPhasedPlanTask(Task task) {
        for (Task t: tasks) {
            if (t.getTaskId() == task.getTaskId()) {
                t.setName(task.getName());
                t.setDescription(task.getDescription());
            }
        }
    }

    // Constructors
    public Phasedplan() {}

    public Phasedplan(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public Phasedplan(int phasedplanId, String name) {
        this.phasedPlanId = phasedplanId;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public Phasedplan(String name, List<Task> tasks) {
        this.name = name;
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }
    public Phasedplan(int phasedPlanId, String name, List<Task> tasks) {
        this.phasedPlanId = phasedPlanId;
        this.name = name;
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    // Methods
    @Override
    public String toString() {
        return this.name;
    }

    public static Phasedplan fromJson(String data) {
        Gson gson  = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        Phasedplan phasedplan = new Phasedplan(jsonObject.get("_name").getAsString());
        JsonArray jsonTasks = jsonObject.getAsJsonArray("_tasks").getAsJsonArray();

        for (JsonElement jsonTask : jsonTasks) {
            JsonObject jsonObject1 = jsonTask.getAsJsonObject();
            phasedplan.addPhasedplanTask(new Task(jsonObject1.get("_name").getAsString(), jsonObject1.get("_description").getAsString()));
        }

        return phasedplan;
    }
}
