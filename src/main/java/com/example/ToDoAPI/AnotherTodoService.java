package com.example.ToDoAPI;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AnotherTodoService implements TodoService{

    @Override
    public String doSomething() {
        return "Something from another Todo";
    }
}
