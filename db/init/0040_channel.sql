USE chatapp;

CREATE TABLE channel (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  created_by INT NOT NULL,
  FOREIGN KEY (created_by) REFERENCES user (id)
);
