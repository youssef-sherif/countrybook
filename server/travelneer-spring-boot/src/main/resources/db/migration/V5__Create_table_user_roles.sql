CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id TINYINT NOT NULL,
    PRIMARY KEY(user_id, role_id),    
	FOREIGN KEY (user_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,    
	FOREIGN KEY (role_id) REFERENCES role (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);