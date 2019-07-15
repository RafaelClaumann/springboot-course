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
    @NotEmpty
    private String name;

    @Column(name = "evn_place", length = 100, nullable = false)
    @NotEmpty
    private String place;

    @Column(name = "evn_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Necessary to change data format, if not, an exception will happen.
    @NotNull
    private Date date;

    // One event to many guests.
    @OneToMany
    private List<Guest> guests;

    public long getEvent_pk() {
        return evn_pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
