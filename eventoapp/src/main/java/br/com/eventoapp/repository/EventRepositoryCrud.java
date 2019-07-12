package br.com.eventoapp.repository;

import br.com.eventoapp.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepositoryCrud extends CrudRepository<Event, Long> {
}
