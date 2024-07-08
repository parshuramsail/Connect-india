package com.stackroute.helpDeskservice.service;

import com.stackroute.helpDeskservice.dto.TicketDto;

import java.util.List;

public interface TicketService {

     TicketDto addTicket(TicketDto ticketDto);
     TicketDto updateTicket(long id, TicketDto ticketDto);
     TicketDto getTicketById(long id);
     void deleteTicket(long id);

     List<TicketDto> getByReporterEmailId(String reporterEmailId);

     List<TicketDto> getByAssigneeEmailId(String assigneeEmailId);

}
