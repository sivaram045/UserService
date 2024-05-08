CREATE TABLE token (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       value VARCHAR(255),
                       expiry_date DATETIME,
                       user_id INT,
                       FOREIGN KEY (user_id) REFERENCES user(id)
);
