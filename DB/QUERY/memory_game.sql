Create database memory_game;
use memory_game;

Create table users
(
user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_usernaam VARCHAR(45) NOT NULL,
user_password VARCHAR(8) NOT NULL,
user_naam VARCHAR(45) NOT NULL,
user_achternaam VARCHAR(45) NOT NULL,
user_geboortedatum DATE NOT NULL
);

Create table score
(
score_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id INT NOT NULL,
score INT,
datum date NOT NULL
);

ALTER TABLE score
ADD FOREIGN KEY (user_id) REFERENCES users(user_id);

INSERT INTO users(user_usernaam,user_password,user_naam,user_achternaam,user_geboortedatum)
VALUES ('admin123','1234','admin','admin','2001-01-08');

