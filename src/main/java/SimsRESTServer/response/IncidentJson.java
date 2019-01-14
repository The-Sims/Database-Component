package SimsRESTServer.response;

import models.Category;
import models.Tip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncidentJson {
    int id;
    ArrayList<MessageJson> incidentDescription;
    Date createDate;
    Date modifyDate;
    CategoryJson category;
    String place;
    boolean live;
    boolean confirmed;
    ArrayList<MessageJson> reinforcementInfo;
    ArrayList<TipJson> tips;
    ArrayList<String> subscribedIds = new ArrayList<>();


    public IncidentJson(int id, Category category, String place, ArrayList<MessageJson> reinforcementInfo, ArrayList<MessageJson> incidentDescription, boolean live, Date create_date, Date modify_date, List<Tip> tips, boolean confirmed) {
        this.id = id;
        this.category = new CategoryJson(category);
        this.place = place;
        if (reinforcementInfo != null)
            this.reinforcementInfo = reinforcementInfo;
        else
            this.reinforcementInfo = new ArrayList<>();
        if (incidentDescription != null)
            this.incidentDescription = incidentDescription;
        else
            this.incidentDescription = new ArrayList<>();
        this.live = live;
        this.createDate = create_date;
        this.modifyDate = modify_date;
        this.tips = new ArrayList<>();
        if (tips != null) {
            for (Tip t : tips) {
                this.tips.add(new TipJson(t));
            }
        }
        this.confirmed = confirmed;
    }
    //test

    public int getId() {
        return id;
    }

    public String getPlace(){
        return place;
    }

    public ArrayList<MessageJson> getReinforcementInfo() {
        return reinforcementInfo;
    }

    public boolean isLive() {
        return live;
    }

}
