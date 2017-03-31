DROP TABLE IF EXISTS Role;

DROP TABLE IF EXISTS Notification;
DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS Provider_review;
DROP TABLE IF EXISTS Event_review;
DROP TABLE IF EXISTS Registration;
DROP TABLE IF EXISTS Tag_subscription;
DROP TABLE IF EXISTS Provider_subscription;
DROP TABLE IF EXISTS Event_tags;
DROP TABLE IF EXISTS Tag;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS Consumer;
DROP TABLE IF EXISTS Administrator;
DROP TABLE IF EXISTS Provider;
DROP TABLE IF EXISTS "User";
DROP TYPE IF EXISTS Related_to;
DROP TYPE IF EXISTS Registration_status;
DROP TYPE IF EXISTS Event_status;
DROP TYPE IF EXISTS "Role";

CREATE TYPE "Role" AS ENUM (
  'CONSUMER',
  'PROVIDER',
  'ADMINISTRATOR'
);

CREATE TYPE Event_status AS ENUM (
  'AVAILABLE',
  'FULL',
  'CANCELLED',
  'CLOSED_REGISTRATION',
  'ONGOING',
  'TERMINATED'
);

CREATE TYPE Registration_status AS ENUM (
  'REGISTERED',
  'WAITING_PAYMENT',
  'PAID',
  'LATE_PAYMENT',
  'REFUSED'
);

CREATE TYPE Related_to AS ENUM (
  'EVENT',
  'MESSAGE',
  'REGISTRATION',
  'PROVIDER_REVIEW',
  'EVENT_REVIEW'
);

CREATE TABLE "User" (
  pseudo   VARCHAR(30) NOT NULL,
  password TEXT        NOT NULL,
  email    VARCHAR(40) NOT NULL,
  role     "Role"      NOT NULL,
  PRIMARY KEY (pseudo)
);

CREATE TABLE Provider (
  name           VARCHAR(30) NOT NULL,
  description    TEXT        NOT NULL,
  phone          VARCHAR(10),
  website        VARCHAR(100),
  officeLocation TEXT,
  userID         VARCHAR(30) NOT NULL,
  PRIMARY KEY (userID),
  FOREIGN KEY (userID) REFERENCES "User" (pseudo)
);

CREATE TABLE Administrator (
  userID VARCHAR(30) NOT NULL,
  PRIMARY KEY (userID),
  FOREIGN KEY (userID) REFERENCES "User" (pseudo)
);

CREATE TABLE Consumer (
  firstName VARCHAR(30) NOT NULL,
  lastName  VARCHAR(30) NOT NULL,
  comments  TEXT,
  userID    VARCHAR(30) NOT NULL,
  PRIMARY KEY (userID),
  FOREIGN KEY (userID) REFERENCES "User" (pseudo)
);

-- duration :  FLOAT number of hours
-- delay_to_pay : INT number of days
CREATE TABLE Event (
  id                    SERIAL       NOT NULL,
  title                 VARCHAR(30)  NOT NULL,
  subtitle              VARCHAR(40),
  location              VARCHAR(30)  NOT NULL,
  description           TEXT,
  begining_time         TIMESTAMP    NOT NULL,
  registration_deadline TIMESTAMP    NOT NULL,
  duration              FLOAT,
  event_constraints     TEXT,
  max_number_of_places  INT          NOT NULL,
  price                 FLOAT,
  delay_to_pay          INT,
  status                Event_status NOT NULL,
  providerID            VARCHAR(30),
  PRIMARY KEY (id),
  FOREIGN KEY (providerID) REFERENCES Provider (userID)
);

CREATE TABLE Tag (
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (name)
);

CREATE TABLE Event_tags (
  name    VARCHAR(30) NOT NULL,
  eventID INT         NOT NULL,
  PRIMARY KEY (name, eventID),
  FOREIGN KEY (name) REFERENCES Tag (name),
  FOREIGN KEY (eventID) REFERENCES Event (id)
);

