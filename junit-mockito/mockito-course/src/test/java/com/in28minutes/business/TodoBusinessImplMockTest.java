package com.in28minutes.business;

import com.in28munites.data.api.TodoService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TodoBusinessImplMockTest {

    @Test
    public void testRetriveTodosRelatedToSpring_usingAMock() {
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC" , "Learn Spring", "Learn to Dance");
        Mockito.when(todoServiceMock.retriveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());

        List<String> expectedValues = Arrays.asList("Learn Spring MVC" , "Learn Spring");
        assertEquals(expectedValues, filteredTodos);
    }

    @Test
    public void testRetriveTodosRelatedToSpring_whitEmptyList() {
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList();
        Mockito.when(todoServiceMock.retriveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

        assertEquals(0, filteredTodos.size());

        List<String> expectedValues = Arrays.asList();
        assertEquals(expectedValues, filteredTodos);
    }
}
