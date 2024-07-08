package com.stackroute.orderservice.service.impl;
import java.util.Date;
import java.util.List;

import com.stackroute.orderservice.config.Producer;
import com.stackroute.orderservice.dto.PlanDto;
import com.stackroute.orderservice.entity.Plan;
import com.stackroute.orderservice.exception.OrderNotFoundException;
import com.stackroute.orderservice.service.SequenceGeneratorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.stackroute.orderservice.dto.CustomerOrderDto;
import com.stackroute.orderservice.entity.CustomerOrder;
import com.stackroute.orderservice.exception.ResourceNotFoundException;
import com.stackroute.orderservice.repository.CustomerOrderRepository;
import com.stackroute.orderservice.service.CustomerOrderService;



@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

@Autowired
private Producer producer;

@Autowired
private SequenceGeneratorService sequenceGeneratorService;
	Log log = LogFactory.getLog(CustomerOrderServiceImpl.class);
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	@Value("${resource.not.found.message}")
	private String resourceNotFoundMessage;




	/**
	 * @description : Fetches All Customers Orders from Database
	 * @return List<customerOrderDto> : List of CustomerOrder
	 */
	@Override
	public List<CustomerOrderDto> fetchAllOrders() {

		List<CustomerOrder> customerOrder = customerOrderRepository.findAll();
		if(customerOrder.isEmpty())
			throw new OrderNotFoundException("Order List is Empty");
		log.info("Retrived All orders from Database");


		return customerOrderRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	/**
	 * @description : Saves the CustomerOrder to Database
	 * @param  : CustomerOrder to be saved into database
	 * @return customerOrderDto : Saved CustomerOrder
	 */
	@Override
	public CustomerOrderDto addOrder(PlanDto planDto) {
		log.info("Adding order to database");

         CustomerOrderDto customerOrderDto = new CustomerOrderDto();
		 customerOrderDto.setOrderDate(new Date());

		 customerOrderDto.setPlanDto(planDto);
		CustomerOrderDto customerOrderDto1 = convertToDto(customerOrderRepository.save(convertToEntity(customerOrderDto)));

		log.info("Adding order to database before Producer");
		producer.sendMessageToRabbitMq(customerOrderDto1);
		return customerOrderDto1;
	}

	/**
	 * @description : Fetches the CustomerOrder by using his ID
	 * @param id : ID of the CustomerOrder to be fetched
	 * @return customerOrderDto : CustomerOrder matching the passed ID
	 * @throws ResourceNotFoundException : If CustomerOrder with given ID not found
	 */
	@Override
	public CustomerOrderDto fetchOrderById(Long id) {

		//CustomerOrder customerOrder = customerOrderRepository.findById(id)
		Optional<CustomerOrder> byId = customerOrderRepository.findById(id);
		if(byId.isEmpty())
		{
			log.error("Exception thrown in method getTicketById");
			throw new OrderNotFoundException(resourceNotFoundMessage);
		}
				//.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundMessage+ id));

		return convertToDto(byId.get());

		//return convertToDto(customerOrder);
	}

	/**
	 * @param id : ID of the CustomerOrder to be Deleted
	 * @return customerOrderDto : CustomerOrder matching the passed ID
	 * @throws ResourceNotFoundException : If CustomerOrder with given ID not found
	 * @description : Deletes the CustomerOrder by using his ID
	 */
	@Override
	public long deleteOrderById(Long id) {

		CustomerOrder customerOrder = customerOrderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundMessage+ id));
		customerOrderRepository.delete(customerOrder);

		return 1;
	}



	/**
	 * @description : Helper Method which converts Entity to DTO
	 * @param customerOrder : CustomerOrder Entity to be converted into CustomerOrder DTO
	 * @return customerOrderDto : CustomerOrder DTO
	 */
	private CustomerOrderDto convertToDto(CustomerOrder customerOrder) {

		PlanDto planDto=new ModelMapper().map(customerOrder.getPlan(), PlanDto.class);
		CustomerOrderDto customerOrderDto = new ModelMapper().map(customerOrder, CustomerOrderDto.class);

		customerOrderDto.setPlanDto(planDto);
		return customerOrderDto;

	}

	/**
	 * @description : Helper Method which converts DTO to Entity
	 * @param customerOrderDto : CustomerOrder DTO to be converted into CustomerOrder Entity
	 * @return person : CustomerOrder Entity
	 */
	private CustomerOrder convertToEntity(CustomerOrderDto customerOrderDto) {


		Plan plan=new ModelMapper().map(customerOrderDto.getPlanDto(), Plan.class);
		CustomerOrder customerOrder = new ModelMapper().map(customerOrderDto, CustomerOrder.class);
		customerOrder.setOrderId(sequenceGeneratorService.generateSequence(CustomerOrder.SEQUENCE_NAME));

		customerOrder.setPlan(plan);
		return customerOrder;
	}



}
