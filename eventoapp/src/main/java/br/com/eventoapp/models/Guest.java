package br.com.eventoapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "gst_guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gst_pk;

    @Column(name = "gst_rg", length = 50, nullable = false, unique = true)
    @NotBlank(message = "please inform a RG!")
    private String rg;

    @Column(name = "gst_name", length = 100, nullable = false)
    @NotBlank(message = "please inform a name!")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
