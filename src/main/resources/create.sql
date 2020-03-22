-- SCHEMA: public
-- Here for just viewing purposes (table generation is done via hibernate)

DROP TABLE Feedback;
DROP TABLE Ride;
DROP TABLE Rider;
DROP TABLE Preferences;
DROP TABLE Ridee;
DROP TABLE Member;
DROP TABLE Address;
DROP TABLE Insurance;
DROP TABLE Vehicle;

CREATE TABLE Vehicle(
	vehicle_id INTEGER PRIMARY KEY,
	year INTEGER,
	make CHAR(100),
	model CHAR(64)
);

CREATE TABLE Insurance(
	insurance_id INTEGER PRIMARY KEY,
	policy_number CHAR(64) UNIQUE,
	provider CHAR(64),
	vehicle_id INTEGER NOT NULL REFERENCES Vehicle(vehicle_id) ON DELETE CASCADE);

CREATE TABLE Address(
	address_id INTEGER PRIMARY KEY,
	city CHAR(64),
	prov CHAR(64),
	country CHAR(64),
	zip CHAR(64),
	street_no_name CHAR(64)
);

CREATE TABLE Member(
	member_id INTEGER PRIMARY KEY,
    name CHAR(64),
    age INTEGER,
	rating CHAR(64),
	status CHAR(64),
    address_id INTEGER REFERENCES Address(address_id)
);

CREATE TABLE Ridee(
	ridee_id INTEGER PRIMARY KEY,
	member_id INTEGER REFERENCES Member(member_id),
    is_minor BIT
);

CREATE TABLE Preferences(
	preference_id INTEGER PRIMARY KEY,
	description CHAR(100),
	favourites CHAR(100),
	ridee_id INTEGER REFERENCES Ridee(ridee_id)
);


CREATE TABLE Rider(
	rider_id INTEGER PRIMARY KEY ,
	member_id INTEGER REFERENCES Member(member_id),
	vehicle_id INTEGER NOT NULL REFERENCES Vehicle(vehicle_id)
);


CREATE TABLE Ride(
	ride_id INTEGER PRIMARY KEY,
	notes CHAR(100),
	arrival_time CHAR(64),
	departure_time CHAR(64),
	arrival_location CHAR(64),
	departure_location CHAR(64),
	rider_id INTEGER REFERENCES Rider(rider_id), 
	ridee_id INTEGER REFERENCES Ridee(ridee_id)
);

CREATE TABLE Feedback(
	feedback_id INTEGER PRIMARY KEY,
	feedback CHAR(100),
	feedback_type CHAR(100),
	ride_ID INTEGER REFERENCES Ride(ride_id), 
	member_id INTEGER REFERENCES Member(member_id)
);


