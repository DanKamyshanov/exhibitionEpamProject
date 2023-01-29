package com.epam.exhibitions.database.Entities.Enums;

public enum Roles {
    ADMINISTRATOR,
    USER,
    GUEST;
    public String getName(){
        return name().toLowerCase();
    }
}
