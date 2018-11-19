package SimsRESTServer.response;

import java.util.ArrayList;

public class IncidentJson {
    int id;
    String category;
    String place;
    ArrayList<String> reinforcementInfo;
    boolean live;

    public IncidentJson(int id, String description, String place, ArrayList<String> reinforcementInfo, boolean live) {
        this.id = id;
        this.category = description;
        this.place = place;
        this.reinforcementInfo = reinforcementInfo;
        this.live = live;
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


}
