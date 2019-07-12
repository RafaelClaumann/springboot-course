package br.com.eventoapp.repository;

import br.com.eventoapp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepositoryJpa extends JpaRepository<Event, Long> {
}
