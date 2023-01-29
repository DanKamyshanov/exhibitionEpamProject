package com.epam.exhibitions.database.DAO;

public interface DAOFactory {
    ExhibitionsDAO getExhibitionsDAO();
    HallsDAO getHallsDAO();
//    TicketsDAO getTicketsDAO();
    UsersDAO getUsersDAO();
}
