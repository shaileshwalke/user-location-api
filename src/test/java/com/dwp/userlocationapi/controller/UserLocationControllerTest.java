package com.dwp.userlocationapi.controller;

import com.dwp.userlocationapi.model.User;
import com.dwp.userlocationapi.service.LocationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserLocationControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UserLocationController userLocationController;

    @Mock
    private LocationService locationService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userLocationController).build();
    }

    @Test
    public void findUsersByCityTest() throws Exception {
        List<User> users = getUsers();
        Mockito.when(locationService.findUsersByCity(ArgumentMatchers.anyString())).thenReturn(users);
        final ObjectMapper objectMapper= new ObjectMapper();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/city/london/users"))
                .andExpect(handler().methodName("findUsersByCity"))
                .andExpect(handler().handlerType(UserLocationController.class))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }

    private List<User> getUsers() throws java.io.IOException {
        List<User> users;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(inputStream, new TypeReference<List<User>>() {
        });
        return users;
    }

}