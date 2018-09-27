package com.example.demo.dto;

import com.example.demo.model.place.i18n.CityI18n;
import com.example.demo.model.place.i18n.StreetI18n;
import com.example.demo.model.ticket.Ticket;


public class TicketQueryDto {
    private final Ticket ticket;
    private final StreetI18n streetI18n;
    private final CityI18n cityI18n;


    public TicketQueryDto(Ticket ticket, StreetI18n streetI18n, CityI18n cityI18n) {
        this.ticket = ticket;
        this.streetI18n = streetI18n;
        this.cityI18n = cityI18n;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public StreetI18n getStreetI18n() {
        return streetI18n;
    }

    public CityI18n getCityI18n() {
        return cityI18n;
    }
}
