package com.mukul.demo.service;

import com.mukul.demo.model.User;
import com.mukul.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user1;
    private User user2;

    private final String user1_id = "uid01";
    private final String user2_id = "uid02";
    private final String user1_name = "name01";
    private final String user2_name = "name02";
    private final String user1_place = "place01";
    private final String user2_place = "place02";

    private final List<User> users = new ArrayList<>();

    @Before
    public void setup() {
        user1 = new User();
        user1.setId(user1_id);
        user1.setName(user1_name);
        user1.setPlace(user1_place);

        user2 = new User();
        user2.setId(user2_id);
        user2.setName(user2_name);
        user2.setPlace(user2_place);

        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userRepository.findById(user1_id)).thenReturn(java.util.Optional.ofNullable(user1));
        Mockito.when(userRepository.findByName(user1_name)).thenReturn(Collections.singletonList(user1));
        Mockito.when(userRepository.findByPlace(user1_place)).thenReturn(Collections.singletonList(user1));
    }

    @Test
    public void testCreateUser_thenUserShouldBeAdded() {
        User newUser = userService.createUser(user1);

        assertNotNull(newUser);
        assertEquals(user1.getId(), newUser.getId());
    }

    @Test
    public void testGetAllUsers_thenUserListShouldBeReturned() {
        List<User> foundUsers = userService.getAllUsers();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
    }

    @Test
    public void testGetUserById_thenUserShouldBeReturned() {
        User foundUser = userService.getUserById(user1_id);

        assertNotNull(foundUser);
        assertEquals(user1_id, foundUser.getId());
    }

    @Test
    public void testGetUsersByName_thenUserListShouldBeReturned() {
        List<User> foundUsers = userService.getUsersByName(user1_name);

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(user1_name, foundUsers.get(0).getName());
    }

    @Test
    public void testGetUsersByPlace_thenUserListShouldBeReturned() {
        List<User> foundUsers = userService.getUsersByPlace(user1_place);

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(user1_place, foundUsers.get(0).getPlace());
    }

    @Test
    public void testUpdateUser_thenUserShouldBeUpdated() {
        User updatedUser = userService.updateUser(user1_id, user1);

        assertNotNull(updatedUser);
        assertEquals(user1_id, updatedUser.getId());
    }

    @Test
    public void testDeleteUser_thenUserShouldBeRemoved() {
        userService.deleteUser(user1_id);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(user1_id);
    }
}
