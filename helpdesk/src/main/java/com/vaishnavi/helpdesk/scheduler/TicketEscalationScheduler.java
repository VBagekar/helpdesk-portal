package com.vaishnavi.helpdesk.scheduler;

import com.vaishnavi.helpdesk.entity.Ticket;
import com.vaishnavi.helpdesk.repository.TicketRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketEscalationScheduler {

    private final TicketRepository ticketRepository;

    public TicketEscalationScheduler(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // runs every 60 seconds (for testing)
    @Scheduled(fixedRate = 60000)
    public void escalateOldTickets() {

        List<Ticket> tickets = ticketRepository.findAll();

        for (Ticket ticket : tickets) {
            if (ticket.getStatus().equals("OPEN") &&
                    ticket.getCreatedAt() != null &&
                    ticket.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(2))) {

                ticket.setPriority("HIGH");
                ticketRepository.save(ticket);

                System.out.println("Ticket escalated: " + ticket.getId());
            }
        }
    }
}