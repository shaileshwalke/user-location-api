package com.dwp.userlocationapi.service;

import com.dwp.userlocationapi.model.User;

import java.util.List;

public interface LocationService {

    List<User> findUsersByCity(final String city);

}
