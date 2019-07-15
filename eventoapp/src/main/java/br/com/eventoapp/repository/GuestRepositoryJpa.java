package br.com.eventoapp.repository;

import br.com.eventoapp.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepositoryJpa extends JpaRepository<Guest,Long> {
}
