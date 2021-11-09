CREATE TABLE tour_package(
code CHAR(3) NOT NULL UNIQUE,
name VARCHAR(50) NOT NULL
);


CREATE TABLE tour (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
tour_package_code CHAR(3) NOT NULL,
title VARCHAR(100) NOT NULL,
difficulty VARCHAR(16) NOT NULL,
region VARCHAR(20) NOT NULL
);


ALTER TABLE tour ADD CONSTRAINT FK_TOUR_PACKAGE_CODE FOREIGN KEY (tour_package_code) REFERENCES tour_package(code);


insert into tour_package (code, name) values
('SD', 'Sindh'),
('KPK', 'Khyber Pakhtunkwah'),
('PJ', 'Punjab'),
('BL', 'Balcochistan'),
('GB', 'Gilgit Baltistan');