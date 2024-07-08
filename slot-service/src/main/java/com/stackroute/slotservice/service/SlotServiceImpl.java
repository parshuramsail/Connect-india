package com.stackroute.slotservice.service;

import com.stackroute.slotservice.config.Consumer;
import com.stackroute.slotservice.config.Producer;
import com.stackroute.slotservice.dto.CustomerOrderDto;
import com.stackroute.slotservice.dto.SlotDto;
import com.stackroute.slotservice.exception.IDNotFoundException;
import com.stackroute.slotservice.modal.CustomerOrder;
import com.stackroute.slotservice.modal.Slot;
import com.stackroute.slotservice.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    Consumer consumer;
    @Autowired
    Producer producer;
    @Value("${id.not.found.message}")
    private String notFoundMessage;


    @Override
    public SlotDto addSlot(SlotDto slotDto) {

        slotDto.setSlotId(sequenceGeneratorService.generateSequence(Slot.SEQUENCE_NAME));
        System.out.println(consumer.getAllOrders());
        SlotDto slotDto1 =new SlotDto();
       for (CustomerOrderDto customerOrderDto: consumer.getAllOrders()){

           if (slotDto.getBuyerEmail().equals(customerOrderDto.getBuyerEmail())){

                    slotDto.setCustomerOrderDto(customerOrderDto);

            slotDto1= convertToDto(slotRepository.save(convertToEntity(slotDto)));

            producer.sendMessageToRabbitMq(slotDto1);

           }
       }

        return slotDto1;
    }

    @Override
    public  void  getorderdetails(CustomerOrderDto customerOrderDto) {
        SlotDto slotDto=new SlotDto();
        slotDto.setCustomerOrderDto(customerOrderDto);
        addSlot(slotDto);
    }


    @Override
    public List<SlotDto> fetchAllSlots() {
        return slotRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public SlotDto fetchSlotById(Long slotId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new IDNotFoundException( notFoundMessage+ slotId));
        return convertToDto(slot);
    }

    @Override
    public SlotDto updateSlotById(Long slotId, SlotDto slotDto) {
        slotRepository.findById(slotId)
                .orElseThrow(() -> new IDNotFoundException(notFoundMessage+ slotId));
        return convertToDto(slotRepository.save(convertToEntity(slotDto)));
    }

    @Override
    public void deleteSlotById(Long slotId) {
        slotRepository.findById(slotId)
                .orElseThrow(() -> new IDNotFoundException(notFoundMessage+ slotId));
        slotRepository.deleteById(slotId);
    }

    private SlotDto convertToDto(Slot slot) {
        CustomerOrderDto customerOrderDto =new CustomerOrderDto();
        SlotDto slotDto = new SlotDto();


        slotDto.setSlotId(slot.getSlotId());
        slotDto.setDate(slot.getDate());
        slotDto.setTimeAvailability(slot.getTimeAvailability());
        slotDto.setStartTime(slot.getStartTime());
        slotDto.setEndTime(slot.getEndTime());
        slotDto.setAvailability(slot.getAvailability());
        slotDto.setBuyerEmail(slot.getBuyerEmail());

        customerOrderDto.setOrderId(slot.getCustomerOrder().getOrderId());
        customerOrderDto.setOrderDate(slot.getCustomerOrder().getOrderDate());
        customerOrderDto.setBuyerEmail(slot.getCustomerOrder().getBuyerEmail());
        customerOrderDto.setPrice(slot.getCustomerOrder().getPrice());
        slotDto.setCustomerOrderDto(customerOrderDto);

        return slotDto;
    }

    private Slot convertToEntity(SlotDto slotDto) {
        Slot slot = new Slot();
        slot.setSlotId(slotDto.getSlotId());
        slot.setDate(slotDto.getDate());
        slot.setTimeAvailability(slotDto.getTimeAvailability());
        slot.setStartTime(slotDto.getStartTime());
        slot.setEndTime(slotDto.getEndTime());
        slot.setAvailability(slotDto.getAvailability());
        slot.setBuyerEmail(slotDto.getBuyerEmail());

        CustomerOrder customerOrder =new CustomerOrder();
        customerOrder.setOrderId(slotDto.getCustomerOrderDto().getOrderId());
        customerOrder.setOrderDate(slotDto.getCustomerOrderDto().getOrderDate());
        customerOrder.setBuyerEmail(slotDto.getCustomerOrderDto().getBuyerEmail());
        customerOrder.setPrice(slotDto.getCustomerOrderDto().getPrice());

        slot.setCustomerOrder(customerOrder);
        return slot;
    }

}


