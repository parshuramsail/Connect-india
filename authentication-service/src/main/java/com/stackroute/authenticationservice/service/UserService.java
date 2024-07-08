package com.stackroute.authenticationservice.service;


import com.stackroute.authenticationservice.exception.BadCredentialsException;
import com.stackroute.authenticationservice.exception.CustomUsernameNotFoundException;
import com.stackroute.authenticationservice.exception.EmailNotFoundException;
import com.stackroute.authenticationservice.model.JwtUser;
import com.stackroute.authenticationservice.model.ResetPassword;
import com.stackroute.authenticationservice.repository.JwtUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private JwtUserRepo userRepo;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String emailId) throws EmailNotFoundException {
        // logic to get the user from database
        Optional<JwtUser> userOptional = userRepo.findByEmailId(emailId);
        if (userOptional.isPresent()) {
            JwtUser user = userOptional.get();
            List<SimpleGrantedAuthority> grantedAuthorities = Stream.of(user.getRoles()).map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
//                    new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoles().name()));

            return new User(user.getEmailId(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public JwtUser addUser(JwtUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("*****************()()(" + user);
        return userRepo.save(user);

    }
    public boolean findUserByMailId(String emailId) {
        Optional<JwtUser> userOptional = userRepo.findByEmailId(emailId);
        if (userOptional.isPresent()) {
            return true;
        } else {
            throw new CustomUsernameNotFoundException("User Not Found");
        }
    }

    // TODO add RabbitMQ here for email as well as for user service
    public String resetPassword(String emailId, ResetPassword resetPassword) {
        JwtUser user = userRepo.findByEmailId(emailId).get();
        String dbPassword = user.getPassword();
        if (passwordEncoder.matches(resetPassword.getOldPassword(),dbPassword))
        {
            user.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
            userRepo.save(user);
            return "password is successfully reset";

        } else {
            throw new CustomUsernameNotFoundException("Old password does not match");
        }


    }


    public List<JwtUser> getAllUsers() {
        return userRepo.findAll();
    }

    // For RabbitMq
    public void findUserByEmailIdAndPassword(String emailId, String password) throws BadCredentialsException {
        Optional<JwtUser> userOpt = userRepo.findByEmailIdAndPassword(emailId, password);
        if(userOpt.isEmpty()) {

            throw new BadCredentialsException("Invalid credentials");
        }

    }

}