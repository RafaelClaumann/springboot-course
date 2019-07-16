package br.com.eventoapp.controllers;

import br.com.eventoapp.models.Event;
import br.com.eventoapp.models.Guest;
import br.com.eventoapp.repository.EventRepositoryCrud;
import br.com.eventoapp.repository.EventRepositoryJpa;
import br.com.eventoapp.repository.GuestRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepositoryCrud eventRepositoryCrud;  // Using JpaRepository instead of CrudRepository.

    @Autowired
    private EventRepositoryJpa eventRepositoryJpa;

    @Autowired
    private GuestRepositoryJpa guestRepositoryJpa;

    /*
     * This method will return a html form to be filled with event data.
     * @FormEvent.html are in /src/main/resources/templates/event
     * see also @saveEvent(Event event, BindingResult result, RedirectAttributes attributes).
     *
     * */
    @RequestMapping(value = "EventRegister", method = RequestMethod.GET)
    public ModelAndView newEvent(Event event) {
        ModelAndView mv = new ModelAndView("event/FormEvent");
        return mv;
    }

    /*
     * This method will record an event in DataBase table evn_event and redirect to @form().
     * See also @newEvent(Event event).
     *
     * */
    @RequestMapping(value = "EventRegister", method = RequestMethod.POST)
    public ModelAndView saveEvent(@Valid Event event, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return newEvent(event);
        }

        ModelAndView mv = new ModelAndView("redirect:/Events");
        eventRepositoryJpa.saveAndFlush(event);  // save event in DB
        attributes.addFlashAttribute("messageHTML", "Event added.");
        return mv;
    }

    /*
     * This method will open a link that contains details of specified event, links are based in event primary key.
     * @EventDetails.html is in /src/main/resources/templates/event
     * @eventHTML is a variable that will be accessed in EventDetails.html
     *
     * @List<Guest> guests, after guests.removeIf() this list will contain only guests of a specific @event object.
     * @guestsHTML is a list that will be accessed in EventDetails.html
     *
     * */
    @RequestMapping(value = "/{event_pk}", method = RequestMethod.GET)
    public ModelAndView eventDetailsGet(@PathVariable("event_pk") long evn_pk) {
        Event event = eventRepositoryJpa.getOne(evn_pk);  // getOne event from DB
        ModelAndView mv = new ModelAndView("event/EventDetails");  // set current view
        mv.addObject("eventHTML", event);  // send event to a view

        List<Guest> guests = guestRepositoryJpa.findAll();  // get all guests from DB to a list
        guests.removeIf(guest -> !(guest.getEvent().equals(event)));  // clear list based on current @event

        mv.addObject("guestsHTML", guests); // send guest list to a view
        return mv;
    }

    /*
     * This method will set an @event for a specific @guest and save it on gst_guest DB table.
     * @MessageHTML is a variable, this variable is accessed in a thymeleaf block at EventDetails.html.
     * @EventDetails.html are in /src/main/resourcers/templates/event
     *
     * */
    @RequestMapping(value = "/{event_pk}", method = RequestMethod.POST)
    public ModelAndView eventDetailsPost(@PathVariable("event_pk") long evn_pk, @Valid Guest guest,
                                         BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("messageHTML", "Verify fields.");
            return eventDetailsGet(evn_pk);
        }

        ModelAndView mv = new ModelAndView("redirect:/{event_pk}");
        Event event = eventRepositoryJpa.getOne(evn_pk);  // fetch one event from DB
        guest.setEvent(event);  // set the event to a guest
        guestRepositoryJpa.saveAndFlush(guest);  // push this guest to DB
        attributes.addFlashAttribute("messageHTML", "Guest Added!");  // success message
        return mv;
    }


    @RequestMapping(value = "/DeleteEvent")
    public String deleteEvent(long evn_pk) {
        eventRepositoryJpa.deleteById(evn_pk);
        return "redirect:/Events";
    }

    @RequestMapping("/DeleteGuest")
    public String deleteGuest(long gst_pk) {
        guestRepositoryJpa.deleteById(gst_pk);
        return "redirect:/Events";
    }

    /*
     * This method will list events recorded in the database.
     * @Index html are in /src/main/resources/templates
     * @eventsHTML is a variable that will be accessed in @Index.html
     *
     * */
    @RequestMapping(value = {"/Events", "/events"})
    public ModelAndView listEvents() {
        ModelAndView mv = new ModelAndView("Index");  // set current view
        List<Event> events = eventRepositoryJpa.findAll();  // fetch all events from DB
        mv.addObject("eventsHTML", events);  // send events list to a view
        return mv;
    }

}
