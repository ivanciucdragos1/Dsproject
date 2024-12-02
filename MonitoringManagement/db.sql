CREATE DATABASE energydb;

\c energydb;

CREATE TABLE energy (
    id UUID PRIMARY KEY,
    device_id UUID NOT NULL,
    timestamp DATE NOT NULL,
    hr_consumption FLOAT NOT NULL
);