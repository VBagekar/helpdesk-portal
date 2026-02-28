package com.vaishnavi.helpdesk.service;

import com.vaishnavi.helpdesk.entity.Ticket;
import com.vaishnavi.helpdesk.repository.TicketRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public Ticket updateStatus(Long id, String status) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(status);

        return ticketRepository.save(ticket);
    }
    public Ticket createTicket(Ticket ticket) {

        // Get logged-in user email from JWT authentication
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        ticket.setCreatedBy(email);
        ticket.setStatus("OPEN");

        return ticketRepository.save(ticket);
    }
}