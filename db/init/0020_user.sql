USE chatapp;

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  enabled TINYINT(1) NOT NULL
);

INSERT INTO user (username, enabled) VALUES ('DUMMY', 0);
