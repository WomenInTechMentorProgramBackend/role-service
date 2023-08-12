package com.medicalcenter.roleservice.service.containers;

import org.testcontainers.containers.PostgreSQLContainer;

public class Postgres extends PostgreSQLContainer<Postgres> {
    private static Postgres container;
    public Postgres() {
        super("postgres:latest");
    }
    public static Postgres getInstance() {
        if (container == null) {
            container = new Postgres();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        // do nothing
    }
}
