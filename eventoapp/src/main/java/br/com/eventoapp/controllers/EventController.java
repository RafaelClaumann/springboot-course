package br.com.eventoapp.controllers;

import br.com.eventoapp.models.Event;
import br.com.eventoapp.repository.EventRepositoryCrud;
import br.com.eventoapp.repository.EventRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepositoryCrud eventRepositoryCrud;  // Not in use at this moment.

    @Autowired
    private EventRepositoryJpa eventRepositoryJpa;

    /*
     * This method will return a form to register an event.
     * */
    @RequestMapping(value = "EventRegister", method = RequestMethod.GET)
    public String form() {
        return "event/FormEvent";
    }

    /*
     * This method will save an event form in MySQL database.
     * */
    @RequestMapping(value = "/EventRegister", method = RequestMethod.POST)
    public String form(Event event) {
        eventRepositoryJpa.saveAndFlush(event);
        return "redirect:/EventRegister";
    }

    @RequestMapping("/Events")
    public ModelAndView listEvents() {
        ModelAndView mv = new ModelAndView("Index");
        List<Event> events = eventRepositoryJpa.findAll();
        mv.addObject("events", events);  // event need to be the same name in Index.html
        return mv;
    }

    @RequestMapping("/{event_pk}")
    public ModelAndView eventDetails(@PathVariable("event_pk") long event_pk) {
        Event event = eventRepositoryJpa.getOne(event_pk);
        ModelAndView mv = new ModelAndView("event/EventDetails");
        mv.addObject("event", event);
        return mv;
    }
}
