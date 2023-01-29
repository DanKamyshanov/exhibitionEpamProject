package com.epam.exhibitions.services;

import com.epam.exhibitions.database.DAO.DAOFactory;
import com.epam.exhibitions.database.DAO.HallsDAO;
import com.epam.exhibitions.database.DAOFactoryMySQLImpl;
import com.epam.exhibitions.database.Entities.Hall;

import java.util.List;
import java.util.Optional;

public class HallService {
    private final DAOFactory dao = new DAOFactoryMySQLImpl();
    HallsDAO hallsDAO = dao.getHallsDAO();

    public void addHall(Hall hall){
        hallsDAO.addHall(hall);
    }

    public void addHall(String name, String description){
        Hall hall = new Hall(name, description);
        hallsDAO.addHall(hall);
    }

    public List<Hall> getAllHalls(){
        return hallsDAO.getAll();
    }

    public Hall getById(int id){
        return hallsDAO.getById(id);
    }

    public Optional<Hall> getByName(String name){
        return Optional.ofNullable(hallsDAO.getByName(name));
    }

    public void updateHall(Hall hall){
        hallsDAO.updateHall(hall);
    }

    public void deleteHall(int id){
        hallsDAO.deleteHall(id);
    }
}
