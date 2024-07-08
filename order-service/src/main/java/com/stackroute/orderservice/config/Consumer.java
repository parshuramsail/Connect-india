package com.stackroute.orderservice.config;

import com.stackroute.orderservice.dto.PlanDto;


import com.stackroute.orderservice.service.impl.CustomerOrderServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Consumer {

    @Autowired
    private CustomerOrderServiceImpl customerOrderService;



    @RabbitListener(queues = "product_queue")
    public void getPlanFromRabbitMq(PlanDto planDto) {
        PlanDto planDto1 = new PlanDto();
        planDto1.setPid(planDto.getPid());
        planDto1.setPlanName(planDto.getPlanName());
        planDto1.setDuration(planDto.getDuration());
        planDto1.setOffers(planDto.getOffers());
        planDto1.setInstallation(planDto.getInstallation());
        planDto1.setPrice(planDto.getPrice());
        planDto1.setBuyerEmail(planDto.getBuyerEmail());


       customerOrderService.addOrder(planDto1);


    }


}
