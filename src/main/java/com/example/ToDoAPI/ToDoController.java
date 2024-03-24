package com.example.ToDoAPI;

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
    public List<Todo> getToDos() {
        return toDoList;
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo newTodo) {
        toDoList.add(newTodo);
        return newTodo;
    }

}
