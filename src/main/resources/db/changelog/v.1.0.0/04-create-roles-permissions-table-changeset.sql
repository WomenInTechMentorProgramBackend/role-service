--liquibase formatted sql
--changeset taya:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset taya:2
CREATE TABLE roles_permissions (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    role_id UUID NOT NULL,
    permission_id UUID NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    CONSTRAINT roles_permissions_pk PRIMARY KEY (id),
    CONSTRAINT roles_permissions_fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT roles_permissions_fk_permission_id FOREIGN KEY (permission_id) REFERENCES permissions (id)
);