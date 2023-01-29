package com.epam.exhibitions.database.Entities;

import com.epam.exhibitions.services.ExhibitionService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Exhibition implements Serializable {

    private int id;
    private final String name;
    private final String theme;
    private final String description;
    private final LocalDate date_from;
    private final LocalDate date_to;
    private final LocalTime working_from;
    private final LocalTime working_to;
    private final double ticket_price;

    public Exhibition(String name, String theme, String description, LocalDate date_from, LocalDate date_to, LocalTime working_from, LocalTime working_to, double ticket_price){
        this.name = name;
        this.theme = theme;
        this.description = description;
        this.date_from = date_from;
        this.date_to = date_to;
        this.working_from = working_from;
        this.working_to = working_to;
        this.ticket_price = ticket_price;
    }

    public int getExhibitionId(){
        return id;
    }

    public void setExhibitionId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getTheme(){
        return theme;
    }

    public String getDescription(){
        return description;
    }

    public List<Hall> getHalls(){
        return new ExhibitionService().getHalls(id);
    }

    public LocalDate getDate_from() {
        return date_from;
    }

    public LocalDate getDate_to() {
        return date_to;
    }

    public LocalTime getWorking_from() {
        return working_from;
    }

    public LocalTime getWorking_to() {
        return working_to;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    @Override
    public String toString(){
        return "Exhibitions [id = " + id + ", name = " + name + ", theme = " + theme +
                ", description = " + description +", date_from = " + date_from + ", date_to = " + date_to +
                ", working_from = " + working_from + ", working_to = " + working_to +
                ", ticket_price = " + ticket_price + "]";
    }
}
