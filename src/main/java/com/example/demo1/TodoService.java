package com.example.demo1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class TodoService {
    @PersistenceContext
    EntityManager entityManager;

    public Todo createTodo(Todo todo) {
        entityManager.persist(todo);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        entityManager.merge(todo);
        return todo;
    }

    public Todo findToDoByid(long id) {
        return entityManager.find(Todo.class, id);
    }

    public List<Todo> getTodos(String task,String dateCompleted ) {
//         serve as a placeholder for the beginning of the WHERE clause
        StringBuilder jpql = new StringBuilder("SELECT t from Todo t WHERE 1=1");

        if (task != null && !task.isEmpty()) {
            jpql.append(" AND t.task = :task");
        }

        if (dateCompleted != null && !dateCompleted.isEmpty()) {
            jpql.append(" AND t.dateCompleted = :dateCompleted");
        }

        TypedQuery<Todo> query = entityManager.createQuery(jpql.toString(), Todo.class);

        if (task != null && !task.isEmpty()) {
            query.setParameter("task", task);
        }

        if (dateCompleted != null && !dateCompleted.isEmpty()) {
            query.setParameter("dateCompleted", dateCompleted);
        }

        return query.getResultList();
    }
}
