package com.epam.exhibitions.database.DAO;

import com.epam.exhibitions.database.Entities.Hall;

import java.sql.ResultSet;
import java.util.List;

public interface HallsDAO {
    void addHall(Hall hall);
    List<Hall> getAll();
    Hall getByName(String name);
    Hall getById(int id);
    void updateHall(Hall hall);
    void deleteHall(int id);
    Hall extractHall(ResultSet rs);
}
