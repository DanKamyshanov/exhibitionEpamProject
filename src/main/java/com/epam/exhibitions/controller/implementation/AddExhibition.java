package com.epam.exhibitions.controller.implementation;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionExecutor;
import com.epam.exhibitions.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddExhibition implements Action {

    private final ExhibitionService exhibitionService;

    public AddExhibition(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return ActionExecutor.addExhibition(request, exhibitionService);
    }
}
