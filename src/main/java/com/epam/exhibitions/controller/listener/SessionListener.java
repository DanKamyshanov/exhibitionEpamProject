package com.epam.exhibitions.controller.listener;

import com.epam.exhibitions.database.Entities.Enums.Roles;
import com.epam.exhibitions.database.Entities.Hall;
import com.epam.exhibitions.database.Entities.User;
import com.epam.exhibitions.services.HallService;
import com.epam.exhibitions.services.UserService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event){
        HallService hallService = new HallService();
        List<Hall> halls = hallService.getAllHalls();
        event.getSession().setAttribute("halls", halls);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSessionListener.super.sessionDestroyed(event);
    }
}