CREATE TABLE Provider_subscription (
  consumerID VARCHAR(30) NOT NULL,
  providerID VARCHAR(30) NOT NULL,
  PRIMARY KEY (consumerID, providerID),
  FOREIGN KEY (consumerID) REFERENCES Consumer (userID),
  FOREIGN KEY (providerID) REFERENCES Provider (userID)
);

CREATE TABLE Tag_subscription (
  consumerID VARCHAR(30) NOT NULL,
  tagID      VARCHAR(30) NOT NULL,
  PRIMARY KEY (consumerID, tagID),
  FOREIGN KEY (consumerID) REFERENCES Consumer (userID),
  FOREIGN KEY (tagID) REFERENCES Tag (name)
);

CREATE TABLE Registration (
  consumerID    VARCHAR(30)         NOT NULL,
  eventID       INT                 NOT NULL,
  creation_time TIMESTAMP           NOT NULL,
  status        Registration_status NOT NULL,
  PRIMARY KEY (consumerID, eventID),
  FOREIGN KEY (consumerID) REFERENCES Consumer (userID),
  FOREIGN KEY (eventID) REFERENCES Event (id)
);

CREATE TABLE Event_review (
  consumerID VARCHAR(30) NOT NULL,
  eventID    INT         NOT NULL,
  rate       INT         NOT NULL,
  content    TEXT,
  PRIMARY KEY (consumerID, eventID),
  FOREIGN KEY (consumerID, eventID) REFERENCES Registration (consumerID, eventID)
);

CREATE TABLE Provider_review (
  consumerID VARCHAR(30) NOT NULL,
  providerID VARCHAR(30) NOT NULL,
  rate       INT         NOT NULL,
  content    TEXT,
  PRIMARY KEY (consumerID, providerID),
  FOREIGN KEY (consumerID, providerID) REFERENCES Provider_subscription (consumerID, providerID)
);

CREATE TABLE Message (
  id       SERIAL      NOT NULL,
  userID   VARCHAR(30) NOT NULL,
  eventID  INT         NOT NULL,
  content  TEXT        NOT NULL,
  postTime TIMESTAMP   NOT NULL,
  parent   INT REFERENCES Message (id),
  PRIMARY KEY (id),
  FOREIGN KEY (userID) REFERENCES "User" (pseudo),
  FOREIGN KEY (eventID) REFERENCES Event (id)
);

CREATE TABLE Notification (
  id                  SERIAL      NOT NULL,
  isRead              BOOLEAN     NOT NULL DEFAULT FALSE,
  userID              VARCHAR(30) NOT NULL,
  message             VARCHAR(200),
  relatedTo           Related_to  NOT NULL,
  relatedToEventID    INT,
  relatedToMessageID  INT,
  relatedToUserID     VARCHAR(30),
  relatedToConsumerID VARCHAR(30),
  relatedToProviderID VARCHAR(30),
  PRIMARY KEY (id),
  FOREIGN KEY (userID) REFERENCES "User" (pseudo),
  FOREIGN KEY (relatedToEventID) REFERENCES Event (id),
  FOREIGN KEY (relatedToMessageID) REFERENCES Message (id),
  FOREIGN KEY (relatedToUserID) REFERENCES "User" (pseudo),
  FOREIGN KEY (relatedToConsumerID) REFERENCES Consumer (userID),
  FOREIGN KEY (relatedToProviderID) REFERENCES Provider (userID)
);

INSERT INTO "User" (pseudo, password, email, role)
VALUES ('consumer', 'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=', 'consumer@gmail.com', 'CONSUMER');
INSERT INTO Consumer (firstName, lastName, comments, userID) VALUES ('Michelle', 'Kart', '', 'consumer');

INSERT INTO "User" (pseudo, password, email, role)
VALUES ('provider', 'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=', 'provider@gmail.com', 'PROVIDER');
INSERT INTO Provider (name, description, phone, website, officeLocation, userID)
VALUES ('PolyBeer', 'Club to make beers', '0123456789', 'polybeer.com', 'Polytech', 'provider');

INSERT INTO "User" (pseudo, password, email, role)
VALUES ('admin', 'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=', 'admin@gmail.com', 'ADMINISTRATOR');
INSERT INTO Administrator (userID) VALUES ('admin');