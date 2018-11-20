package SimsRESTServer.response;

import java.util.ArrayList;

public class IncidentJson {
    int id;
    String category;
    String place;
    ArrayList<String> reinforcementInfo;
    ArrayList<String> incidentDescription;
    boolean live;
    String create_date;
    String modify_date;

    public IncidentJson(int id, String category, String place, ArrayList<String> reinforcementInfo, ArrayList<String> incidentDescription, boolean live, String create_date, String modify_date) {
        this.id = id;
        this.category = category;
        this.place = place;
        this.reinforcementInfo = reinforcementInfo;
        this.incidentDescription = incidentDescription;
        this.live = live;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getPlace(){
        return place;
    }

    public ArrayList<String> getReinforcementInfo() {
        return reinforcementInfo;
    }

    public boolean isLive() {
        return live;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getModify_date() {
        return modify_date;
    }
}
