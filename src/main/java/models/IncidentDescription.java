package models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name="incidentDescription")
public class IncidentDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
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
