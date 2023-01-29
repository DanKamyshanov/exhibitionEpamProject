package com.epam.exhibitions.controller;

import com.epam.exhibitions.controller.action.Action;
import com.epam.exhibitions.controller.action.ActionManager;
import com.epam.exhibitions.database.Entities.Enums.Roles;
import com.epam.exhibitions.database.Entities.User;
import com.epam.exhibitions.services.UserService;
import org.apache.log4j.Logger;

import javax.imageio.IIOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class Servlet extends HttpServlet {

    @Override
    public void init(){}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        if(session.isNew()){
            session.setAttribute("user", null);
        }

        String page = "index.jsp";
        String actionName = request.getParameter("action");

        if(actionName == null){
            request.getRequestDispatcher(page).forward(request, response);
        } else{
            Action action = ActionManager.getAction(actionName);
            page = ActionManager.executeAction(action, request);

            if(page.contains("redirect")){
                response.sendRedirect(page.replace("redirect:", ""));
            } else{
                request.getRequestDispatcher(page).forward(request, response);
            }
        }
    }
}
