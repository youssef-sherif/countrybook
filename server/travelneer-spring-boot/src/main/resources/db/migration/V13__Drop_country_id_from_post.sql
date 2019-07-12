ALTER TABLE post
    DROP FOREIGN KEY fk_post_country_id;

ALTER TABLE post
    DROP COLUMN country_id;

