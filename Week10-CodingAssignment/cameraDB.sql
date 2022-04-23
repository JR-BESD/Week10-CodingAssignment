CREATE DATABASE IF NOT EXISTS cameras;

USE cameras;

DROP TABLE IF EXISTS cameras;
DROP TABLE IF EXISTS manufacturers;

CREATE TABLE manufacturers(
mfr_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
mfr_name VARCHAR(25) NOT NULL
);

CREATE TABLE cameras(
camera_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
mfr_id INT NOT NULL,
cam_name VARCHAR(50) NOT NULL,
cam_type VARCHAR(25),
image_type ENUM('film','digital'),
year_of_release YEAR,
FOREIGN KEY(mfr_id) REFERENCES manufacturers(mfr_id)
);