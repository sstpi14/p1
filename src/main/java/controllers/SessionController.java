package controllers;

import services.UserService;

public class SessionController {
    private UserService userService;

    public SessionController(){
        this.userService = new UserService();
    }



}
