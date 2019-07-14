package br.com.eventoapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evn_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long evn_pk;

    @Column(name = "evn_name", length = 50, nullable = false)
    @NotEmpty(message = "IT IS NECESSARY TO SET AN EVENT NAME.")
    private String name;

    @Column(name = "evn_place", length = 100, nullable = false)
    @NotEmpty(message = "IT IS NECESSARY TO SET AN EVENT PLACE.")
    private String place;

    @Column(name = "evn_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Necessary to change data format, if not, a exception will happen.
    @NotNull(message = "IT IS NECESSARY TO SET A DATE.")
    private Date date;

    // One event to many guests.
    @OneToMany
    private List<Guest> guests;

    public long getEvent_pk() {
        return evn_pk;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getplace() {
        return place;
    }

    public void setplace(String place) {
        this.place = place;
    }

    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }
}
