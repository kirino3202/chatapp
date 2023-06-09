USE chatapp;

CREATE TABLE message (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  channel_id INT NOT NULL,
  content VARCHAR(2000) NOT NULL,
  created_by INT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (channel_id) REFERENCES channel (id),
  FOREIGN KEY (created_by) REFERENCES user (id)
);
