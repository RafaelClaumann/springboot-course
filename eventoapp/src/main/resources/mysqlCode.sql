CREATE DATABASE eventosapp; -- Create eventosapp database, can use CREATE SCHEMA too

USE eventosapp;
-- select the database that you want use/change

-- Create a table to save events.
CREATE TABLE evn_event
(
    evn_pk    INT PRIMARY KEY AUTO_INCREMENT,
    evn_name  VARCHAR(50)  NOT NULL,
    evn_place VARCHAR(100) NOT NULL,
    evn_date  DATE         NOT NULL
);

-- Create a table to save guests.
CREATE TABLE gst_guest
(
    gst_pk   INT PRIMARY KEY AUTO_INCREMENT,
    gst_rg   VARCHAR(50)  NOT NULL UNIQUE,
    gst_name VARCHAR(100) NOT NULL
);

-- Show table columns and his specifications.
DESCRIBE evn_event;
DESCRIBE gst_guest;

-- Show data stored in table.
SELECT *
FROM evn_event;
SELECT *
FROM gst_guest;

-- Drop row based on conditions
DELETE
FROM evn_event
WHERE evn_place = 'CETEC';

-- Delete tables.
DROP TABLE evn_event;
DROP TABLE gst_guest;
DROP TABLE hibernate_sequence;

-- Delete database
DROP DATABASE eventosapp;
