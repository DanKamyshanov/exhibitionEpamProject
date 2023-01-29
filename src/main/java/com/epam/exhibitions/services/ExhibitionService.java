package com.epam.exhibitions.services;

import com.epam.exhibitions.database.DAO.DAOFactory;
import com.epam.exhibitions.database.DAO.ExhibitionsDAO;
import com.epam.exhibitions.database.DAOFactoryMySQLImpl;
import com.epam.exhibitions.database.Entities.Exhibition;
import com.epam.exhibitions.database.Entities.Hall;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExhibitionService {
    private final DAOFactory dao = new DAOFactoryMySQLImpl();
    ExhibitionsDAO exhibitionsDAO = dao.getExhibitionsDAO();

    public void addExhibition(Exhibition exhibition){
        exhibitionsDAO.addExhibition(exhibition);
    }

    public void addExhibition(String name, String theme, String description, LocalDate dateFrom, LocalDate dateTo,
                              LocalTime workingFrom, LocalTime workingTo, double price){
        Exhibition exhibition = new Exhibition(name, theme, description, dateFrom, dateTo, workingFrom, workingTo, price);
        exhibitionsDAO.addExhibition(exhibition);
    }

    public List<Exhibition> getAllExhibitions(){
        return exhibitionsDAO.getAll();
    }

    public Optional<Exhibition> getById(int id){
        return Optional.ofNullable(exhibitionsDAO.getById(id));
    }

    public List<Hall> getHalls(int id){
        return exhibitionsDAO.getHalls(id);
    }

    public void setHalls(int id, String[] ids){
        exhibitionsDAO.setHalls(id, ids);
    }

    public void updateExhibition(Exhibition exhibition){
        exhibitionsDAO.updateExhibition(exhibition);
    }

    public void deleteExhibition(int id){
        exhibitionsDAO.deleteExhibition(id);
    }

    public int getNumberOfTicketsSold(int id){
        return exhibitionsDAO.getNumberOfTicketsSold(id);
    }

    public Optional<Exhibition> getByName(String name){
        return Optional.ofNullable(exhibitionsDAO.getByName(name));
    }

    public List<Exhibition> getByDate(int page, LocalDate date){
        return exhibitionsDAO.getByDate(page, date);
    }

    public List<Exhibition> getBySortType(int page, String type){
        if(type.equals("default") || type.equals("theme") || type.equals("ticket_price")){
            return exhibitionsDAO.getBySortType(page, type);
        } else{
            return null;
        }
    }

    public int getNumber(){
        return exhibitionsDAO.getNumberOfExhibitions();
    }

    public Map<String, Integer> getStatisticsById(int id){
        return exhibitionsDAO.getStatisticsById(id);
    }

    public int getNumberByDate(LocalDate date){
        return exhibitionsDAO.getNumberOfExhibitionsSortByDate(date);
    }

}
