package models;

import javax.persistence.*;

@Entity
@Table(name="tips")
public class Tip {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Origin origin;
    private String sender;
    private String message;
    private String location;
    private boolean confirmed;

    @ManyToOne(cascade = CascadeType.ALL)
    private Incident incident;

    public Tip(int tipId, Origin origin, String sender, String message, String location) {
        this.id = tipId;
        this.origin = origin;
        this.sender = sender;
        this.message = message;
        this.location = location;
    }

    public Tip(Origin origin, String sender, String message, String location) {
        this.id = -1;
        this.origin = origin;
        this.sender = sender;
        this.message = message;
        this.location = location;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
