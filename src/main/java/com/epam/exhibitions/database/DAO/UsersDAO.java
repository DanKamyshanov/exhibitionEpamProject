package com.epam.exhibitions.database.DAO;

import com.epam.exhibitions.database.Entities.User;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

public interface UsersDAO {
    void setProfilePicture(InputStream image);
    User getById(int id);
    User getByLogin(String login);
    User getByCredentials(String login, String password);
    String getRole(String login);
    String getPassword(String login);
    List<User> getAll();
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User extractUser(ResultSet rs);
    boolean loginExists(String login);
    boolean emailExists(String email);
    boolean phoneExists(String phone_number);

    void purchaseTicket(int userId, int exhibitionId);
}
