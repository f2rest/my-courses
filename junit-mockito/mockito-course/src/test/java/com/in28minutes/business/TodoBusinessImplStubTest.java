package com.in28minutes.business;

import com.in28minutes.data.api.TodoServiceStub;
import com.in28munites.data.api.TodoService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TodoBusinessImplStubTest {

    @Test
    public void testRetriveTodosRelatedToSpring_usingAStub(){
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());

        List<String> expectedValues = Arrays.asList("Learn Spring MVC" , "Learn Spring");
        assertEquals(expectedValues, filteredTodos);
    }
}
