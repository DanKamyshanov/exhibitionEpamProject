package com.epam.exhibitions.controller.action;

import com.epam.exhibitions.controller.implementation.*;
import com.epam.exhibitions.services.ExhibitionService;
import com.epam.exhibitions.services.HallService;
import com.epam.exhibitions.services.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.TreeMap;

public class ActionManager {

    private static Map<String, Action> actions = new TreeMap<>();

    private static final UserService userService = new UserService();
    private static final ExhibitionService exhibitionService = new ExhibitionService();
    private static final HallService hallService = new HallService();

    private ActionManager(){}

    static {
        actions.put("login", new Login(userService));
        actions.put("logout", new Logout());
        actions.put("register", new Registration(userService));
        actions.put("deleteUser", new DeleteUser(userService));
        actions.put("updateProfile", new UpdateProfile(userService));
        actions.put("viewUsers", new ViewUsers(userService));
        actions.put("addExhibition", new AddExhibition(exhibitionService));
        actions.put("deleteExhibition", new DeleteExhibition(exhibitionService));
        actions.put("viewExhibitions", new ViewExhibitions(exhibitionService));
        actions.put("addHall", new AddHall(hallService));
        actions.put("viewHalls", new ViewHalls(hallService));
        actions.put("deleteHall", new DeleteHall(hallService));
        actions.put("purchaseTicket", new PurchaseTicket(userService));
        actions.put("changeLanguage", new ChangeLanguage());
        actions.put("viewStatistics", new ViewStatistics(exhibitionService));
    }

    public static Action getAction(String action){
        return actions.get(action);
    }

    public static String executeAction(Action action, HttpServletRequest request){
        return action.execute(request);
    }
}
