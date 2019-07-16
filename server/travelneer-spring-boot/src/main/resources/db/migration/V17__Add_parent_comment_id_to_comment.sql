ALTER TABLE comment
    ADD COLUMN parent_comment_id INT AFTER parent_post_id;


ALTER TABLE comment
    ADD CONSTRAINT fk_parent_comment_id
    FOREIGN KEY (parent_comment_id) REFERENCES comment (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;