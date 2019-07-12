CREATE TABLE country (
    id SMALLINT NOT NULL auto_increment,
    code VARCHAR(2) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
PRIMARY KEY (id)
);