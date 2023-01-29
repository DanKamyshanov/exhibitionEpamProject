package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Action {

    private final UserService userService;

    public Login(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.login(request, userService);
    }
}
