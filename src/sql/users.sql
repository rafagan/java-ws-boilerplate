CREATE DATABASE IF NOT EXISTS emeter;
CREATE USER 'vetorlog'@'localhost' IDENTIFIED BY 'abcd@1234';
GRANT USAGE ON *.* TO 'vetorlog'@'localhost';
GRANT ALL PRIVILEGES ON `emeter`.* TO 'vetorlog'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;