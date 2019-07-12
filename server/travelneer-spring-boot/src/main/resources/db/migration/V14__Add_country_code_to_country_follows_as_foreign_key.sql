ALTER TABLE country_follows
    ADD COLUMN country_code VARCHAR(2) NOT NULL AFTER user_id;

ALTER TABLE country_follows
    ADD CONSTRAINT fk_country_follows_country_code
    FOREIGN KEY (country_code) REFERENCES country (code)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;