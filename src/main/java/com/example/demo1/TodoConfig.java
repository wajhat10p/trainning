package com.example.demo1;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class TodoConfig extends Application {
    static {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
    }
}