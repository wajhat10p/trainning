package com.example.demo1;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    private int Sal;
    @GET
    @Produces("application/json")
    public String hello() {
        this.Sal=12;
        System.out.println(this.Sal);
        return "Hello, World!";
    }
}