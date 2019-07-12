ALTER TABLE post
    ADD COLUMN country_code VARCHAR(2) NOT NULL AFTER author_id;

ALTER TABLE post
    ADD CONSTRAINT fk_post_country_code
    FOREIGN KEY (country_code) REFERENCES country (code)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;