CREATE DATABASE users_db;
\c users_db;
CREATE TABLE IF NOT EXISTS users (
	id UUID PRIMARY KEY,
	name TEXT NOT NULL UNIQUE,
	role SMALLINT NOT NULL,
	password TEXT NOT NULL UNIQUE
);