package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class ViewUsers implements Action {

    UserService userService;

    public ViewUsers(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.viewUsers(request, userService);
    }
}
