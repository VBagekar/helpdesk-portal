package com.vaishnavi.helpdesk.repository;

import com.vaishnavi.helpdesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}