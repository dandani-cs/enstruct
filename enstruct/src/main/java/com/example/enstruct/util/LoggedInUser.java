package com.example.enstruct.util;

import com.example.enstruct.model.User;

public class AuthManager {
    private static AuthManager instance = null;
    private User curr_logged_in_user    = null;

    private AuthManager() { }

    public static AuthManager getInstance()
    {
        if(instance == null)
            instance = new AuthManager();
        return instance;
    }

    public User getLoggedInUser()
    {
        return curr_logged_in_user;
    }

    public void loginUser(User user)
    {
        curr_logged_in_user = user;
    }
}
