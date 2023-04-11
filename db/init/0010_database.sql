CREATE DATABASE chatapp;

CREATE USER backend@'%' IDENTIFIED BY 'pass';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON chatapp.* TO backend;
