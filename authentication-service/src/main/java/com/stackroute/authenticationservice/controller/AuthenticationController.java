package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.exception.CustomUsernameNotFoundException;
import com.stackroute.authenticationservice.model.JwtRequest;
import com.stackroute.authenticationservice.model.JwtResponse;
import com.stackroute.authenticationservice.model.ResetPassword;
import com.stackroute.authenticationservice.service.UserService;
import com.stackroute.authenticationservice.utility.JwtUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    Log log = LogFactory.getLog(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody JwtRequest jwtRequest) throws com.stackroute.authenticationservice.exception.BadCredentialsException, CustomUsernameNotFoundException {

        try {
            log.info("Inside auth controller");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmailId(),
                            jwtRequest.getPassword()
                    )
            );
            log.info("No exception");

        } catch (BadCredentialsException e) {
            log.info("In Exception");
            throw new com.stackroute.authenticationservice.exception.BadCredentialsException("Bad Credentials");

        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmailId());
        String token = jwtUtility.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.ACCEPTED);
    }

    /**
     * Google auth Implementation
     * @param - emailId
     * @return
     * @author - maheshwari
     */
//
//    @GetMapping("/login")
//    public String login() {
//        return "Welcome to connect-India";
//    }
//
//    @GetMapping("/test")
//    public String test() {
//        return "No Google login";
//    }

    /**
     * Testing api's
     * @param emailId
     * @return
     * @author - maheshwari
     */

//    @GetMapping("/seller")
//    @PreAuthorize("hasAuthority('SELLER')")
//    public ResponseEntity<String> sellerApi() {
//        return new ResponseEntity<>("Seller API", HttpStatus.OK);
//    }
//
//    @GetMapping("/buyer")
//    @PreAuthorize("hasAuthority('BUYER')")
//    public ResponseEntity<String> buyerApi() {
//        return new ResponseEntity<>("Buyer API", HttpStatus.OK);
//    }

//    @PostMapping("/addUser")
//    public ResponseEntity<JwtUser> addUser(@RequestBody JwtUser user) throws com.stackroute.authenticationservice.exception.BadCredentialsException {
//        if (user == null) {
//            throw new com.stackroute.authenticationservice.exception.BadCredentialsException("User is not vaild");
//        }
//        JwtUser jwtUser = userService.addUser(user);
//        return new ResponseEntity<>(jwtUser, HttpStatus.CREATED);
//    }

    //    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

//    @GetMapping("/findUser/{emailId}")
//    public ResponseEntity<String> findUser(@PathVariable String emailId) {
//        log.info("In find user controller");
//        boolean userExist = userService.findUserByMailId(emailId);
//
//        if (userExist) {
//            return new ResponseEntity<>("emailId is Valid", HttpStatus.OK);
//        }
//        log.info("Invalid mail Id");
//        return null;
//    }

    @PutMapping("/addUser/{emailId}")
    public ResponseEntity<String> resetPassword(@PathVariable String emailId, @RequestBody ResetPassword resetPassword) {
        log.info("in controller");
        log.info(resetPassword.toString());

        return new ResponseEntity<>(userService.resetPassword(emailId, resetPassword), HttpStatus.OK);

    }

//    @GetMapping("/users")
//    public ResponseEntity<List<JwtUser>> allAllUsers() {
//        log.info("All Users controller");
//        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
//    }


}
