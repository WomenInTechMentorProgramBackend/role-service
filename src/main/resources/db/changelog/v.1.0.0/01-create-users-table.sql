--liquibase formatted sql

--changeset taya:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset taya:2
CREATE TABLE users (
                       id UUID DEFAULT gen_random_uuid() NOT NULL,
                       external_id UUID UNIQUE NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP,
                       created_by VARCHAR(50) NOT NULL,
                       updated_by VARCHAR(50),
                       CONSTRAINT users_pk PRIMARY KEY (id),
                       CONSTRAINT users_un UNIQUE (id)
);