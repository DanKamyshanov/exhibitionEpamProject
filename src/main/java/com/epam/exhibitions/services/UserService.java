package com.epam.exhibitions.services;

import com.epam.exhibitions.database.DAO.DAOFactory;
import com.epam.exhibitions.database.DAO.UsersDAO;
import com.epam.exhibitions.database.DAOFactoryMySQLImpl;
import com.epam.exhibitions.database.Entities.Enums.Roles;
import com.epam.exhibitions.database.Entities.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserService {
    private final DAOFactory dao = new DAOFactoryMySQLImpl();
    UsersDAO usersDAO = dao.getUsersDAO();

    public void addUser(User user){
        usersDAO.addUser(user);
    }

    public void addUser(String firsName, String lastName, String login, String password, String email, String phone){
        User user = new User(firsName, lastName, login, password, email, phone, Roles.USER);
        usersDAO.addUser(user);
    }

    public Optional<User> getUserByLogin(String login){
        return Optional.ofNullable(usersDAO.getByLogin(login));
    }

    public Optional<User> getUserByCred(String login, String password){
        return Optional.ofNullable(usersDAO.getByCredentials(login, password));
    }

    public User getUserById(int id){
        return usersDAO.getById(id);
    }

    public List<User> getAllUsers(){
        return usersDAO.getAll();
    }

    public void updateUser(User user){
        usersDAO.updateUser(user);
    }

    public void deleteUser(int id){
        usersDAO.deleteUser(id);
    }

    public void setProfilePicture(InputStream image){
        usersDAO.setProfilePicture(image);
    }

    public void purchaseTicket(int userId, int exhibitionId){
        usersDAO.purchaseTicket(userId, exhibitionId);
    }
}
