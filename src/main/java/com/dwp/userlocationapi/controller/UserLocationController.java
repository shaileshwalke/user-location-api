package com.dwp.userlocationapi.controller;

import com.dwp.userlocationapi.model.User;
import com.dwp.userlocationapi.service.LocationService;
import com.dwp.userlocationapi.validation.ValidCityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
public class UserLocationController {

    @Autowired
    LocationService locationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/city/{city}/users")
    public List<User> findUsersByCity(@PathVariable("city") @ValidCityConstraint @NotBlank String city) {
        return locationService.findUsersByCity(city);
    }

}
