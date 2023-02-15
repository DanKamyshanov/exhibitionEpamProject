package com.epam.exhibitions.controller.action;

import com.epam.exhibitions.database.Entities.Enums.Roles;
import com.epam.exhibitions.database.Entities.Exhibition;
import com.epam.exhibitions.database.Entities.Hall;
import com.epam.exhibitions.database.Entities.User;
import com.epam.exhibitions.services.ExhibitionService;
import com.epam.exhibitions.services.HallService;
import com.epam.exhibitions.services.UserService;
import com.epam.exhibitions.utils.VerifyReCaptcha;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class ActionExecutor {

    public static String register(HttpServletRequest request, UserService userService){
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("reEnterPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone_number");

        if(login == null || login.equals("")){
            request.getSession().setAttribute("error", "loginRegistration");
            return "registration.jsp";
        }
        if(password == null || password.equals("")){
            request.getSession().setAttribute("error", "passwordRegistration");
            return "registration.jsp";
        }
        if(email == null || email.equals("")){
            request.getSession().setAttribute("error", "emailRegistration");
            return "registration.jsp";
        }
        if(!password.equals(passwordConfirm)){
            request.getSession().setAttribute("error", "passwordMismatch");
            return "registration.jsp";
        }
        if(userService.getUserByLogin(login).isPresent()){
            request.setAttribute("error", true);
            return "registration.jsp";
        }

        userService.addUser(firstName, lastName, login, password, email, phone);
        if(userService.getUserByLogin(login).isPresent()){
            User user = new User(firstName, lastName, login, password, email, phone, Roles.USER);
            request.getSession().setAttribute("user", user);
        }
        return "redirect:/home";
    }

    public static String login(HttpServletRequest request, UserService userService){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        boolean verify = VerifyReCaptcha.verify(gRecaptchaResponse);
        if(!verify){
            request.setAttribute("captchaError", "captcha.error");
            return "login.jsp";
        }

        if(login == null || login.equals("")){
            request.getSession().setAttribute("error", "loginLogin");
            return "login.jsp";
        }
        if(password == null || password.equals("")){
            request.getSession().setAttribute("error", "passwordLogin");
            return "login.jsp";
        }

        Optional<User> user = userService.getUserByCred(login, password);
        if(!user.isPresent()){
            request.getSession().setAttribute("error", "loginError");
            return "login.jsp";
        }

        request.getSession().setAttribute("user", user.get());
        return "redirect:/home";
    }

    public static String logout(HttpServletRequest request){
        request.getSession().setAttribute("user", null);
        return "index.jsp";
    }

    public static String deleteUser(HttpServletRequest request, UserService userService){
        int id = Integer.parseInt(request.getParameter("userToDeleteID"));
        userService.deleteUser(id);
        return "home?action=viewUsers&page=1";
    }

    public static String addExhibition(HttpServletRequest request, ExhibitionService exhibitionService){
        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String description = request.getParameter("description");
        String ticketPriceString = request.getParameter("ticket_price");
        String[] hallsIds = request.getParameterValues("occupiedHalls");
        LocalDate dateFrom = null;
        LocalDate dateTo = null;
        LocalDate currentDate = new Date(System.currentTimeMillis()).toLocalDate();
        LocalTime workingFrom = null;
        LocalTime workingTo = null;
        double ticketPrice = 0.00;

        if(exhibitionService.getByName(name).isPresent()){
            request.getSession().setAttribute("error", "ExhibitionAlreadyExists");
            return "addExhibition.jsp";
        }
        if(!request.getParameter("date_from").equals("")){
            dateFrom = Date.valueOf(request.getParameter("date_from")).toLocalDate();
        }
        if(!request.getParameter("date_to").equals("")){
            dateTo = Date.valueOf(request.getParameter("date_to")).toLocalDate();
        }
        if(!request.getParameter("working_from").equals("")){
            workingFrom = Time.valueOf(request.getParameter("working_from") + ":00").toLocalTime();
        }
        if(!request.getParameter("working_to").equals("")){
            workingTo = Time.valueOf(request.getParameter("working_to") + ":00").toLocalTime();
        }
        if(!ticketPriceString.equals("")){
            ticketPrice = Double.parseDouble(ticketPriceString);
        }

        if(name == null || name.equals("")){
            request.getSession().setAttribute("error", "invalidName");
            return "addExhibition.jsp";
        }
        if(theme == null || theme.equals("")){
            request.getSession().setAttribute("error", "invalidTheme");
            return "addExhibition.jsp";
        }
        if(description == null || description.equals("")){
            request.getSession().setAttribute("error", "invalidDescription");
            return "addExhibition.jsp";
        }
        if(dateFrom == null) {
            request.getSession().setAttribute("error", "invalidStartDate");
            return "addExhibition.jsp";
        }
        if(dateTo == null){
            request.getSession().setAttribute("error", "invalidEndDate");
            return "addExhibition.jsp";
        }
        if(workingFrom == null){
            request.getSession().setAttribute("error", "invalidStartHours");
            return "addExhibition.jsp";
        }
        if(workingTo == null){
            request.getSession().setAttribute("error", "invalidEndHours");
            return "addExhibition.jsp";
        }
        if (ticketPrice <= 0) {
            request.getSession().setAttribute("error", "invalidPrice");
            return "addExhibition.jsp";
        }

        if(dateFrom.isAfter(dateTo) || dateFrom.isBefore(currentDate)){
            request.getSession().setAttribute("error", "invalidDatePeriod");
            return "addExhibition.jsp";
        }
        if(workingFrom.isAfter(workingTo)){
            request.getSession().setAttribute("error", "invalidTimePeriod");
            return "addExhibition.jsp";
        }
        if(hallsIds == null || hallsIds.length == 0){
            request.getSession().setAttribute("error", "hallError");
        }

        exhibitionService.addExhibition(name, theme, description, dateFrom, dateTo, workingFrom, workingTo, ticketPrice);
        int id = exhibitionService.getByName(name).get().getExhibitionId();
        exhibitionService.setHalls(id, hallsIds);
        return "redirect:/index.jsp";
    }

    public static String deleteExhibition(HttpServletRequest request, ExhibitionService exhibitionService){
        int id = Integer.parseInt(request.getParameter("exhibitionToDeleteId"));
        exhibitionService.deleteExhibition(id);
        return "home?action=viewExhibitions&page=1&sortBy=default";
    }

    public static String deleteHall(HttpServletRequest request, HallService hallService){
        int id = Integer.parseInt(request.getParameter("hallToDeleteId"));
        hallService.deleteHall(id);
        return "index.jsp";
    }

    public static String viewExhibitions(HttpServletRequest request, ExhibitionService exhibitionService){
        List<Exhibition> exhibitions;
        String sortBy = request.getParameter("sortBy");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int numberOfPages;
        int numberOfExhibitions;
        LocalDate selectedDate = null;
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

        if(request.getParameter("selectedDate") != null && !request.getParameter("selectedDate").equals("")){
            selectedDate = Date.valueOf(request.getParameter("selectedDate")).toLocalDate();
        }
        if(sortBy.equals("date") && selectedDate == null){
            request.getSession().setAttribute("error", "invalidDate");
            return "index.jsp";
        }

        List<Exhibition> exhibitionList = exhibitionService.getAllExhibitions();
        for(Exhibition exhib : exhibitionList){
            if(exhib.getDate_to().isBefore(currentDate)){
                exhibitionService.deleteExhibition(exhib.getExhibitionId());
            }
        }

        if(sortBy.equals("date")){
            exhibitions = exhibitionService.getByDate(currentPage, selectedDate);
            numberOfExhibitions = exhibitionService.getNumberByDate(selectedDate);
        } else{
            exhibitions = exhibitionService.getBySortType(currentPage, sortBy);
            numberOfExhibitions = exhibitionService.getNumber();
        }

        if(selectedDate != null && request.getSession().getAttribute("sortByDate") == null){
            request.getSession().setAttribute("sortByDate", exhibitionService.getByDate(currentPage, selectedDate));
        }

        if(numberOfExhibitions % 4 == 0){
            numberOfPages = numberOfExhibitions / 4;
        } else{
            numberOfPages = (numberOfExhibitions / 4) + 1;
        }
        HttpSession session = request.getSession();
        session.setAttribute("exhibitions", exhibitions);
        session.setAttribute("numberOfPages", numberOfPages);
        session.setAttribute("currentPage", currentPage);
        return "exhibitions.jsp";
    }

    public static String viewUsers(HttpServletRequest request, UserService service){
        List<User> userList = service.getAllUsers();
        List<User> usersPerPage = new ArrayList<>();
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int pos = (currentPage - 1) * 5;
        int numberOfPages;
        int numberOfUsers = userList.size();

        for(int i = pos; i < Math.min(pos + 5, userList.size()); ++i){
            usersPerPage.add(userList.get(i));
        }

        if(numberOfUsers % 5 == 0){
            numberOfPages = numberOfUsers / 5;
        } else{
            numberOfPages = (numberOfUsers / 5) + 1;
        }

        HttpSession session = request.getSession();
        session.setAttribute("users", usersPerPage);
        session.setAttribute("usersNumberOfPages", numberOfPages);
        session.setAttribute("usersCurrentPage", currentPage);

        return "manageUsers.jsp";
    }

    public static String updateProfile(HttpServletRequest request, UserService service){
        User user = (User) request.getSession().getAttribute("user");
        user.setFirst_name(request.getParameter("first_name"));
        user.setLast_name(request.getParameter("last_name"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setPhone_number(request.getParameter("phone_number"));
        service.updateUser(user);
        return "profile.jsp";
    }

    public static String viewHalls(HttpServletRequest request, HallService hallService){
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int pos = (currentPage - 1) * 4;
        int numberOfPages;
        List<Hall> hallList = hallService.getAllHalls();
        List<Hall> hallsPerPage = new ArrayList<>();

        for(int i = pos; i < Math.min(pos + 4, hallList.size()); ++i){
            hallsPerPage.add(hallList.get(i));
        }
        int numberOfHalls = hallList.size();

        if(numberOfHalls % 4 == 0){
            numberOfPages = numberOfHalls / 4;
        } else{
            numberOfPages = (numberOfHalls / 4) + 1;
        }

        HttpSession session = request.getSession();
        session.setAttribute("halls", hallsPerPage);
        session.setAttribute("HallsPages", numberOfPages);
        session.setAttribute("HallsCurrentPage", currentPage);

        return "halls.jsp";
    }

    public static String addHall(HttpServletRequest request, HallService hallService){
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if(name == null || name.equals("")){
            request.getSession().setAttribute("error", "invalidName");
            return "/addHall.jsp";
        }
        if(description == null || description.equals("")){
            request.getSession().setAttribute("error", "invalidDescription");
            return "/addHall.jsp";
        }

        if(hallService.getByName(name).isPresent()){
            request.getSession().setAttribute("error", "hallAlreadyExists");
            return "/addHall.jsp";
        }

        hallService.addHall(name, description);
        List<Hall> halls = hallService.getAllHalls();
        request.getSession().setAttribute("halls", halls);
        return "redirect:/halls.jsp";
    }

    public static String purchaseTicket(HttpServletRequest request, UserService userService){
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        int exhibitionId = Integer.parseInt(request.getParameter("exhibition_id"));
        userService.purchaseTicket(userId, exhibitionId);

        final String name = "exhibitionsforepam";
        final String password = "gwtubicjzgphvkwu";
        String userEmail = user.getEmail();
        String login = user.getLogin();
        String exhibitionName = request.getParameter("exhibition_name");
        String workingFrom = request.getParameter("exhibition_time_from");
        String workingTo = request.getParameter("exhibition_time_to");
        String endDate = request.getParameter("end_date");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(name, password);
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("exhibitionsforepam@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Your purchase was successful!");
            message.setText("Dear " + login + ", you have successfully purchased tickets for " + exhibitionName + " exhibition! Please be advised, venues operate from " + workingFrom + " to " + workingTo + " until " + endDate + ". Plan your attendance accordingly. Best Regards!");
            Transport.send(message);
        } catch (MessagingException e){
            e.printStackTrace();
            System.out.println("Email was not sent.");
        }
        return "home?action=viewExhibitions&page=1&sortBy=default";
    }

    public static String changeLanguage(HttpServletRequest request){
        String lang = request.getParameter("language");
        request.getSession().setAttribute("language", lang);
        return "";
    }

    public static String viewStatistics(HttpServletRequest request, ExhibitionService service){
        Map<String, Integer> statistics;
        int exhibitionId = Integer.parseInt(request.getParameter("exhibition_id"));
        statistics = service.getStatisticsById(exhibitionId);

        request.getSession().setAttribute("statistics", statistics);

        return "statistics.jsp";
    }
}
