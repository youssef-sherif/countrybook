CREATE TABLE country_follows (
	user_id INT NOT NULL,
    country_id SMALLINT NOT NULL,
    PRIMARY KEY(user_id, country_id),    
	CONSTRAINT fk_country_follows_user_id FOREIGN KEY (user_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,    
	CONSTRAINT fk_country_follows_country_id FOREIGN KEY (country_id) REFERENCES country (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);