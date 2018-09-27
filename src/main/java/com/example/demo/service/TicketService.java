package com.example.demo.service;

import com.example.demo.dto.CarDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.dto.TicketQueryDto;
import com.example.demo.model.enums.Language;
import com.example.demo.model.place.Street;
import com.example.demo.model.place.i18n.CityI18n;
import com.example.demo.model.place.i18n.StreetI18n;
import com.example.demo.model.ticket.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Iterable<TicketDto> getAllWithDto() {
        Iterable<TicketDto> dtos = new LinkedList<>();
        Language language = Language.valueOf(LocaleContextHolder.getLocale().getLanguage().toUpperCase());
        Iterable<TicketQueryDto> iterable = ticketRepository.findAllByUserId(UserUtil.getAutorizedId(), language);
        for (TicketQueryDto ticket : iterable) {
            ((LinkedList<TicketDto>) dtos).add(
                    new TicketDto(ticket.getTicket().getId(),ticket.getCityI18n().getName(),ticket.getStreetI18n().getName(), ticket.getTicket().getParkingPlace().getStreetNumber(),
                    ticket.getTicket().getDateTimeStart(),ticket.getTicket().getDateTimeStart(),ticket.getTicket().getCar().getNumber(),CarDto.asTo(ticket.getTicket().getCar())));
        }
        return dtos;
    }
    public Iterable<TicketDto> getAll() {
        return ticketRepository.findAllByUserIdToTicketDto(UserUtil.getAutorizedId(), Language.valueOf(LocaleContextHolder.getLocale().getLanguage().toUpperCase()));
    }

}
