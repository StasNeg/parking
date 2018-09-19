package com.example.demo.service;

import com.example.demo.dto.TicketDto;
import com.example.demo.model.ticket.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Iterable<TicketDto> getAll() {
        Iterable<TicketDto> dtos = new LinkedList<>();
        for (Ticket ticket :ticketRepository.findAllByUserId(UserUtil.getAutorizedId())
                ) {
            ((LinkedList<TicketDto>) dtos).add(TicketDto.asTo(ticket));

        }
        return dtos;
    }
}
