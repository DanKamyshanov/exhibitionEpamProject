package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddHall implements Action {

    private final HallService hallService;

    public AddHall(HallService hallService){
        this.hallService = hallService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.addHall(request, hallService);
    }
}
