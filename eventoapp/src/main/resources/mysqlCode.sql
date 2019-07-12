/*
* Define a database que esta em uso.
*/
USE eventosapp;

CREATE TABLE evn_event
(
evn_pk INT PRIMARY KEY AUTO_INCREMENT,
evn_name VARCHAR(50) NOT NULL,
evn_place VARCHAR(100) NOT NULL,
evn_date DATE
);

DESCRIBE event;

DROP TABLE evn_event;

DROP TABLE hibernate_sequence;

SELECT * FROM evn_event;

/*
*  Deletar rows cuja data tenha um valor null.
*/
DELETE FROM evn_event
WHERE evn_date = NULL;

