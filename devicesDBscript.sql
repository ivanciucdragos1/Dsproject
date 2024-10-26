CREATE DATABASE devices_db;
\c devices_db;
CREATE TABLE IF NOT EXISTS devices (
	id UUID PRIMARY KEY,
	user_id UUID NOT NULL,
	description TEXT NOT NULL,
	address TEXT NOT NULL,
	max_hr_consumption INTEGER NOT NULL
);