package br.com.eventoapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "gst_guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gst_pk;

    @Column(name = "gst_rg", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "IT IS NECESSARY TO SET A GUEST RG.")
    private String rg;

    @Column(name = "gst_name", length = 100, nullable = false)
    @NotEmpty(message = "IT IS NECESSARY TO SET A GUEST NAME.")
    private String guestName;

    //  Many guests to one event.
    @ManyToOne
    private Event event;

    public long getGuest_pk() {
        return gst_pk;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
