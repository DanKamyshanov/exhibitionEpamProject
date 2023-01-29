package com.epam.exhibitions.database.Entities;

import java.time.LocalDate;

public class Hall {

    private int id;
    private String name;
    private String description;
    private LocalDate creationDate;

    public Hall(String name, String description){
        this.name = name;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(){
        this.description = description;
    }
}
