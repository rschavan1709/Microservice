package com.neosoft.user.service.Service;

import com.neosoft.user.service.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
    void delete(String userId);
    User update(User user,String userId);
}
