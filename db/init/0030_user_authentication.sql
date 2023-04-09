USE chatapp;

CREATE TABLE user_authentication (
  id INT NOT NULL PRIMARY KEY,
  password_hash VARCHAR(1000) NOT NULL,
  FOREIGN KEY (id) REFERENCES user (id)
);
