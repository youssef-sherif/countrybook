CREATE TABLE favourites (
    created_at DATETIME NOT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (user_id, post_id),
	FOREIGN KEY (user_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (post_id) REFERENCES post (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);