package com.stackroute.helpDeskservice.service;

import com.stackroute.helpDeskservice.dto.TicketDto;
import com.stackroute.helpDeskservice.exception.NoTicketFoundException;
import com.stackroute.helpDeskservice.exception.TicketIdNotFoundException;
import com.stackroute.helpDeskservice.modal.Ticket;
import com.stackroute.helpDeskservice.repository.TicketRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepo;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Value("${id.not.found.exception.message}")
    private String idNotFoundMessage;
    @Value("${no.ticket.found.reporter}")
    private String noReporterTicket;
    @Value("${no.ticket.found.assignee}")
    private String noAssigneeTicket;

    Log log = LogFactory.getLog(TicketServiceImpl.class);

    @Override
    public TicketDto addTicket(TicketDto ticketDto) {

        Ticket ticket = new Ticket();
        ticket.setTicketId(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME));
        ticketRepo.save(convertToEntity(ticketDto, ticket));
        log.info("Ticket successfully created and stored in database");
        return convertToDto(ticket);
    }

    @Override
    public TicketDto updateTicket(long id, TicketDto ticketDto) {
        Optional<Ticket> ticketOptional = ticketRepo.findById(id);
        if (ticketOptional.isEmpty()) {
            log.error("Exception thrown in method updateTicket");
            throw new TicketIdNotFoundException(idNotFoundMessage);
        }
        Ticket ticket = ticketOptional.get();
        convertToEntity(ticketDto, ticket);
        ticketRepo.save(ticket);
        log.info("Ticket successfully updated");
        return convertToDto(ticket);
    }

    @Override
    public TicketDto getTicketById(long id) {
        Optional<Ticket> ticketOptional = ticketRepo.findById(id);
        if (ticketOptional.isEmpty()) {
            log.error("Exception thrown in method getTicketById");
            throw new TicketIdNotFoundException(idNotFoundMessage);
        }
        return convertToDto(ticketOptional.get());
    }

    @Override
    public void deleteTicket(long id) {
        Optional<Ticket> ticketOptional = ticketRepo.findById(id);
        if (ticketOptional.isEmpty()) {
            log.error("Exception thrown in method deleteTicket");
            throw new TicketIdNotFoundException(idNotFoundMessage);
        }
        ticketRepo.deleteById(id);
    }

    @Override
    public List<TicketDto> getByReporterEmailId(String reporterEmailId) {
        List<Ticket> tickets = ticketRepo.findByReporterEmailId(reporterEmailId);
        log.info("Tickets");
        log.info(tickets.toString());
        if(tickets.isEmpty()) {
            log.error("Exception thrown in method getByReporterEmailId");
            throw new NoTicketFoundException(noReporterTicket);
        }
        List<TicketDto> dtos = new ArrayList<>();
        for(Ticket ticket : tickets) {
            dtos.add(convertToDto(ticket));
        }
        return dtos;
    }

    @Override
    public List<TicketDto> getByAssigneeEmailId(String assigneeEmailId) {
        List<Ticket> tickets = ticketRepo.findByAssigneeEmailId(assigneeEmailId);
        if(tickets.isEmpty()) {
            log.error("Exception thrown in method getByAssigneeEmailId");
            throw new NoTicketFoundException(noAssigneeTicket);
        }
        List<TicketDto> dtos = new ArrayList<>();
        for(Ticket ticket : tickets) {
            dtos.add(convertToDto(ticket));
        }
        return dtos;
    }

    private Ticket convertToEntity(TicketDto ticketDto, Ticket ticket) {
        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setCreatedDate(ticketDto.getCreatedDate());
        ticket.setReporterEmailId(ticketDto.getReporterEmailId());
        ticket.setAssigneeEmailId(ticketDto.getAssigneeEmailId());
        ticket.setStatus(ticketDto.getStatus());
        ticket.setPriority(ticketDto.getPriority());
        ticket.setComments(ticketDto.getComments());
        return ticket;
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setTicketId(ticket.getTicketId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setCreatedDate(ticket.getCreatedDate());
        dto.setReporterEmailId(ticket.getReporterEmailId());
        dto.setAssigneeEmailId(ticket.getAssigneeEmailId());
        dto.setStatus(ticket.getStatus());
        dto.setPriority(ticket.getPriority());
        dto.setComments(ticket.getComments());
        return dto;
    }


}
