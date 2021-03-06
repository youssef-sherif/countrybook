CREATE TABLE comment (
    id INT NOT NULL auto_increment,
    content VARCHAR(500) NOT NULL,
    author_id INT NOT NULL,
    parent_post_id INT,
    created_at TIMESTAMP,
    PRIMARY KEY (id),
	CONSTRAINT fk_comment_user_id FOREIGN KEY (author_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_comment_post_id FOREIGN KEY (parent_post_id) REFERENCES post (id)
	ON DELETE SET NULL ON UPDATE NO ACTION
);