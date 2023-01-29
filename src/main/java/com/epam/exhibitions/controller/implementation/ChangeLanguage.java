package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguage implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.changeLanguage(request);
    }
}
