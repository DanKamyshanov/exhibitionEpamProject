package com.epam.exhibitions.database.Entities;

import com.epam.exhibitions.database.Entities.Enums.Roles;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String login;
    private String password;
    private String email;
    private String phone_number;
    private Roles role;

    public User(String first_name, String last_name, String login, String password, String email, String phone_number, Roles role){
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstName){this.first_name = firstName;}

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastName){this.last_name = lastName;}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login){this.login = login;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){this.password = password;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email = email;}

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phoneNumber){this.phone_number = phoneNumber;}

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role){
        this.role = role;
    }

    @Override
    public String toString(){
        return "User [first_name = " + first_name + ", last_name = " + last_name +
                ", login = " + login + ", password = " + password +
                ", email = " + email + ", phone_number = " + phone_number +
                ", role = " + role + "]";
    }
}
