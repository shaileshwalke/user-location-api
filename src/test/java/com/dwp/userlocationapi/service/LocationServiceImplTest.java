package com.dwp.userlocationapi.service;

import com.dwp.userlocationapi.config.ApplicationProps;
import com.dwp.userlocationapi.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LocationServiceImplTest {

    private LocationServiceImpl locationService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApplicationProps applicationProps;

    @Before
    public void setup() {
        Map<String, ApplicationProps.Coordinate> coordinateMap = new HashMap<>();
        ApplicationProps.Coordinate coordinate = new ApplicationProps.Coordinate();
        coordinate.setLatitude(51.50853);
        coordinate.setLongitude(-0.12574);
        coordinateMap.put("london", coordinate);
        when(applicationProps.getCityList()).thenReturn(coordinateMap);
        locationService = new LocationServiceImpl(restTemplate, "",applicationProps);
    }

    @Test
    public void testFindUserByCity() throws Exception  {
        List<User> users = getUsers();
        final ResponseEntity<List<User>> response = new ResponseEntity<>(users, HttpStatus.OK);
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class))).thenReturn(response);

        List<User> userList = locationService.findUsersByCity("london");

        Assert.assertEquals(true, userList.isEmpty());
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