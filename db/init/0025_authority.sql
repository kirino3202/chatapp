USE chatapp;

CREATE TABLE authority(
  id INT NOT NULL PRIMARY KEY,
  authority VARCHAR(2000) NOT NULL,
  FOREIGN KEY (id) REFERENCES user (id)
);
