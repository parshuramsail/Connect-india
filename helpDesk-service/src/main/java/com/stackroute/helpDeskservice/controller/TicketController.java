package com.stackroute.helpDeskservice.controller;

import com.stackroute.helpDeskservice.dto.TicketDto;
import com.stackroute.helpDeskservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Value("${delete.message}")
    private String deletionMessage;

//    @GetMapping("/message")
//    public String getMessage() {
//        return "It's working ..........!!";
//    }

    @PostMapping("/ticket")
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto) {
        return new ResponseEntity<>(ticketService.addTicket(ticketDto), HttpStatus.OK);
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable long id, @RequestBody TicketDto ticketDto) {
        return new ResponseEntity<>(ticketService.updateTicket(id, ticketDto), HttpStatus.OK);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable long id) {
        return new ResponseEntity<>(ticketService.getTicketById(id), HttpStatus.OK);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(deletionMessage, HttpStatus.OK);
    }

    @GetMapping("/ticket/reporter/{reporterEmailId}")
    public ResponseEntity<List<TicketDto>> getTicketsByReporterMailId(@PathVariable String reporterEmailId) {
        return new ResponseEntity<>(ticketService.getByReporterEmailId(reporterEmailId), HttpStatus.OK);
    }

    @GetMapping("/ticket/assignee/{assigneeEmailId}")
    public ResponseEntity<List<TicketDto>> getTicketsByAssigneeMailId(@PathVariable String assigneeEmailId) {
        return new ResponseEntity<>(ticketService.getByAssigneeEmailId(assigneeEmailId), HttpStatus.OK);
    }
}
