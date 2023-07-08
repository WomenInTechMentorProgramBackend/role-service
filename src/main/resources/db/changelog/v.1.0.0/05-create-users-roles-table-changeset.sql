--liquibase formatted sql
--changeset taya:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset taya:2
CREATE TABLE users_roles (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    users_id UUID NOT NULL,
    role_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    updated_by VARCHAR(50),
    CONSTRAINT users_roles_pk PRIMARY KEY (id),
    CONSTRAINT users_roles_fk_user_id FOREIGN KEY (users_id) REFERENCES users (id),
    CONSTRAINT users_roles_fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);
