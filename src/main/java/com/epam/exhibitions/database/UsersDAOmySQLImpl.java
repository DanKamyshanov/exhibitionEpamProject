package com.epam.exhibitions.database;

import com.epam.exhibitions.database.DAO.UsersDAO;
import com.epam.exhibitions.database.Entities.Enums.Roles;
import com.epam.exhibitions.database.Entities.Queries;
import com.epam.exhibitions.database.Entities.User;
import org.apache.log4j.Logger;


import javax.swing.text.html.parser.Entity;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersDAOmySQLImpl implements UsersDAO {

    final static Logger logger = Logger.getLogger(UsersDAOmySQLImpl.class);


    private static UsersDAOmySQLImpl instance;

    public static UsersDAOmySQLImpl getInstance(){
        if(instance == null){
            instance = new UsersDAOmySQLImpl();
        }
        return instance;
    }


    @Override
    public void setProfilePicture(InputStream image) {}

    @Override
    public User getById(int id) {
        User user = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_GET_BY_ID);
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if(rs.next()){
                user = extractUser(rs);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User extraction by ID failed.");
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        User user = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_GET_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                user = extractUser(rs);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User extraction by login failed.");
        }
        return user;
    }

    @Override
    public User getByCredentials(String login, String password) {
        User user = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_GET_BY_CREDENTIALS);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                user = extractUser(rs);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User extraction by credentials failed.");
        }
        return user;
    }

    @Override
    public String getRole(String login) {
        String role = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_GET_ROLE);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                role = rs.getString("role_name");
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User role extraction failed.");
        }
        return role;
    }

    @Override
    public String getPassword(String login) {
        String password = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_GET_PASSWORD);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                password = rs.getString("password");
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User password extraction failed.");
        }
        return password;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try(Connection conn = DataSource.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.USER_GET_ALL);
            while(rs.next()){
                userList.add(extractUser(rs));
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User list extraction failed.");
        }
        return userList;
    }

    @Override
    public void addUser(User user) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_ADD);
            pstmt.setString(1, user.getFirst_name());
            pstmt.setString(2,user.getLast_name());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhone_number());
            pstmt.setInt(7, user.getRole().ordinal());
            pstmt.executeUpdate();
            conn.commit();
            logger.info("User (login: " + user.getLogin() + ") was added to the database.");
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User addition failed.");
        }
    }

    @Override
    public void deleteUser(int id) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_DELETE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
            logger.info("User (id: " + id + ") was deleted from the database.");
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User deletion failed.");
        }
    }

    @Override
    public void updateUser(User user) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_UPDATE);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getFirst_name());
            pstmt.setString(3, user.getLast_name());
            pstmt.setString(4, user.getLogin());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getPhone_number());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User update failed.");
        }
    }

    @Override
    public User extractUser(ResultSet rs) {
        User user = null;
        try{
            user = new User(rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("login"), rs.getString("password"),
                    rs.getString("email"), rs.getString("phone_number"), Roles.values()[rs.getInt("role_id")]);
            int id = rs.getInt("id");
            user.setId(id);
        } catch (SQLException e){
            logger.error(e);
            System.out.println("User extraction failed.");
        }
        return user;
    }

    @Override
    public boolean loginExists(String login) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_VERIFY_LOGIN);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e){
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean emailExists(String email) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_VERIFY_EMAIL);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e){
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean phoneExists(String phone_number) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_VERIFY_PHONE);
            pstmt.setString(1, phone_number);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e){
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void purchaseTicket(int userId, int exhibitionId) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.USER_PURCHASE_TICKET);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, exhibitionId);
            pstmt.executeUpdate();
            conn.commit();
            logger.info("User (id: " + userId + ") has successfully purchased a ticket for exhibition (id: " + exhibitionId + ").");
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Purchase unsuccessful.");
        }
    }
}
