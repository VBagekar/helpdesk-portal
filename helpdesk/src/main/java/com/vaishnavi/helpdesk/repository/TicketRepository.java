package com.vaishnavi.helpdesk.repository;

import com.vaishnavi.helpdesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCreatedBy(String email);
}