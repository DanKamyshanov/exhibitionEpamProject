package com.epam.exhibitions.database;

import com.epam.exhibitions.database.DAO.*;

public class DAOFactoryMySQLImpl implements DAOFactory {

    @Override
    public ExhibitionsDAO getExhibitionsDAO() {
        return new ExhibitionsDAOmySQLImpl();
    }

    @Override
    public HallsDAO getHallsDAO() {
        return new HallsDAOmySQLImpl();
    }

//    @Override
//    public TicketsDAO getTicketsDAO() {
//        return new TicketsDAOmySQLImpl();
//    }

    @Override
    public UsersDAO getUsersDAO() {
        return new UsersDAOmySQLImpl();
    }
}
