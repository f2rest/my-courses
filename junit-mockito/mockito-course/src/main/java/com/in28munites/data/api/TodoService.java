package com.in28munites.data.api;

import java.util.List;

public interface TodoService {
    List<String> retriveTodos(String user);

    void deleteTodo(String todo);
}
