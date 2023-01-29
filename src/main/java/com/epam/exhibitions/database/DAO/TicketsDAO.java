package com.epam.exhibitions.database.DAO;

import com.epam.exhibitions.database.Entities.Ticket;

import java.util.List;

public interface TicketsDAO {
    Ticket getById(int id);
    List<Ticket> getUserTickets(String login);
    void addTicket(Ticket ticket);
    void deleteTicket(int exhibition_id);
}
