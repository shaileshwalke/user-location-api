package com.dwp.userlocationapi.service;

import com.dwp.userlocationapi.config.ApplicationProps;
import com.dwp.userlocationapi.exception.ServiceNotAvailableException;
import com.dwp.userlocationapi.model.User;
import com.dwp.userlocationapi.util.DistanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);
    private final RestTemplate restTemplate;
    private final String userServiceEndpointUrl;
    private final ApplicationProps applicationProps;
    private static final String USERS_URL = "/users";

    private static final int RADIUS = 50;

    @Inject
    public LocationServiceImpl(final RestTemplate restTemplate,
                                @Value("${userServiceEndpointUrl}") final String userServiceEndpointUrl,
                               final ApplicationProps applicationProps) {
        this.userServiceEndpointUrl = userServiceEndpointUrl;
        this.restTemplate = restTemplate;
        this.applicationProps = applicationProps;
    }

    @Override
    public List<User> findUsersByCity(String city) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<Void> requestEntity = new HttpEntity<>(null, httpHeaders);
        List<User> users = null;
        try {
            final ResponseEntity<List<User>> response = restTemplate.exchange(
                    userServiceEndpointUrl + USERS_URL,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<List<User>>() {}
                    );
            users = response.getBody();

        }
        catch (Exception clientException){
            LOGGER.error(clientException.getMessage());
            throw new ServiceNotAvailableException("Service not available at the moment. Please try later");
        }
        if (null!=users)
            return users.stream().
                filter(user-> DistanceUtil.calculatorDistance(
                        applicationProps.getCityList().get(city).getLatitude(),
                        applicationProps.getCityList().get(city).getLongitude(),
                        user.getLatitude(),
                        user.getLongitude())<=RADIUS).map(User.class::cast).collect(Collectors.toCollection(LinkedList::new));
        return users;
    }
}
