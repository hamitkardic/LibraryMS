package com.library.repository;

import com.library.model.User;

public class UserDAO {

    private static final User ADMIN =
            new User("admin", "1234");

    public boolean login(String username, String password) {
        return ADMIN.getUsername().equals(username)
                && ADMIN.getPassword().equals(password);
    }
}