package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.HallService;

import javax.servlet.http.HttpServletRequest;

public class DeleteHall implements Action {

    private final HallService hallService;

    public DeleteHall(HallService hallService){
        this.hallService = hallService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.deleteHall(request, hallService);
    }
}
