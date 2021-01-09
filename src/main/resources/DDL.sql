CREATE DATABASE stm_db;
CREATE USER 'stm_user'@'%' IDENTIFIED BY 'pass';
GRANT CREATE, DROP, DELETE, SELECT, UPDATE ON stm_db.* TO 'stm_user'@'%';
