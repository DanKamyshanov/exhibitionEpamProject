package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewHalls implements Action {

    private final HallService service;

    public ViewHalls(HallService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.viewHalls(request, service);
    }
}
