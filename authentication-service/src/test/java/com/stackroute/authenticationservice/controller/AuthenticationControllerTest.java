package com.stackroute.authenticationservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.model.JwtRequest;
import com.stackroute.authenticationservice.model.ResetPassword;
import com.stackroute.authenticationservice.service.UserService;
import com.stackroute.authenticationservice.utility.JwtUtility;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtility jwtUtility;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AuthenticationController#allAllUsers()}
     */
//    @Test
//    void testAllAllUsers() throws Exception {
//        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users");
//        MockMvcBuilders.standaloneSetup(authenticationController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
//    }

    /**
     * Method under test: {@link AuthenticationController#auth(JwtRequest)}
     */
    @Test
    void testAuth() throws Exception {
        when(jwtUtility.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(userService.loadUserByUsername((String) any()))
                .thenReturn(new User("shashikant.1", "shashikant.1", new ArrayList<>()));
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setEmailId("42");
        jwtRequest.setPassword("shashikant.1");
        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<JwtResponse><token>ABC123</token></JwtResponse>"));
    }

    /**
     * Method under test: {@link AuthenticationController#findUser(String)}
     */
//    @Test
//    void testFindUser() throws Exception {
//        when(userService.findUserByMailId((String) any())).thenReturn(true);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/findUser/{emailId}", "42");
//        MockMvcBuilders.standaloneSetup(authenticationController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
//                .andExpect(MockMvcResultMatchers.content().string("emailId is Valid"));
//    }

    /**
     * Method under test: {@link AuthenticationController#findUser(String)}
     */
//    @Test
//    void testFindUser2() throws Exception {
//        when(userService.findUserByMailId((String) any())).thenReturn(false);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/findUser/{emailId}", "42");
//        MockMvcBuilders.standaloneSetup(authenticationController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    /**
     * Method under test: {@link AuthenticationController#updatePassword(String, ResetPassword)}
     */
    @Test
    void testUpdatePassword() throws Exception {
        when(userService.resetPassword((String) any(), (ResetPassword) any())).thenReturn("shashikant.1");

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setNewPassword("shashikant.1");
        resetPassword.setOldPassword("shashikant.1");
        String content = (new ObjectMapper()).writeValueAsString(resetPassword);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/addUser/{emailId}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("shashikant.1"));
    }
}

