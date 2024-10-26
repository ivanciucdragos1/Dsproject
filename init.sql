CREATE DATABASE users_db;
CREATE DATABASE devices_db;

\c users_db;
CREATE TABLE IF NOT EXISTS users (
	id UUID PRIMARY KEY,
	name TEXT NOT NULL UNIQUE,
	role SMALLINT NOT NULL,
	password TEXT NOT NULL UNIQUE
);


\c devices_db;
CREATE TABLE IF NOT EXISTS devices (
	id UUID PRIMARY KEY,
	user_id UUID NOT NULL,
	description TEXT NOT NULL,
	address TEXT NOT NULL,
	max_hr_consumption INTEGER NOT NULL
);