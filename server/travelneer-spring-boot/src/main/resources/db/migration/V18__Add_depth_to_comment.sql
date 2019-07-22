ALTER TABLE comment
    ADD COLUMN depth SMALLINT NOT NULL AFTER parent_comment_id;