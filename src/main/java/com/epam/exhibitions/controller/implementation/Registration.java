package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration implements Action {

    private final UserService userService;

    public Registration(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request){
        return ActionExecutor.register(request, userService);
    }
}
