CREATE TABLE post (
    id INT NOT NULL auto_increment,
    content VARCHAR(500) NOT NULL,
    author_id INT NOT NULL,
    country_id SMALLINT NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id),
	CONSTRAINT fk_post_user_id FOREIGN KEY (author_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_post_country_id FOREIGN KEY (country_id) REFERENCES country (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);