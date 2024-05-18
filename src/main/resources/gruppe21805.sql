DROP TABLE IF EXISTS person;


CREATE TABLE person (
	pnr       SERIAL,
	vorname   varchar(20) not null,
	nachname  varchar(20) not null,
	gehalt    int not null,
	PRIMARY KEY (pnr)
);
