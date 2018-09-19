package com.example.demo.repository;

import com.example.demo.model.ticket.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Query("select ticket from Ticket ticket join fetch ticket.car car join fetch ticket.parkingPlace place where car.user.id =:id")
    Iterable<? extends Ticket> findAllByUserId(Long id);
}
