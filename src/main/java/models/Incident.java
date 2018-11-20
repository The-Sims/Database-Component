package models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)    // don't remove this line
    private List<IncidentDescription> descriptions;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @Enumerated(EnumType.STRING)
    private Origin origin;

    @ManyToOne(cascade = { CascadeType.ALL })
    private Category category;

    private String place;

    private boolean live;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)    // don't remove this line
    private List<ReinforceInfo> reinforceInfo;

    public Incident(ArrayList<IncidentDescription> descriptions, Date createDate, Date modifyDate, Origin origin, Category category, String place, boolean live, ArrayList<ReinforceInfo> reinforceInfo) {
        this.descriptions = descriptions;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.origin = origin;
        this.category = category;
        this.place = place;
        this.live = live;
        this.reinforceInfo = reinforceInfo;
    }

    public Incident() {
    }

    public int getId() {
        return id;
    }

    public List<IncidentDescription> getDescriptions() {
        return descriptions;
    }

    public String getFirstDescription() {
        IncidentDescription incidentDescription = descriptions.get(0);
        return incidentDescription.getDescription();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryString(){
        return category.getCategory();
    }

    public String getPlace() {
        return place;
    }

    public boolean isLive() {
        return live;
    }

    public List<ReinforceInfo> getReinforceInfo(){
        return reinforceInfo;
    }

    public ArrayList<String> getReinforceInfoStrings(){
        ArrayList<String> reinforceInfosStrings = new ArrayList<>();
        for(ReinforceInfo r: reinforceInfo){
            reinforceInfosStrings.add(r.getReinforceInfo());
        }
        return reinforceInfosStrings;
    }
}
