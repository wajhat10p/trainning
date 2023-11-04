package com.example.demo1;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;


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
        return Response.ok(todo).status(201).build();
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
        return todoService.findToDoByid(id = id);
    }

    @Path("list")
    @GET
    public List<Todo> getTodos(@QueryParam("task") String task, @QueryParam("dateCompleted") String dateCompleted) {
        List<Todo> filteredTodos = todoService.getTodos();

        if (task != null) {
            filteredTodos = filteredTodos.stream()
                    .filter(todo -> todo.getTask().equals(task))
                    .collect(Collectors.toList());
        }

        if (dateCompleted != null) {
            filteredTodos = filteredTodos.stream()
                    .filter(todo -> todo.getDateCompleted().equals(dateCompleted))
                    .collect(Collectors.toList());
        }

        return filteredTodos;
    }


    @Path("status")
    @POST
    public Response markAsComplete(@QueryParam("id") Long id) {
        Todo todo = todoService.findToDoByid(id);
        todo.setCompleted(true);
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }
}