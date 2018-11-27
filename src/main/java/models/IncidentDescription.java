package models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="incidentDescription")
public class IncidentDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Incident incident;

    public IncidentDescription(String description) {
        this.description = description;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public IncidentDescription() {
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
