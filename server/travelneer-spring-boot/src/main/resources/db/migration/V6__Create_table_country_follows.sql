CREATE TABLE country_follows (
	user_id INT NOT NULL,
    country_id SMALLINT NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY(user_id, country_id),    
	FOREIGN KEY (user_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,    
	FOREIGN KEY (country_id) REFERENCES country (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);