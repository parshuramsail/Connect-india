package com.stackroute.slotservice.config;

import com.stackroute.slotservice.dto.CustomerOrderDto;
import com.stackroute.slotservice.service.SlotServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {

    @Autowired
     SlotServiceImpl slotService;

    List<CustomerOrderDto> customerOrderDtoList = new ArrayList<>();

    @RabbitListener(queues = "order_queue")
    public void getPlanFromRabbitMq(CustomerOrderDto customerOrderDto) {
        CustomerOrderDto customerOrderDto1 = new CustomerOrderDto();
        customerOrderDto1.setOrderId(customerOrderDto.getOrderId());
        customerOrderDto1.setOrderDate(customerOrderDto.getOrderDate());

        customerOrderDto1.setBuyerEmail(customerOrderDto.getBuyerEmail());
        customerOrderDto1.setPrice(customerOrderDto.getPrice());

        customerOrderDtoList.add(customerOrderDto1);

    }

    public List<CustomerOrderDto> getAllOrders(){

        return customerOrderDtoList;
    }
}
