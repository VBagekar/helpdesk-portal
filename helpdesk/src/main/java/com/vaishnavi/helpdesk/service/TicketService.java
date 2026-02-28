package com.vaishnavi.helpdesk.service;

import com.vaishnavi.helpdesk.entity.Ticket;
import com.vaishnavi.helpdesk.repository.TicketRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.vaishnavi.helpdesk.dto.TicketResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class TicketService {
    private TicketResponse mapToResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority()
        );
    }
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public Ticket updateStatus(Long id, String status) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        String role = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        // Only ADMIN can close
        if (status.equals("CLOSED") && !role.equals("ROLE_ADMIN")) {
            throw new RuntimeException("Only ADMIN can close tickets");
        }

        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }


    public List<TicketResponse> getMyTickets() {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ticketRepository.findByCreatedBy(email)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public Ticket createTicket(Ticket ticket) {

        // Get logged-in user email from JWT authentication
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        ticket.setCreatedBy(email);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setStatus("OPEN");

        return ticketRepository.save(ticket);
    }
}