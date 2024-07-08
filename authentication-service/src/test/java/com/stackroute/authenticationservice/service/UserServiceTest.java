package com.stackroute.authenticationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.authenticationservice.exception.BadCredentialsException;
import com.stackroute.authenticationservice.exception.CustomUsernameNotFoundException;
import com.stackroute.authenticationservice.exception.EmailNotFoundException;
import com.stackroute.authenticationservice.model.JwtUser;
import com.stackroute.authenticationservice.model.ResetPassword;
import com.stackroute.authenticationservice.model.UserRole;
import com.stackroute.authenticationservice.repository.JwtUserRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private JwtUserRepo jwtUserRepo;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws EmailNotFoundException {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userService.loadUserByUsername("42");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("42", actualLoadUserByUsernameResult.getUsername());
        assertEquals("shashikant.1", actualLoadUserByUsernameResult.getPassword());
        verify(jwtUserRepo).findByEmailId((String) any());
    }

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws EmailNotFoundException {
        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getEmailId()).thenReturn("42");
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        when(jwtUser.getRoles()).thenReturn(UserRole.SELLER);
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userService.loadUserByUsername("42");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("42", actualLoadUserByUsernameResult.getUsername());
        assertEquals("shashikant.1", actualLoadUserByUsernameResult.getPassword());
        verify(jwtUserRepo).findByEmailId((String) any());
        verify(jwtUser).getRoles();
        verify(jwtUser).getEmailId();
        verify(jwtUser).getPassword();
        verify(jwtUser).setEmailId((String) any());
        verify(jwtUser).setId((String) any());
        verify(jwtUser).setPassword((String) any());
        verify(jwtUser).setRoles((UserRole) any());
    }

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws EmailNotFoundException {
        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getEmailId()).thenThrow(new EmailNotFoundException("An error occurred"));
        when(jwtUser.getPassword()).thenThrow(new EmailNotFoundException("An error occurred"));
        when(jwtUser.getRoles()).thenReturn(UserRole.SELLER);
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        assertThrows(EmailNotFoundException.class, () -> userService.loadUserByUsername("42"));
        verify(jwtUserRepo).findByEmailId((String) any());
        verify(jwtUser).getRoles();
        verify(jwtUser).getEmailId();
        verify(jwtUser).setEmailId((String) any());
        verify(jwtUser).setId((String) any());
        verify(jwtUser).setPassword((String) any());
        verify(jwtUser).setRoles((UserRole) any());
    }

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername4() throws EmailNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Cannot pass null or empty values to constructor
        //       at com.stackroute.authenticationservice.service.UserService.loadUserByUsername(UserService.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getEmailId()).thenReturn("");
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        when(jwtUser.getRoles()).thenReturn(UserRole.SELLER);
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        userService.loadUserByUsername("42");
    }

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws EmailNotFoundException {
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(Optional.empty());
        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getEmailId()).thenReturn("42");
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        when(jwtUser.getRoles()).thenReturn(UserRole.SELLER);
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("42"));
        verify(jwtUserRepo).findByEmailId((String) any());
        verify(jwtUser).setEmailId((String) any());
        verify(jwtUser).setId((String) any());
        verify(jwtUser).setPassword((String) any());
        verify(jwtUser).setRoles((UserRole) any());
    }

    /**
     * Method under test: {@link UserService#addUser(JwtUser)}
     */
    @Test
    void testAddUser() {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        when(jwtUserRepo.save((JwtUser) any())).thenReturn(jwtUser);

        JwtUser jwtUser1 = new JwtUser();
        jwtUser1.setEmailId("42");
        jwtUser1.setId("42");
        jwtUser1.setPassword("shashikant.1");
        jwtUser1.setRoles(UserRole.SELLER);
        assertSame(jwtUser, userService.addUser(jwtUser1));
        verify(jwtUserRepo).save((JwtUser) any());
    }

    /**
     * Method under test: {@link UserService#addUser(JwtUser)}
     */
    @Test
    void testAddUser2() {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        when(jwtUserRepo.save((JwtUser) any())).thenReturn(jwtUser);
        JwtUser jwtUser1 = mock(JwtUser.class);
        when(jwtUser1.getPassword()).thenReturn("shashikant.1");
        doNothing().when(jwtUser1).setEmailId((String) any());
        doNothing().when(jwtUser1).setId((String) any());
        doNothing().when(jwtUser1).setPassword((String) any());
        doNothing().when(jwtUser1).setRoles((UserRole) any());
        jwtUser1.setEmailId("42");
        jwtUser1.setId("42");
        jwtUser1.setPassword("shashikant.1");
        jwtUser1.setRoles(UserRole.SELLER);
        assertSame(jwtUser, userService.addUser(jwtUser1));
        verify(jwtUserRepo).save((JwtUser) any());
        verify(jwtUser1).getPassword();
        verify(jwtUser1).setEmailId((String) any());
        verify(jwtUser1).setId((String) any());
        verify(jwtUser1, atLeast(1)).setPassword((String) any());
        verify(jwtUser1).setRoles((UserRole) any());
    }

    /**
     * Method under test: {@link UserService#findUserByMailId(String)}
     */
    @Test
    void testFindUserByMailId() {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        assertTrue(userService.findUserByMailId("42"));
        verify(jwtUserRepo).findByEmailId((String) any());
    }

    /**
     * Method under test: {@link UserService#findUserByMailId(String)}
     */
    @Test
    void testFindUserByMailId2() {
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(Optional.empty());
        assertThrows(CustomUsernameNotFoundException.class, () -> userService.findUserByMailId("42"));
        verify(jwtUserRepo).findByEmailId((String) any());
    }

    /**
     * Method under test: {@link UserService#findUserByMailId(String)}
     */
    @Test
    void testFindUserByMailId3() {
        when(jwtUserRepo.findByEmailId((String) any())).thenThrow(new UsernameNotFoundException("User Not Found"));
        assertThrows(UsernameNotFoundException.class, () -> userService.findUserByMailId("42"));
        verify(jwtUserRepo).findByEmailId((String) any());
    }

    /**
     * Method under test: {@link UserService#resetPassword(String, ResetPassword)}
     */
    @Test
    void testResetPassword() {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        assertThrows(CustomUsernameNotFoundException.class,
                () -> userService.resetPassword("42", new ResetPassword("shashikant.1", "shashikant.1")));
        verify(jwtUserRepo).findByEmailId((String) any());
    }

    /**
     * Method under test: {@link UserService#resetPassword(String, ResetPassword)}
     */
    @Test
    void testResetPassword2() {
        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        assertThrows(CustomUsernameNotFoundException.class,
                () -> userService.resetPassword("42", new ResetPassword("shashikant.1", "shashikant.1")));
        verify(jwtUserRepo).findByEmailId((String) any());
        verify(jwtUser).getPassword();
        verify(jwtUser).setEmailId((String) any());
        verify(jwtUser).setId((String) any());
        verify(jwtUser).setPassword((String) any());
        verify(jwtUser).setRoles((UserRole) any());
    }

    /**
     * Method under test: {@link UserService#resetPassword(String, ResetPassword)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.stackroute.authenticationservice.service.UserService.resetPassword(UserService.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(Optional.empty());
        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        userService.resetPassword("42", new ResetPassword("shashikant.1", "shashikant.1"));
    }

    /**
     * Method under test: {@link UserService#resetPassword(String, ResetPassword)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: rawPassword cannot be null
        //       at com.stackroute.authenticationservice.service.UserService.resetPassword(UserService.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        userService.resetPassword("42", new ResetPassword());
    }

    /**
     * Method under test: {@link UserService#resetPassword(String, ResetPassword)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.stackroute.authenticationservice.service.UserService.resetPassword(UserService.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        JwtUser jwtUser = mock(JwtUser.class);
        when(jwtUser.getPassword()).thenReturn("shashikant.1");
        doNothing().when(jwtUser).setEmailId((String) any());
        doNothing().when(jwtUser).setId((String) any());
        doNothing().when(jwtUser).setPassword((String) any());
        doNothing().when(jwtUser).setRoles((UserRole) any());
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailId((String) any())).thenReturn(ofResult);
        userService.resetPassword("42", null);
    }

    /**
     * Method under test: {@link UserService#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        ArrayList<JwtUser> jwtUserList = new ArrayList<>();
        when(jwtUserRepo.findAll()).thenReturn(jwtUserList);
        List<JwtUser> actualAllUsers = userService.getAllUsers();
        assertSame(jwtUserList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(jwtUserRepo).findAll();
    }

    /**
     * Method under test: {@link UserService#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() {
        when(jwtUserRepo.findAll()).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getAllUsers());
        verify(jwtUserRepo).findAll();
    }

    /**
     * Method under test: {@link UserService#findUserByEmailIdAndPassword(String, String)}
     */
    @Test
    void testFindUserByEmailIdAndPassword() throws BadCredentialsException {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmailId("42");
        jwtUser.setId("42");
        jwtUser.setPassword("shashikant.1");
        jwtUser.setRoles(UserRole.SELLER);
        Optional<JwtUser> ofResult = Optional.of(jwtUser);
        when(jwtUserRepo.findByEmailIdAndPassword((String) any(), (String) any())).thenReturn(ofResult);
        userService.findUserByEmailIdAndPassword("42", "shashikant.1");
        verify(jwtUserRepo).findByEmailIdAndPassword((String) any(), (String) any());
        assertTrue(userService.getAllUsers().isEmpty());
    }

    /**
     * Method under test: {@link UserService#findUserByEmailIdAndPassword(String, String)}
     */
    @Test
    void testFindUserByEmailIdAndPassword2() throws BadCredentialsException {
        when(jwtUserRepo.findByEmailIdAndPassword((String) any(), (String) any())).thenReturn(Optional.empty());
        assertThrows(BadCredentialsException.class, () -> userService.findUserByEmailIdAndPassword("42", "shashikant.1"));
        verify(jwtUserRepo).findByEmailIdAndPassword((String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserService#findUserByEmailIdAndPassword(String, String)}
     */
    @Test
    void testFindUserByEmailIdAndPassword3() throws BadCredentialsException {
        when(jwtUserRepo.findByEmailIdAndPassword((String) any(), (String) any()))
                .thenThrow(new UsernameNotFoundException("Invalid credentials"));
        assertThrows(UsernameNotFoundException.class, () -> userService.findUserByEmailIdAndPassword("42", "shashikant.1"));
        verify(jwtUserRepo).findByEmailIdAndPassword((String) any(), (String) any());
    }
}

