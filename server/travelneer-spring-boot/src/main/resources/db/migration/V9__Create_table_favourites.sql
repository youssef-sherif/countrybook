CREATE TABLE favourites (
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (user_id, post_id),
	FOREIGN KEY (user_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (post_id) REFERENCES post (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);