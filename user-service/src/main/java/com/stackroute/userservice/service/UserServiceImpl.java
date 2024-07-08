package com.stackroute.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.dto.AddressDto;
import com.stackroute.userservice.dto.UserDto;
import com.stackroute.userservice.dto.UserUpdateRequest;
import com.stackroute.userservice.exception.UserAllReadyExits;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.modal.Address;
import com.stackroute.userservice.modal.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Service
public class UserServiceImpl implements UserService{

    Log log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private Producer producer;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     *
     * @Description : this method is used to fetch  All user details
     * @Param:
     * @returns : It returns  All the user details  which is present in the db
     * @throws :
     * @Created by : Rakesh Kumar
     * @createdDate : 22nd November 2022
     *
     */
    @Override
    public List<UserDto> fetchAllUsers() {

        log.info("Fetch All the users details from user table");
        return  userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /*
     *
     * @Description : this method is used to register  the  user
     * @Param: It takes user details as a signature
     * @returns : It returns  successful message with the user details
     * @throws :
     * @Created by : Rakesh Kumar
     * @createdDate : 22nd November 2022
     *
     */
    @Override
    public UserDto addUser(UserDto userDto) {

        log.info(" User Registration successfully");
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findByEmailId(userDto.getEmailId()).isPresent()) {
            throw new UserAllReadyExits(AppConstant.USER_ALREADY_EXISTS);
        }
        userRepository.insert(user);
        UserDto user_dto = convertToDto(user);
        producer.sendMessageToRabbitMq(userDto);
        return user_dto;
    }

    /*
     *
     * @Description : this method is used to fetch  existing user details by user email id
     * @Param: It takes user email id as a parameter
     * @returns : It returns user details  according to user email id
     * @throws : It throws Resource not found exception if the user details is not available in the table
     * @Created by : Rakesh Kumar
     * @createdDate : 22nd November 2022
     *
     */
    @Override
    public UserDto fetchUserByEmailId(String emailId) {

        log.info(" Getting user details by user email id ");
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() ->
                        {
                            log.warn("Email Id is not found in DB");
                            return new UserNotFoundException(AppConstant.USER_NOT_FOUND_MESSAGE + emailId );
                        }
                );
        log.info("Got user details successfully");
        return convertToDto(user);
    }

    /*
     *
     * @Description : this method is used to update existing user details
     * @Param: It takes product email id  and the modified data as a parameter
     * @returns : It returns modified user  details
     * @throws : It throws Resource not found exception if the user details is not available in the table
     * @Created by : Rakesh Kumar
     * @createdDate : 22nd November 2022
     *
     */
    @Override
    public UserDto updateUserByEmailId(String emailId, UserUpdateRequest userUpdateRequest) {
        log.info(" Update  user details by user email id ");
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new UserNotFoundException(AppConstant.USER_NOT_FOUND_MESSAGE + emailId));
        Address address = user.getAddress();
        address.setDoorNo(userUpdateRequest.getAddressDto().getDoorNo());
        address.setBuildingName(userUpdateRequest.getAddressDto().getBuildingName());
        address.setStreet(userUpdateRequest.getAddressDto().getStreet());
        address.setArea(userUpdateRequest.getAddressDto().getArea());
        address.setLandMark(userUpdateRequest.getAddressDto().getLandMark());
        address.setCity(userUpdateRequest.getAddressDto().getCity());
        address.setState(userUpdateRequest.getAddressDto().getState());
        address.setPinCode(userUpdateRequest.getAddressDto().getPinCode());

        user.setUserName(userUpdateRequest.getUserName());
        user.setContactNo( userUpdateRequest.getContactNo());
        user.setAlternateNo(userUpdateRequest.getAlternateNo());

        log.info(" Save updated  user details in db ");
        return convertToDto(userRepository.save(user));
    }
    /*
     *
     * @Description : this method is used to delete existing user details
     * @Param: It takes product email id as a parameter
     * @returns : It returns deleted message and deleted the existing user details which users want to delete
     * @throws : It throws Resource not found exception if the user details is not available in the table
     * @Created by : Rakesh Kumar
     * @createdDate : 22nd November 2022
     *
     */

    @Override
    public void deleteUserByEmailId(String emailId) {

        log.info("Getting user details bu user email id");
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new UserNotFoundException(AppConstant.USER_NOT_FOUND_MESSAGE + emailId));
        log.info("Delete User Details Successfully");
        userRepository.delete(user);
    }

    /*
     *convert dto to entity
     */
    @Override
    public User convertToEntity(UserDto userDto) {

        log.info("Convert DTO  to Entity ");
        Address address = new ModelMapper().map(userDto.getAddressDto(), Address.class);
        User user = new ModelMapper().map(userDto, User.class);
        user.setAddress(address);
        return user;
    }

    /*
     *convert entity to dto
     */
    @Override
    public UserDto convertToDto(User user) {

        log.info("Convert Entity to Dto");
        AddressDto addressDto = new ModelMapper().map(user.getAddress(), AddressDto.class);
        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        userDto.setAddressDto(addressDto);
        return userDto;
    }
}





