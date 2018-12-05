package SimsRESTServer.response;

import models.Category;
import models.Origin;
import models.Tip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncidentJson {
    int id;
    ArrayList<MessageJson> incidentDescription;
    Date create_date;
    Date modify_date;
    CategoryJson category;
    String place;
    boolean live;
    boolean confirmed;
    ArrayList<MessageJson> reinforcementInfo;
    ArrayList<TipJson> tips;


    public IncidentJson(int id, Category category, String place, ArrayList<MessageJson> reinforcementInfo, ArrayList<MessageJson> incidentDescription, boolean live, Date create_date, Date modify_date, List<Tip> tips, boolean confirmed) {
        this.id = id;
        this.category = new CategoryJson(category);
        this.place = place;
        this.reinforcementInfo = reinforcementInfo;
        this.incidentDescription = incidentDescription;
        this.live = live;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.tips = new ArrayList<>();
        for (Tip t: tips){
            this.tips.add(new TipJson(t));
        }
        this.confirmed = confirmed;
    }

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
