package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "taskId", nullable = false)
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int taskId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Phasedplan phasedplan;

    // Getters and setters
    public int getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Constructor
    public Task() {}

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(int taskId, String name, String description) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    // Methods
    @Override
    public String toString() {
        return this.name;
    }
}
