package edu.karazin.shop.service;

import edu.karazin.shop.model.User;

public interface UserService {

    void saveUser(User user);
    
    User validateUser(String email, String password);
}
