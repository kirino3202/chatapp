USE chatapp;

DELIMITER //

CREATE PROCEDURE add_user(
  IN username VARCHAR(100),
  IN password_hash VARCHAR(1000),
  IN _enabled TINYINT(1)
)
BEGIN
  INSERT INTO user (username, enabled) VALUES (username, _enabled);
  INSERT INTO user_authentication (id, password_hash) VALUES (LAST_INSERT_ID(), password_hash);
END //

DELIMITER ;
