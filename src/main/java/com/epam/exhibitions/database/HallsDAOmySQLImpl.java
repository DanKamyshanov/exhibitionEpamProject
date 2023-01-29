package com.epam.exhibitions.database;

import com.epam.exhibitions.database.DAO.HallsDAO;
import com.epam.exhibitions.database.Entities.Hall;
import com.epam.exhibitions.database.Entities.Queries;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HallsDAOmySQLImpl implements HallsDAO {

    final static Logger logger = Logger.getLogger(HallsDAOmySQLImpl.class);

    @Override
    public void addHall(Hall hall) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.HALL_ADD);
            pstmt.setString(1, hall.getName());
            pstmt.setString(2, hall.getDescription());
            pstmt.executeUpdate();
            conn.commit();
            logger.info("Hall (id: " + hall.getId() + ") was added to the database.");
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall addition failed.");
        }
    }

    @Override
    public List<Hall> getAll() {
        List<Hall> halls = new ArrayList<>();
        try(Connection conn = DataSource.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.HALL_GET_ALL);
            while(rs.next()){
                Hall hall = extractHall(rs);
                halls.add(hall);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall list retrievement failed.");
        }
        return halls;
    }

    @Override
    public Hall getByName(String name) {
        Hall hall = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.HALL_GET_BY_NAME);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                hall = extractHall(rs);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall retrievement by name failed.");
        }
        return hall;
    }

    @Override
    public Hall getById(int id) {
        Hall hall = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(Queries.HALL_GET_BY_ID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                hall = extractHall(rs);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall retrievement by ID failed.");
        }
        return hall;
    }

    @Override
    public void updateHall(Hall hall) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.HALL_UPDATE);
            pstmt.setInt(1, hall.getId());
            pstmt.setString(2, hall.getName());
            pstmt.setString(3, hall.getDescription());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall update failed.");
        }
    }

    @Override
    public void deleteHall(int id) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.HALL_DELETE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
            logger.info("Hall (id " + id + ") was deleted from the database.");
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall deletion failed.");
        }
    }

    @Override
    public Hall extractHall(ResultSet rs) {
        Hall hall = null;
        try{
            hall = new Hall(rs.getString("name"), rs.getString("description"));
            hall.setId(rs.getInt("id"));
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Hall extraction failed.");
        }
        return hall;
    }
}
