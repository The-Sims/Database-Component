package models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reinforceinfo")
public class ReinforceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reinforceInfo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Incident incident;

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public ReinforceInfo(String reinforceInfo) {
        this.reinforceInfo = reinforceInfo;
    }

    public ReinforceInfo(int id, String reinforceInfo, Date date) {
        this.id = id;
        this.reinforceInfo = reinforceInfo;
        this.date = date;
    }

    public ReinforceInfo() {
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getReinforceInfo() {
        return reinforceInfo;
    }
}
