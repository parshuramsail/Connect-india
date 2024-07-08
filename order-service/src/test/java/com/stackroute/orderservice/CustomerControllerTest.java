package com.stackroute.orderservice;

import com.stackroute.orderservice.controller.CustomerOrderController;
import com.stackroute.orderservice.dto.CustomerOrderDto;
import com.stackroute.orderservice.dto.PlanDto;
import com.stackroute.orderservice.entity.CustomerOrder;
import com.stackroute.orderservice.entity.Plan;
import com.stackroute.orderservice.repository.CustomerOrderRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import static com.stackroute.orderservice.enums.Installation.CHARGEABLE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class CustomerControllerTest {

    @MockBean
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    CustomerOrderController customerOrderController;

    @Value("${delete.message}")
    private String deletionMessage;

    @Test
    public void fetchOrderByIdTest()
    {



        Plan plan = new Plan(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");
        PlanDto planDto = new PlanDto(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");

       CustomerOrder customerOrder = new CustomerOrder(1L,999d,new Date(),plan,"connectindia@gmail.com");
        CustomerOrderDto customerOrderDto = new CustomerOrderDto(1L,999d,new Date(),planDto,"connectindia@gmail.com");
        Mockito.when(customerOrderRepository.findById(customerOrder.getOrderId())).thenReturn(Optional.of(customerOrder));
        assertThat(customerOrderController.fetchOrderById(customerOrderDto.getOrderId()).equals(customerOrder.getOrderId()));


    }


    @Test
    public void fetchAllOrdersTest()
    {

        Plan plan = new Plan(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");
        PlanDto planDto = new PlanDto(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");

        CustomerOrder customerOrder = new CustomerOrder(1L,999d,new Date(),plan,"connectindia@gmail.com");
        CustomerOrderDto customerOrderDto = new CustomerOrderDto(1L,999d,new Date(),planDto,"connectindia@gmail.com");
        Mockito.when(customerOrderRepository.findAll()).thenReturn(Stream.of(customerOrder).collect(toList()));

        ResponseEntity<List<CustomerOrderDto>> expected = new ResponseEntity<>(Stream.of(customerOrderDto).collect(Collectors.toList()), HttpStatus.OK);

        Assertions.assertEquals(expected.toString(), customerOrderController.fetchAllOrders().toString());


    }


    @Test
    public void deleteOrderByIdTest()
    {
        Plan plan = new Plan(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");
        CustomerOrder customerOrder = new CustomerOrder(1L,999d,new Date(),plan,"connectindia@gmail.com");
        Mockito.when(customerOrderRepository.findById(customerOrder.getOrderId())).thenReturn(Optional.of(customerOrder));
        Mockito.doNothing().when(customerOrderRepository).deleteById(customerOrder.getOrderId());
        ResponseEntity<String> expected = new ResponseEntity<>(deletionMessage, HttpStatus.OK);
        Assertions.assertEquals(expected.toString(), customerOrderController.deleteOrderById(1L).toString());

    }




    @Test
    void contextLoads() {
    }


}


