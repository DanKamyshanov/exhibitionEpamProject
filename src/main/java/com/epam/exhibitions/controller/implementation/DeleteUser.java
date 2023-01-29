package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.database.Entities.User;
import com.epam.exhibitions.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser implements Action {

    private final UserService userService;

    public DeleteUser(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.deleteUser(request, userService);
    }
}
