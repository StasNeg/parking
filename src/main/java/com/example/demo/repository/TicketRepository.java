package com.example.demo.repository;

import com.example.demo.dto.TicketDto;
import com.example.demo.dto.TicketQueryDto;
import com.example.demo.model.enums.Language;
import com.example.demo.model.ticket.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Query("select ticket from Ticket ticket join ticket.car car " +
            "where car.user.id =:id")
    Iterable<Ticket> findAllByUserId(Long id);

    @Query("select new com.example.demo.dto.TicketQueryDto(ticket, streetI18n, cityI18n) from Ticket ticket " +
            "join ticket.car car " +
            "inner join ticket.parkingPlace.street.streetI18ns streetI18n " +
            "inner join ticket.parkingPlace.street.city.cities cityI18n " +
            "where car.user.id =:id " +
            "and streetI18n.lang =:lang and cityI18n.lang =:lang")
    Iterable<TicketQueryDto> findAllByUserId(Long id, Language lang);

    //    public TicketDto(String street, String city, String streetNumber, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String carNumber, CarDto car) {

    @Query("select new com.example.demo.dto.TicketDto(ticket.id, streetI18n.name, cityI18n.name, ticket.parkingPlace.streetNumber, ticket.dateTimeStart, ticket.dateTimeEnd, ticket.car.number, ticket.car) from Ticket ticket " +
            "join ticket.car car " +
            "inner join ticket.parkingPlace.street.streetI18ns streetI18n " +
            "inner join ticket.parkingPlace.street.city.cities cityI18n " +
            "where car.user.id =:id " +
            "and streetI18n.lang =:lang and cityI18n.lang =:lang")
    Iterable<TicketDto> findAllByUserIdToTicketDto(Long id, Language lang);

}
