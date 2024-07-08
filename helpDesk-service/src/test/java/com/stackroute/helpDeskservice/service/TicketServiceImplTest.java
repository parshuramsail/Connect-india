package com.stackroute.helpDeskservice.service;

import com.stackroute.helpDeskservice.dto.TicketDto;
import com.stackroute.helpDeskservice.enums.Priority;
import com.stackroute.helpDeskservice.enums.Status;
import com.stackroute.helpDeskservice.exception.NoTicketFoundException;
import com.stackroute.helpDeskservice.exception.TicketIdNotFoundException;
import com.stackroute.helpDeskservice.modal.Ticket;
import com.stackroute.helpDeskservice.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceImplTest {

    @MockBean
    TicketRepository ticketRepository;

    @Autowired
    TicketServiceImpl ticketService;

    @Test
    public void getTicketsByReporterEmailIdTest() {
        Mockito.when(ticketRepository.findByReporterEmailId("virat@123")).thenReturn(Stream.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "virat@123", "sanyog@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList()));
        List<TicketDto> expected = Stream.of(new TicketDto(1, "speed_issue", "Speed is low", new Date(), "virat@123", "sanyog@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList());
        Assert.assertEquals(expected.size(),ticketService.getByReporterEmailId("virat@123").size());
    }

    @Test(expected = NoTicketFoundException.class)
    public void getTicketsByReporterEmailIdTest_Exception() {
        Mockito.when(ticketRepository.findByReporterEmailId("virat@123")).thenReturn(new ArrayList<>());
        ticketService.getByReporterEmailId("virat@123");
    }

    @Test
    public void getTicketsByAssigneeEmailIdTest() {
        Mockito.when(ticketRepository.findByAssigneeEmailId("virat@123")).thenReturn(Stream.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "virat@123", "sanyog@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList()));
        List<TicketDto> expected = Stream.of(new TicketDto(1, "speed_issue", "Speed is low", new Date(), "virat@123", "sanyog@123", Priority.LOW, Status.OPEN, null)).collect(Collectors.toList());
        Assert.assertEquals(expected.size(),ticketService.getByAssigneeEmailId("virat@123").size());
    }

    @Test(expected = NoTicketFoundException.class)
    public void getTicketsByAssigneeEmailIdTest_Exception() {
        Mockito.when(ticketRepository.findByAssigneeEmailId("virat@123")).thenReturn(new ArrayList<>());
        ticketService.getByAssigneeEmailId("virat@123");
    }

    @Test
    public void getTicketsByIdTest() {
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)));
        TicketDto expected = new TicketDto(1, "speed_issue", "Speed is low", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        Assert.assertEquals(expected.toString(),ticketService.getTicketById(1).toString());
    }

    @Test(expected = TicketIdNotFoundException.class)
    public void getTicketsByIdTest_Exception() {
        Optional<Ticket> ticketOpt = Optional.empty();
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(ticketOpt);
        ticketService.getTicketById(1);
    }

    @Test(expected = TicketIdNotFoundException.class)
    public void deleteTicketsByIdTest_Exception() {
        Optional<Ticket> ticketOpt = Optional.empty();
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(ticketOpt);
        ticketService.deleteTicket(1);
    }

    @Test
    public void updateTicketsByIdTest() {
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Ticket(1, "speed_issue", "Speed is low", new Date(), "sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null)));
        TicketDto ticketDto = new TicketDto(1, "speed_issue1", "Speed is fluctuating", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        TicketDto expected = new TicketDto(1, "speed_issue1", "Speed is fluctuating", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        Assert.assertEquals(expected.toString(), ticketService.updateTicket(1, ticketDto).toString());
    }

    @Test(expected = TicketIdNotFoundException.class)
    public void updateTicketsByIdTest_Exception() {
        Optional<Ticket> ticketOpt = Optional.empty();
        Mockito.when(ticketRepository.findById(Mockito.anyLong())).thenReturn(ticketOpt);
        ticketService.updateTicket(1, null);
    }

    @Test
    public void addTicketsByIdTest() {
        TicketDto ticketDto = new TicketDto(1, "speed_issue1", "Speed is fluctuating", new Date(),"sanyog@123", "virat@123", Priority.LOW, Status.OPEN, null);
        Assert.assertNotNull(ticketService.addTicket(ticketDto));
    }
}
