CREATE TABLE tour_rating (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
tour_id BIGINT,
customer_id BIGINT,
score INT,
comment VARCHAR(100));

ALTER TABLE tour_rating ADD FOREIGN KEY (tour_id) REFERENCES tour(id);
ALTER TABLE tour_rating ADD UNIQUE MyConstraint (tour_id, customer_id);

insert into tour  (tour_package_code, title, difficulty, region) values
(
'SD',
'Sindh Tour',
'Medium',
'SINDH'
);

insert into tour_rating (tour_id, customer_id, score, comment) values
(1, 4, 5, 'I loved it'),
(1, 5, 5, 'We loved it');