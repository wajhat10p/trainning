package com.example.demo1;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoRest {
    @Inject
    TodoService todoService;

    @Path("new")
    @POST

    public Response creatTodo(Todo todo) {
        todoService.createTodo(todo);
        return Response.ok(todo).build();
    }

    @Path("update")
    @PUT
    public Response updateTodo(Todo todo) {
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }

    @Path("{id}")
    @GET
    public Todo getTodo(@PathParam("id") long id) {
       return todoService.findToDoByid(id=id);
    }

    @Path("list")
    @GET
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

}
