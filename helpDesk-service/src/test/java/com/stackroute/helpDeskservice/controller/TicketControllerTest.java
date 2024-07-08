package com.stackroute.helpDeskservice.controller;

import com.stackroute.helpDeskservice.dto.TicketDto;
import com.stackroute.helpDeskservice.enums.Priority;
import com.stackroute.helpDeskservice.enums.Status;
import com.stackroute.helpDeskservice.exception.NoTicketFoundException;
import com.stackroute.helpDeskservice.exception.TicketIdNotFoundException;
import com.stackroute.helpDeskservice.modal.Ticket;
import com.stackroute.helpDeskservice.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketControllerTest {

    @MockBean
    TicketRepository ticketRepository;

    @Autowired
    TicketController ticketController;

    @Value("${delete.message}")
    private String deletionMessage;

    @Test
    public void getTicketsByReporterEmailIdTest() {
        Mockito.when(ticketRepository.findByReporterEmailId("virat@123")).thenReturn(Stream.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "virat@123", "sanyog@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList()));
        ResponseEntity<List<TicketDto>> expected = new ResponseEntity<>(Stream.of(new TicketDto(1, "speed_issue", "Speed is low", new Date(), "virat@123", "sanyog@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList()), HttpStatus.OK);
        Assert.assertEquals(expected.toString(), ticketController.getTicketsByReporterMailId("virat@123").toString());
    }

    @Test(expected = NoTicketFoundException.class)
    public void getTicketsByReporterEmailIdTest_Exception() {
        ticketController.getTicketsByReporterMailId("viren@123").toString();
    }

    @Test
    public void getTicketsByAssigneeEmailIdTest() {
        Mockito.when(ticketRepository.findByAssigneeEmailId("virat@123")).thenReturn(Stream.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList()));
        ResponseEntity<List<TicketDto>> expected = new ResponseEntity<>(Stream.of(new TicketDto(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList()), HttpStatus.OK);
        Assert.assertEquals(expected.toString(), ticketController.getTicketsByAssigneeMailId("virat@123").toString());
    }

    @Test(expected = NoTicketFoundException.class)
    public void getTicketsByAssigneeEmailIdTest_Exception() {
        ticketController.getTicketsByReporterMailId("viren@123").toString();
    }

    @Test
    public void deleteTicketsTest() {
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)));
        Mockito.doNothing().when(ticketRepository).deleteById(Mockito.anyLong());
        ResponseEntity<String> expected = new ResponseEntity<>(deletionMessage, HttpStatus.OK);
        Assert.assertEquals(expected.toString(), ticketController.deleteTicket(1).toString());
    }

    @Test(expected = TicketIdNotFoundException.class)
    public void deleteTicketsTest_Exception() {
        ticketController.deleteTicket(1);
    }

    @Test
    public void getTicketsByIdTest() {
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)));
        ResponseEntity<TicketDto> expected = new ResponseEntity<>(new TicketDto(1, "speed_issue", "Speed is low", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null), HttpStatus.OK);
        Assert.assertEquals(expected.toString(), ticketController.getTicketById(1).toString());
    }

    @Test(expected = TicketIdNotFoundException.class)
    public void getTicketsByIdTest_Exception() {
        ticketController.getTicketById(1);
    }

    @Test
    public void updateTicketTest() {
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)));
        TicketDto ticketDto = new TicketDto(1, "speed_issue1", "Speed is fluctuating", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        ResponseEntity<TicketDto> expected = new ResponseEntity<>(new TicketDto(1, "speed_issue1", "Speed is fluctuating", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null), HttpStatus.OK);
        Assert.assertEquals(expected.toString(), ticketController.updateTicket(1, ticketDto).toString());
    }

    @Test(expected = TicketIdNotFoundException.class)
    public void updateTicketTest_Exception() {
        TicketDto ticketDto = new TicketDto(1, "speed_issue1", "Speed is fluctuating", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        ticketController.updateTicket(1, ticketDto);
    }

    @Test
    public void addTicketTest() {
        TicketDto ticketDto = new TicketDto(1, "speed_issue", "Speed is low", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        ResponseEntity<TicketDto> expected = new ResponseEntity<>(new TicketDto(1, "speed_issue", "Speed is low", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null), HttpStatus.OK);
        Assert.assertNotNull(ticketController.addTicket(ticketDto));
    }
}
