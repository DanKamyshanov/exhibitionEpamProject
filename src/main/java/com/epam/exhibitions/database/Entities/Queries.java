package com.epam.exhibitions.database.Entities;

public class Queries {

    //Exhibition DAO queries
    public static String EXHIBITION_GET_BY_ID = "SELECT * FROM exhibitions WHERE id = ?";
    public static String EXHIBITION_ADD = "INSERT INTO exhibitions (name, theme, description, date_from, date_to, working_from, working_to, ticket_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static String EXHIBITION_GET_ALL = "SELECT * FROM exhibitions";
    public static String EXHIBITION_GET_HALLS = "SELECT * FROM halls INNER JOIN exhibition_halls ON halls.id = exhibition_halls.hall_id INNER JOIN exhibitions ON exhibition_halls.exhibition_id = exhibitions.id WHERE exhibitions.id = ?";
    public static String EXHIBITION_SET_HALLS = "INSERT INTO exhibition_halls (exhibition_id, hall_id) VALUES (?, ?)";
    public static String EXHIBITION_GET_TICKETS_SOLD = "SELECT COUNT(*) as tickets_sold FROM tickets WHERE exhibition_id = ?";
    public static String EXHIBITION_GET_STATS_BY_ID = "SELECT COUNT(*) as ticket_purchases FROM purchases WHERE user_id = ? AND exhibition_id = ?";
    public static String EXHIBITION_GET_NUMBER_BY_DATE = "SELECT COUNT(*) as number FROM exhibitions WHERE ? BETWEEN date_from AND date_to";
    public static String EXHIBITION_GET_NUMBER = "SELECT COUNT(*) as number FROM exhibitions";
    public static String EXHIBITION_GET_BY_HALLS = "SELECT * FROM exhibitions INNER JOIN exhibition_halls ON exhibitions.id = exhibition_halls.exhibition_id INNER JOIN halls ON exhibition_halls.hall_id = halls.id WHERE halls.id = ?";
    public static String EXHIBITION_GET_BY_NAME = "SELECT * FROM exhibitions WHERE name = ?";
    public static String EXHIBITION_GET_BY_DATE_SORT = "SELECT * FROM exhibitions WHERE ? BETWEEN date_from AND date_to LIMIT ?, 4";
    public static String EXHIBITION_GET_BY_PRICE_SORT = "SELECT * FROM exhibitions ORDER BY ticket_price LIMIT ?, 4";
    public static String EXHIBITION_GET_BY_THEME_SORT = "SELECT * FROM exhibitions ORDER BY theme LIMIT ?, 4";
    public static String EXHIBITION_DEFAULT_SORT = "SELECT * FROM exhibitions LIMIT ?, 4";
    public static String EXHIBITION_DELETE = "DELETE FROM exhibitions WHERE id = ?";
    public static String EXHIBITION_UPDATE = "UPDATE exhibitions SET name = ?, theme = ?, description = ?, date_from = ?, date_to = ?, working_from = ?, working_to = ?, ticket_price = ? WHERE id = ?";
    public static String EXHIBITION_SORT = "SELECT * FROM exhibitions WHERE ticket_price BETWEEN ? AND ? AND (date_from <= ? AND ? <= date_to) AND (name LIKE ?)";

    //User DAO queries
    public static String USER_GET_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static String USER_GET_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static String USER_GET_BY_CREDENTIALS = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static String USER_GET_ROLE = "SELECT role_name FROM roles INNER JOIN users WHERE login = ? ON roles.role_id = users.role_id";
    public static String USER_GET_PASSWORD = "SELECT password FROM users WHERE login = ?";
    public static String USER_GET_ALL = "SELECT * FROM users";
    public static String USER_ADD = "INSERT INTO users (first_name, last_name, login, password, email, phone_number, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static String USER_DELETE = "DELETE FROM users WHERE id = ?";
    public static String USER_UPDATE = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, email = ?, phone_number = ? WHERE id = ?";
    public static String USER_VERIFY_LOGIN = "SELECT login FROM users WHERE login = ?";
    public static String USER_VERIFY_EMAIL = "SELECT email FROM users WHERE email = ?";
    public static String USER_VERIFY_PHONE = "SELECT phone_number FROM users WHERE phone_number = ?";
    public static String USER_PURCHASE_TICKET = "INSERT INTO purchases VALUES (?, ?)";

    //Ticket DAO queries
//    public static String TICKET_GET_BY_USER = "SELECT * FROM tickets WHERE user_login = ?";
//    public static String TICKET_ADD = "INSERT INTO tickets VALUES (?, ?, ?)";
//    public static String TICKET_DELETE = "DELETE FROM tickets WHERE exhibition_id = ?";

    //Hall DAO queries
    public static String HALL_GET_ALL = "SELECT * FROM halls";
    public static String HALL_GET_BY_NAME = "SELECT * FROM halls where name = ?";
    public static String HALL_GET_BY_ID = "SELECT * FROM halls where id = ?";
    public static String HALL_ADD = "INSERT INTO halls (name, description) VALUES (?, ?)";
    public static String HALL_DELETE = "DELETE FROM halls WHERE id = ?";
    public static String HALL_UPDATE = "UPDATE halls SET name = ?, description = ? WHERE id = ?";
}
