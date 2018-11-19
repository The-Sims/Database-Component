package models;

import javax.persistence.*;

@Entity
@Table(name="reinforceinfo")
public class ReinforceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reinforceInfo;

    @ManyToOne
    @JoinColumn(name="incident_id", nullable=false)
    private Incident incident;

    public ReinforceInfo(int id, String reinforceInfo) {
        this.id = id;
        this.reinforceInfo = reinforceInfo;
    }

    public ReinforceInfo() {
    }

    public int getId() {
        return id;
    }

    public String getReinforceInfo() {
        return reinforceInfo;
    }
}
