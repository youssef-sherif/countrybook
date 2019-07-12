CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id TINYINT NOT NULL,
    PRIMARY KEY(user_id, role_id),    
	CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES user (id)
	ON DELETE CASCADE ON UPDATE CASCADE,    
	CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES role (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);