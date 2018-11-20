package models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name="reinforceinfo")
public class ReinforceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reinforceInfo;

    @ManyToOne(cascade = CascadeType.ALL)
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
