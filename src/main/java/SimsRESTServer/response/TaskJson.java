package SimsRESTServer.response;

public class TaskJson {
    private int taskId;
    private String name;
    private String description;

    public int getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskJson(int taskId, String name, String description) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }
}
