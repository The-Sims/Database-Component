package SimsRESTServer.response;

import models.Tip;

public class TipJson {
    private int id;
    private String origin;
    private String sender;
    private String message;
    private String location;
    private boolean confirmed;

    public TipJson(Tip tip) {
        this.id = tip.getId();
        this.origin = tip.getOrigin().toString();
        this.sender = tip.getSender();
        this.message = tip.getMessage();
        this.location = tip.getLocation();
        this.confirmed = tip.isConfirmed();
    }
}
