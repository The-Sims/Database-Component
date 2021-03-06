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

    @ManyToOne(cascade = { CascadeType.ALL })
    private Category category;

    private String place;

    private boolean live;

    private boolean confirmed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)    // don't remove this line
    private List<ReinforceInfo> reinforceInfo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)    // don't remove this line
    private List<Tip> tips;



    public Incident(ArrayList<IncidentDescription> descriptions, Category category, String place, boolean live, ArrayList<ReinforceInfo> reinforceInfo) {
        this.descriptions = descriptions;
        for (IncidentDescription description : this.descriptions) {
            description.setIncident(this);
        }
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

    public Category getCategory() {
        return category;
    }

    public String getPlace() {
        return place;
    }

    public boolean isLive() {
        return live;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<Tip> getTips() {
        return tips;
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
            descriptionJson.add(new MessageJson(i.getId(),i.getDescription(), i.getDate()));
        }
        return descriptionJson;
    }

    public ArrayList<MessageJson> getReinformentJson(){
        ArrayList<MessageJson> reinforcementJson = new ArrayList<>();
        for(ReinforceInfo r: reinforceInfo){
            reinforcementJson.add(new MessageJson(r.getId(),r.getReinforceInfo(), r.getDate()));
        }
        return reinforcementJson;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
