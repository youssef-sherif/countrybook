ALTER TABLE country_follows
    DROP PRIMARY KEY, ADD PRIMARY KEY (user_id, country_code);