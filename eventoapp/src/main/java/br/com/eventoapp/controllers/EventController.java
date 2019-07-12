package br.com.eventoapp.controllers;

import br.com.eventoapp.models.Event;
import br.com.eventoapp.repository.EventRepositoryCrud;
import br.com.eventoapp.repository.EventRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    @Autowired
    private EventRepositoryCrud eventRepositoryCrud;

    @Autowired
    private EventRepositoryJpa eventRepositoryJpa;

    /*
    * This method will return a form to register an event.
    * */
    @RequestMapping(value = "/registerEvent", method = RequestMethod.GET)
    public String form() {
        return "event/FormEvent";
    }

    /*
    * This method will save an event in MySQL database.
    * */
    @RequestMapping(value = "/registerEvent", method = RequestMethod.POST)
    public String form(Event event) {
        eventRepositoryJpa.saveAndFlush(event);
        // eventRepositoryCrud.save(event);
        return "redirect:/registerEvent";
    }
}
