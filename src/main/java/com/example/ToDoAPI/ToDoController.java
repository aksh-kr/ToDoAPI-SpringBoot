package com.example.ToDoAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {

    private static List<Todo> toDoList;

    public ToDoController() {
        toDoList = new ArrayList<>();
        toDoList.add(new Todo(1, false, "ToDo 1", 1));
        toDoList.add(new Todo(2, true, "ToDo 2", 2));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getToDos() {
        return ResponseEntity.ok(toDoList);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        toDoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity getTodoById(@PathVariable Long todoId) {
        for (Todo todo : toDoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        return new ResponseEntity("Todo Not Found for the given todoId", null, HttpStatus.NOT_FOUND);
    }

}
