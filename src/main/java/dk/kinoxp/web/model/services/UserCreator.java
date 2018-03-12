package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.User;

public class UserCreator {

    public UserCreator() {
    }

    public User createUser(String username, String password) {
        return new User(username, password);
    }

    public User createUser(){
        return new User();
    }
}
