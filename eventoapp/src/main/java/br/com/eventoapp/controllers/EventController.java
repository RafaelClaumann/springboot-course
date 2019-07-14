package br.com.eventoapp.controllers;

import br.com.eventoapp.models.Event;
import br.com.eventoapp.models.Guest;
import br.com.eventoapp.repository.EventRepositoryCrud;
import br.com.eventoapp.repository.EventRepositoryJpa;
import br.com.eventoapp.repository.GuestRepository;
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

    @Autowired
    private GuestRepository guestRepository;

    /*
     * This method will return a html form to fill with event data.
     * @FormEvent.html are in /src/main/resources/templates/event
     * see also @form(Event event).
     *
     * */
    @RequestMapping(value = "EventRegister", method = RequestMethod.GET)
    public String form() {
        return "event/FormEvent";
    }

    /*
     * This method will record an event in DataBase table evn_event, event data are filled in a form.
     * Then will redirect to @form().
     * see also @form().
     * */
    @RequestMapping(value = "EventRegister", method = RequestMethod.POST)
    public String form(Event event) {
        eventRepositoryJpa.saveAndFlush(event);
        return "redirect:/EventRegister";
    }

    /*
     * This method will list events recorded in the database.
     * @Index html are in /src/main/resources/templates
     * @eventsHTML is a variable that will be accessed in @Index.html
     *
     * */
    @RequestMapping(value = {"/Events", "/events"})
    public ModelAndView listEvents() {
        ModelAndView mv = new ModelAndView("Index");
        List<Event> events = eventRepositoryJpa.findAll();
        mv.addObject("eventsHTML", events);  // event need to be the same name in Index.html
        return mv;
    }

    /*
     * This method will open a link that contains details of specified event, links are based in event primary key.
     * @EventDetails.html is in /src/main/resources/templates/event
     * @eventHTML is a variable that will be accessed in EventDetails.html
     *
     * @List<Guest> guests, after guests.removeIf() this list will contain only guests of specific @event object.
     * @guestsHTML is a list that will be accessed in EventDetails.html
     *
     * */
    @RequestMapping(value = "/{event_pk}", method = RequestMethod.GET)
    public ModelAndView eventDetailsGet(@PathVariable("event_pk") long evn_pk) {
        Event event = eventRepositoryJpa.getOne(evn_pk);
        ModelAndView mv = new ModelAndView("event/EventDetails");
        mv.addObject("eventHTML", event);

        List<Guest> guests = guestRepository.findAll();
        guests.removeIf(guest -> !(guest.getEvent().equals(event)));
        // guests.removeIf(guest -> guest.getEvent().getEvent_pk() == event.getEvent_pk());

        mv.addObject("guestsHTML", guests);
        return mv;
    }

    /*
     * This method will set an @event for a specific @guest and save it on gst_guest DB table.
     *
     * */
    @RequestMapping(value = "/{event_pk}", method = RequestMethod.POST)
    public String eventDetailsPost(@PathVariable("event_pk") long evn_pk, Guest guest) {
        Event event = eventRepositoryJpa.getOne(evn_pk);
        guest.setEvent(event);
        guestRepository.saveAndFlush(guest);
        return "redirect:/{event_pk}";
    }
}
