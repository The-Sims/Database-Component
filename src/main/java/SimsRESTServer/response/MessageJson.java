package SimsRESTServer.response;

import java.util.Date;

public class MessageJson {
    int id;
    String message;
    Date date;

    public MessageJson(int id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
