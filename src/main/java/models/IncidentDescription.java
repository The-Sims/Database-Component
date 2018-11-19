package models;

import javax.persistence.*;

@Entity
@Table(name="incidentDescription")
public class IncidentDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;

    @ManyToOne
    @JoinColumn(name="incident_id", nullable=false)
    private Incident incident;

    public IncidentDescription(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public IncidentDescription() {
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
