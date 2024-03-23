package com.example.ToDoAPI;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class ToDoController {

    private static List<Todo> toDoList;

    public ToDoController() {
        toDoList = new ArrayList<>();
        toDoList.add(new Todo(1, false, "ToDo 1", 1));
        toDoList.add(new Todo(2, true, "ToDo 2", 2));
    }

}
