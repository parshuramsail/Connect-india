package com.stackroute.orderservice;

import com.stackroute.orderservice.dto.CustomerOrderDto;
import com.stackroute.orderservice.dto.PlanDto;
import com.stackroute.orderservice.entity.CustomerOrder;

import com.stackroute.orderservice.entity.Plan;
import com.stackroute.orderservice.repository.CustomerOrderRepository;
import com.stackroute.orderservice.service.impl.CustomerOrderServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.stackroute.orderservice.enums.Installation.CHARGEABLE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerOrderServiceImplTest {


    @MockBean
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    CustomerOrderServiceImpl customerOrderService;

    private CustomerOrder customerOrder;

    private CustomerOrderDto customerOrderDto;



    @BeforeEach
    public void setup(){


        Plan plan = new Plan(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");
        PlanDto planDto = new PlanDto(1L,"bonaza",1700d,"1 month","no",CHARGEABLE,"connectindia@gmail.com");

        customerOrder = new CustomerOrder(1L,999d,new Date(),plan,"connectindia@gmail.com");
        customerOrderDto = new CustomerOrderDto(1L,999d,new Date(),planDto,"connectindia@gmail.com");
    }


    @Test
    public void fetchOrderByIdTest()
    {

        when(customerOrderRepository.findById(customerOrder.getOrderId())).thenReturn(Optional.of(customerOrder));
        assertThat(customerOrderService.fetchOrderById(customerOrderDto.getOrderId()).equals(customerOrder.getOrderId()));
    }


    @Test
    public void fetchAllOrdersTest()
    {

        when(customerOrderRepository.findAll()).thenReturn(Stream.of(customerOrder).collect(Collectors.toList()));

        List<CustomerOrderDto> expected = (Stream.of(customerOrderDto).collect(Collectors.toList()));

        Assert.assertEquals(expected.toString(),customerOrderService.fetchAllOrders().toString());


    }


    @Test
    public void deleteOrderByIdTest()
    {


        when(customerOrderRepository.findById(customerOrder.getOrderId())).thenReturn(Optional.of(customerOrder));
        Mockito.doNothing().when(customerOrderRepository).deleteById(customerOrder.getOrderId());
        Assertions.assertEquals(1, customerOrderService.deleteOrderById(1L));


    }

    @Test
    void contextLoads() {
    }


}


