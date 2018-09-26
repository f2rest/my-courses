package com.in28minutes.data.api;

import com.in28munites.data.api.TodoService;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

    public List<String> retriveTodos(String user) {
        return Arrays.asList("Learn Spring MVC" , "Learn Spring", "Learn to Dance");
    }

    public void deleteTodo(String todo) {

    }
}
