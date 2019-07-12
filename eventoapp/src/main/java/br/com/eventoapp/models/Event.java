package br.com.eventoapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "evn_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long event_pk;

    @Column(name = "evn_name", length = 50, nullable = false)
    @NotEmpty(message = "É OBRIGATÓRIO NOMEAR O EVENTO.")
    private String name;

    @Column(name = "evn_place", length = 100, nullable = false)
    @NotEmpty(message = "É OBRIGATÓRIO NOMEAR O LUGAR DO EVENTO.")
    private String place;

    @Column(name = "evn_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Need to change data format, if not, a exception will happen.
    @NotNull(message = "É OBRIGATÓRIO DEFINIR UMA DATA PARA O EVENTO.")
    private Date date;

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
