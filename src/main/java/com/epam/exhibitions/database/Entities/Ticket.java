package com.epam.exhibitions.database.Entities;

public class Ticket {

    private int id;
    private String login;
    private int exhibition_id;
    private int ticket_price;

    public Ticket(String login, int exhibition_id, int ticket_price){
        this.login = login;
        this.exhibition_id = exhibition_id;
        this.ticket_price = ticket_price;
    }

    public int getId() {
        return id;
    }

    public String getUserLogin() {
        return login;
    }

    public void setUserLogin(String login) {
        this.login = login;
    }

    public int getExhibitionId() {
        return exhibition_id;
    }

    public void setExhibitionId(int exhibition_id) {
        this.exhibition_id = exhibition_id;
    }

    public int getTicketPrice() {
        return ticket_price;
    }

    public void setTicketPrice(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    @Override
    public String toString(){
        return "Ticket [user_login =" + login + ", exhibition_id =" + exhibition_id + ", ticket_price =" + ticket_price + "]";
    }
}
