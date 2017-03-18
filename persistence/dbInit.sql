CREATE DATABASE db;
USE db;
CREATE TABLE "Role" (
    id INTEGER NOT NULL AUTO_INCREMENT,
    role VARCHAR(20),
    PRIMARY KEY(idRole)
);

CREATE TABLE "User" (
    pseudo VARCHAR(30) NOT NULL,
    email VARCHAR(40) NOT NULL,
    firstname VARCHAR(30),
    lastname VARCHAR(30),
    password VARCHAR(30) NOT NULL,
    role INT,
    PRIMARY KEY(pseudo),
    FOREIGN KEY (role) REFERENCES Role(id)
);

CREATE TABLE "Provider" (
    description TEXT,
    phone VARCHAR(10),
    website VARCHAR(50),
    officeLocation TEXT,
    userID VARCHAR(30),
    PRIMARY KEY(id),
    FOREIGN KEY (userID) REFERENCES User(pseudo)
);
-- duration :  FLOAT number of hours
-- delay_to_pay : INT number of days
CREATE TABLE "Event"(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(30) NOT NULL,
    subtitle VARCHAR(40),
    location VARCHAR(30) NOT NULL,
    description TEXT,
    begining_time DATE NOT NULL,
    registration_deadline DATE NOT NULL,
    duration FLOAT,
    event_constraints TEXT,
    max_number_of_places INT NOT NULL,
    price FLOAT,
    delay_to_pay INT,
    status VARCHAR(30),
    PRIMARY KEY(id)
);

CREATE TABLE "Tag"(
    name VARCHAR(30) NOT NULL,
    PRIMARY KEY(name)
);

CREATE TABLE "Event_tags"(
    name VARCHAR(30) NOT NULL,
    event_id INT NOT NULL,
    PRIMARY KEY(name, event_id)
)

CREATE TABLE "Subscription_to_provider"(
    userID VARCHAR(30) NOT NULL,
    providerID VARCHAR(30) NOT NULL,
    PRIMARY KEY(userID,providerID),
    FOREIGN KEY (userID) REFERENCES User(pseudo),
    FOREIGN KEY (providerID) REFERENCES Provider(userID)
);

CREATE TABLE "Subscription_to_tag"(
    userID VARCHAR(30) NOT NULL,
    tagID VARCHAR(30) NOT NULL,
    PRIMARY KEY(userID,eventID),
    FOREIGN KEY (userID) REFERENCES User(pseudo),
    FOREIGN KEY (tagID) REFERENCES Tag(name)
);

CREATE TABLE "Event_review"(
    userID VARCHAR(30) NOT NULL,
    eventID INT NOT NULL,
    rate INT NOT NULL,
    review TEXT,
    PRIMARY KEY(userID,eventID),
    FOREIGN KEY (userID) REFERENCES User(pseudo),
    FOREIGN KEY (eventID) REFERENCES Event(id)
);

CREATE TABLE "Provider_review"(
    userID VARCHAR(30) NOT NULL,
    providerID VARCHAR(30) NOT NULL,
    rate INT NOT NULL,
    review TEXT,
    PRIMARY KEY(userID,providerID),
    FOREIGN KEY (userID) REFERENCES User(pseudo),
    FOREIGN KEY (providerID) REFERENCES Provider(userID)
);

CREATE TABLE "Event_registration"(
    userID VARCHAR(30) NOT NULL,
    eventID INT NOT NULL,
    creation_time DATE NOT NULL,
    status BOOLEAN NOT NULL,
    event_review VARCHAR(60) REFERENCES event_review,
    PRIMARY KEY(userID,eventID),
    FOREIGN KEY (userID) REFERENCES User(pseudo),
    FOREIGN KEY (eventID) REFERENCES Event(id)
);

CREATE TABLE "Event_message"(
    userID VARCHAR(30) NOT NULL,
    eventID INT NOT NULL,
    responds_to VARCHAR(60) REFERENCES Event_message,
    review TEXT NOT NULL,
    PRIMARY KEY(userID,eventID),
    FOREIGN KEY (userID) REFERENCES User(pseudo),
    FOREIGN KEY (eventID) REFERENCES Event(id)
);

CREATE TABLE "Notifications"(
    id INT NOT NULL AUTO_INCREMENT,
    unread BOOLEAN NOT NULL DEFAULT FALSE,
    relates_to INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (relates_to) REFERENCES Event(id)
)