package com.vaishnavi.helpdesk.controller;

import com.vaishnavi.helpdesk.entity.Ticket;
import com.vaishnavi.helpdesk.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @PutMapping("/{id}/status")
    public Ticket updateTicketStatus(@PathVariable Long id,
                                     @RequestParam String status) {
        return ticketService.updateStatus(id, status);
    }
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
}