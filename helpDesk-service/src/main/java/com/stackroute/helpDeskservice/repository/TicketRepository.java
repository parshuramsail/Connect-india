package com.stackroute.helpDeskservice.repository;

import com.stackroute.helpDeskservice.modal.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, Long> {

    List<Ticket> findByReporterEmailId(String reporterEmailId);
    List<Ticket> findByAssigneeEmailId(String assigneeEmailId);
}
