package com.mukul.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukul.demo.model.User;
import com.mukul.demo.service.UserService;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private final List<User> users = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    private User ragcrix;
    private User yigit;

    @Before
    public void setup() {
        ragcrix = new User();
        ragcrix.setId("aBc123");
        ragcrix.setName("ragcrix");
        ragcrix.setPlace("Bengaluru");

        yigit = new User();
        yigit.setId("dEf345");
        yigit.setName("yigit");
        yigit.setPlace("Hyderabad");

        users.add(yigit);

    }

    @Test
    public void givenStudents_whenGetAllStudents_thenReturnJsonArray() throws Exception {
        given(userService.getAllUsers()).willReturn(Arrays.asList(ragcrix));

        mvc.perform(get("/api/v1/demo/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()))
//                .andExpect(jsonPath("$", hasSize(1)));
                .andExpect(jsonPath("$[0].name", is(ragcrix.getName())));
    }

//    @Test
//    public void updateCustomer_succescenario() throws Exception {
////        given(userService.createUser(yigit)).willReturn(yigit);
//       when(userService.createUser(yigit)).thenReturn(yigit);
//
//        mvc.perform(post("/api/v1/demo/users/")
//                .header("Content-Type", "application/json")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
////                .andExpect(status().isBadRequest());
////                .andExpect(content().json(yigit.getId()+yigit.getName()+yigit.getPlace()));
//    }

}
