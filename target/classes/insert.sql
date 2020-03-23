-- SCHEMA: public
-- Used for creating fake data.

INSERT INTO Insurance (id, policy_number, provider, vehicle_id) VALUES (1, 'C1', 'Tesla Insurance', 1), (2, 'S1', 'Intact', 1), (3, 'T1', 'RBC Insurance', 1),
(4, 'X1', 'American Insurance', 1), (5, 'Y1', 'Tesla Insurance', 1);

INSERT INTO Address (id, city, prov, country, zip, street_no_name) VALUES (1, 'Vancouver', 'British Columbia', 'Canada', 'V6R 2G9', '3981 W 12th Ave'), (2, 'Toronto', 'Ontario', 'Canada', 'T6R 2G9', '39 W 10th Ave'), 
(3, 'Edmenton', 'Alberta', 'Canada', 'E6R 2G9', '81 W 19th Ave'), 
(4, 'Montreal', 'Qubec', 'Canada', 'M6R 2G9', '31 W 20th Ave'), (5, 'Ottawa', 'Ontario', 'Canada', 'O6R 2G9', '62 W 62nd Ave'),
 (6, 'Ottawa', 'Ontario', 'Canada', 'O6R 2E9', '2 W 82nd Ave');

INSERT INTO Member (id, name, age, rating, status, address_id) VALUES (1, 'Povel', 22, '5 STAR','ACTIVE', 1), (2, 'Jessica', 21, '4 STAR','ACTIVE', 2), (3, 'Will', 73, '1 STAR','ACTIVE', 3),
(4, 'Austin', 33, '4.5 STARS','ACTIVE', 4), (5, 'Sam', 16, '0 STARS', 'ACTIVE', 5), (6, 'Harman', 27, '3.5 STARS', 'INACTIVE', 4), 
(7, 'Veronica', 36, '0 STARS', 'ACTIVE', 6);

INSERT INTO Ridee (id, member_id, is_minor) VALUES (1, 1, false), (2, 2, false), (3, 3, false), (4, 4, false), (5, 5, true);

INSERT INTO Preferences (id, description, favourites, ridee_id) VALUES (1, 'Clean environment', NULL, 1), (2, 'No Talking', NULL, 2), (3, 'Hip hop soundtracks', NULL, 3),
 (4, 'slow paced driver', NULL, 4), (5, 'To be extra chatty', NULL, 5);

INSERT INTO Rider (id, member_id, vehicle_id) VALUES (1, 1, 1), (2, 2, 2), (3, 6, 3), (4, 4, 4), (5, 7, 5);

INSERT INTO Ride (id, notes, arrival_time, departure_time, arrival_location, departure_location, rider_id, ridee_id) VALUES (1, NULL, '2018-11-11 8:59 PM' , '2018-11-11 6:59 PM', 'Vancouver', 'Toronto', 2, 1), (2, 'Stopped at McDonalds on Highway 1', '2019-09-11 1:59 PM' , '2019-09-11 10:59 PM', 'Toronto', 'Edmenton', 3, 2),
 (3, NULL, '2019-04-04 1:00 PM' , '2019-04-04 5:24 PM', 'Montreal', 'Toronto', 2, 4),  (4, NULL, '2019-01-12 4:00 AM' , '2019-01-12 8:24 PM', 'Montreal', 'Nova Scotia', 5, 5)
,  (5, 'Encountered an accident at Bloor and Yonge', '2020-02-12 4:00 AM' , '2020-02-12 8:24 PM', 'Vancouver', 'Burnaby', 4, 3), 
 (6, 'Safe Travels', '2020-02-22 4:00 AM' , '2019-02-22 8:24 PM', 'Vancouver', 'Vancouver', 1, 3);
 
INSERT INTO Feedback (id, feedback, feedback_type, ride_id, member_id) VALUES (1, 'The drive was really awesome.', 'REGULAR', 1, 1), (2, 'The ride was really comfortable.', 'REGULAR', 1, 2)
, (3, 'The ride was a bit bumpy.', 'COMPLAINT', 2, 2) , (4, 'Veronica is a really impressive driver. HATS OFF.', 'REGULAR', 3, 5) 
, (5, 'A lot of stops in the way, but happy with the trip', 'REGULAR', 4, 3);


