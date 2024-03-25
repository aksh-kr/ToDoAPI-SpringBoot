package com.example.ToDoAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/todos")
@RestController
public class ToDoController {

    private static List<Todo> toDoList;

    public ToDoController() {
        toDoList = new ArrayList<>();
        toDoList.add(new Todo(1, false, "ToDo 1", 1));
        toDoList.add(new Todo(2, true, "ToDo 2", 2));
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getToDos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println("Incoming query param is : " + isCompleted);
        return ResponseEntity.ok(toDoList);
    }

    @PostMapping()
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        toDoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable Long todoId) {
        for (Todo todo : toDoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        return new ResponseEntity<>("Todo Not Found for the given todoId", null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long todoId) {
        for (Todo todo : toDoList) {
            if(todo.getId() == todoId) {
                toDoList.remove(todo);
                return ResponseEntity.ok("Todo deleted for the given todoId");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Not Found");
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo requestTodo) {
        for (Todo todo : toDoList) {
            if(todo.getId() == todoId) {
                if(requestTodo.isCompleted() !=  null) {
                    todo.setCompleted(requestTodo.isCompleted());

                }
                if(requestTodo.getTitle() !=  null) {
                    todo.setTitle(requestTodo.getTitle());

                }
                if(requestTodo.getUserId() !=  null) {
                    todo.setUserId(requestTodo.getUserId());

                }
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
