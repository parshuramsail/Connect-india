package com.stackroute.userservice;

import com.stackroute.userservice.dto.AddressDto;
import com.stackroute.userservice.dto.UserDto;
import com.stackroute.userservice.modal.Address;
import com.stackroute.userservice.modal.User;
import com.stackroute.userservice.modal.UserRole;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceApplicationTests {
@Autowired
private UserService userService;
@MockBean
private UserRepository userRepository;


	/**
	 * Tests get all User Details
	 */
	@Test
public 	void testGetAllUserDetails() {
		List<User>userList=new ArrayList<>();

		Address address1= new Address( 10, "kulhariya complex", "boring road", "gandhi maidan", "stadium", "delhi", "delhi", 657890);
		Address address2= new Address( 12, "Motipur complex", "High Court  road", "G.M Road", "vennala", "kochi", "kerala", 335647);
		userList.add(new User("rakesh@gmail.com", "Rakesh Kumar", "Mg@12345", "9876543210", "89345654321", UserRole.BUYER,  address1));
		userList.add(new User( "ratnesh@gmail.com", "Ratnesh Kumar", "Sg@800297", "9456234516", "82354567890", UserRole.SELLER,  address2));

		when(userRepository.findAll()).thenReturn(userList);
		assertEquals(2, userService.fetchAllUsers().size());

	}
	/**
	 * Tests Add User Details or Register User
	 */
	@Test
	public void testadduserDetails(){
		Address address=new Address( 10, "kulhariya complex", "boring road", "gandhi maidan", "stadium", "chapra", "bihar", 841222);

		User user=new User( "rakesh@gmail.com", "Rakesh Kumar", "12345", "9504857518", "8294610768", UserRole.SELLER,  address);
		UserDto userDto = userService.convertToDto(user);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user.getEmailId(), userDto.getEmailId());
	}

	/**
	 * Tests Update User Details by User Email Id
	 */
	@Test
	public void testUpdateUserDetailsByEmailId(){
		String emailId= "rakesh@gmail.con";

		AddressDto address =new AddressDto( 12, "Ramdat bhawan", "mahi river", "north village", "near by pond", "chhapra", "bihar", 841222);
		Optional<UserDto>userDto=Optional.of(new UserDto("rakesh@gmail.com","Rakesh kumar","rakeshkumar10@gmail.com","9504857518","8294610768",UserRole.SELLER,address));

		User user=userService.convertToEntity(userDto.get());
		when(userRepository.findByEmailId(emailId)).thenReturn(Optional.ofNullable(user));
		assertNotNull(userService.fetchUserByEmailId(emailId));


	}
/**
 * Tests delete user details bu user  email id
 */
@Test
public void testDeleteUserDetailsByEmailId(){

AddressDto addressDto=new AddressDto(123,"Ramdatbhawan","mahi river","pond","near by pond","Chhapra","Bihar",841222);
Optional<UserDto>userDto=Optional.of(new UserDto("rakesh@gmail.com","Rakesh kumar","rakeshkumar10@gmail.com","9504857518","8294610768",UserRole.SELLER,addressDto));
User user=userService.convertToEntity(userDto.get());

when(userRepository.findByEmailId("rakesh@gmail.com")).thenReturn(Optional.ofNullable(user));
	userService.deleteUserByEmailId(user.getEmailId());

}
	/**
	 * Tests get User Details by user  email Id
	 */
	@Test
	public void testGetUserDetailsByEmailId() {
		String emailId = "manisha@gmail.com";

		AddressDto  address= new AddressDto( 10, "Ramdat bhawan", "mahi river", "near by pond", "pond", "Chhapra", "Bihar", 841222);
		Optional<UserDto> userDto= Optional.of(new UserDto( "rakesh@gmail.com", "Rakesh Kumar", "12345", "8294610768", "9504857518", UserRole.BUYER,  address));

		User user = userService.convertToEntity(userDto.get());

		when(userRepository.findByEmailId(emailId)).thenReturn(Optional.ofNullable(user));
		assertNotNull(userService.fetchUserByEmailId(emailId));
	}


}
