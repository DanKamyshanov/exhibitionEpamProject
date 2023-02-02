#Schema creation
CREATE SCHEMA IF NOT EXISTS exhibitions;

#Table creation
USE exhibitions;

CREATE TABLE IF NOT EXISTS exhibitions(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    name VARCHAR(80) NOT NULL,
    theme VARCHAR(80) NOT NULL,
    description VARCHAR(600) NOT NULL,
    date_from DATE NOT NULL,
    date_to DATE NOT NULL,
    working_from TIME NOT NULL,
    working_to TIME NOT NULL,
    ticket_price DECIMAL(6, 2)
);

CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone_number VARCHAR(15) DEFAULT NULL,
    role_id INT DEFAULT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS roles(
    id INT PRIMARY KEY NOT NULL,
    role_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS halls(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS exhibition_halls(
    exhibition_id INT NOT NULL,
    hall_id INT NOT NULL,
    FOREIGN KEY (exhibition_id) REFERENCES exhibitions (id) ON DELETE CASCADE,
    FOREIGN KEY (hall_id) REFERENCES halls (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS purchases(
    user_id INT NOT NULL,
    exhibition_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (exhibition_id) REFERENCES exhibitions (id) ON DELETE CASCADE
);

#Table content
INSERT INTO roles VALUES (0, 'ADMINISTRATOR');
INSERT INTO roles VALUES (1, 'USER');

INSERT INTO halls VALUES (1, 'Elysian Hall', 'A beautiful, rose-themed hall. It is the hall that held our first art exhibition back in 2005, and it still holds its past glory thanks to the restoration, which took place in 2019.');
INSERT INTO halls VALUES (2, 'Seirai Hall', 'The most beloved hall of our guests - it offers a unique recreational zone, which gives you an opportunity to gaze at art without hurry or disturbance.');
INSERT INTO halls VALUES (3, 'Concorde Hall', 'As it is mostly used for modern art exhibitions, this hall is designed to represent all the advancements humanity has achieved. Permanent on-site attractions are also available.');
INSERT INTO halls VALUES (4, 'Холл Єдності', 'Холл, посвяченний національній тематиці. Один з перших холлів, з 2007 року радує гостей національними виставками, прославляючи Українську культуру.');

INSERT INTO users VALUES (1, 'Admin', 'Admin', 'Admin', 'adminadmin123', 'exhibitionsforepam@gmail.com', '+380970000000', 0);
INSERT INTO users VALUES(2, 'Danylo', 'Kamyshanov', 'danky', 'dankyky123', 'dan.kamyshanov@gmail.com', '+380975017992', 1);
INSERT INTO users VALUES (3, 'Anastasiia', 'Burlai', 'anaverse', 'anaverse321', 'anesti.the.owl@gmail.com', '+380673970386', 1);
INSERT INTO users VALUES (4, 'Alex', 'Perov', 'alchwi', 'alchwi67', 'Oleksii.Perov@nure.com', '+380731801932', 1);
INSERT INTO users VALUES (5, 'Hlib', 'Anisimov', 'DJUlt', 'ult55', 'anisimovUlt@icloud.com', '+380951069520', 1);
