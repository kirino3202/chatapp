CREATE DATABASE chatapp;

CREATE USER backend@'%' IDENTIFIED BY 'pass';
GRANT SELECT, INSERT, UPDATE, DELETE ON chatapp.* TO backend;
