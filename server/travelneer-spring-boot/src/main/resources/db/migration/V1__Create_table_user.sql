CREATE TABLE user (
   id INT  NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(70) NOT NULL,   
   created_at DATETIME NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY user_name (name),
   UNIQUE KEY user_email (email)
);