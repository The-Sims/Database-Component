package models;

import SimsRESTServer.response.MessageJson;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)    // don't remove this line
    private List<Tip> tips;



    public Incident(ArrayList<IncidentDescription> descriptions, Origin origin, Category category, String place, boolean live, ArrayList<ReinforceInfo> reinforceInfo) {
        this.descriptions = descriptions;
        for (IncidentDescription description : this.descriptions) {
            description.setIncident(this);
        }
        this.origin = origin;
        this.category = category;
        this.place = place;
        this.live = live;
        this.reinforceInfo = reinforceInfo;
        for (ReinforceInfo info : this.reinforceInfo) {
            info.setIncident(this);
        }
    }

    public Incident() {
    }

    public int getId() {
        return id;
    }

    public List<IncidentDescription> getDescriptions() {
        return descriptions;
    }

    public ArrayList<String> getDescriptionStrings() {
        ArrayList<String> descriptionStrings = new ArrayList<>();
        for(IncidentDescription d: descriptions){
            descriptionStrings .add(d.getDescription());
        }
        return descriptionStrings ;
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

    public ArrayList<MessageJson> getDescriptionJson(){
        ArrayList<MessageJson> descriptionJson = new ArrayList<>();
        for(IncidentDescription i: descriptions){
            descriptionJson.add(new MessageJson(i.getDescription(), i.getDate().toString()));
        }
        return descriptionJson;
    }

    public ArrayList<MessageJson> getReinformentJson(){
        ArrayList<MessageJson> reinforcementJson = new ArrayList<>();
        for(ReinforceInfo r: reinforceInfo){
            reinforcementJson.add(new MessageJson(r.getReinforceInfo(), r.getDate().toString()));
        }
        return reinforcementJson;
    }
}
