--liquibase formatted sql

--changeset taya:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset taya:2
CREATE TABLE roles (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    is_active SMALLINT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    updated_by VARCHAR(50),
    CONSTRAINT roles_pk PRIMARY KEY (id),
    CONSTRAINT roles_name_un UNIQUE (name)
);