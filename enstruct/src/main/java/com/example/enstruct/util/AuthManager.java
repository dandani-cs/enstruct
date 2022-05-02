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

    public void logoutUser() {
        curr_logged_in_user = null;
    }

    public String labelUser(boolean isTeacher) {
        if(AuthManager.getInstance().getLoggedInUser() == null) {
            return "redirect:/login";
        } else {
            boolean has_invalid_priv = true;

            if(isTeacher == true && AuthManager.getInstance().getLoggedInUser().getTeacher() == true ||
                    isTeacher == false && AuthManager.getInstance().getLoggedInUser().getTeacher() == false) {
                has_invalid_priv = false;
            } else {
                has_invalid_priv = true;
            }

            if (has_invalid_priv) {
                //logoutUser();
                if(curr_logged_in_user.getTeacher()) {
                    return "redirect:/instructor";
                } else {
                    return "redirect:/student";
                }
            }
        }
        return "continue";
    }
}
