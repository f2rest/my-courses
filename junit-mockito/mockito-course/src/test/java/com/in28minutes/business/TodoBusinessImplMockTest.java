package com.in28minutes.business;

import com.in28munites.data.api.TodoService;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

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

    @Test
    public void testRetriveTodosRelatedToSpring_usingBDD() {

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC" , "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);

        List<String> expectedValues = Arrays.asList("Learn Spring MVC" , "Learn Spring");

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

        //Then
        Assert.assertThat(filteredTodos.size(), Is.is(2));
        Assert.assertThat(expectedValues, Is.is(filteredTodos));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC" , "Learn Spring","Learn to Dance");

        given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        //Then
        then(todoServiceMock).should().deleteTodo("Learn to Dance" );
        //verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
        //verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
        //verify(todoServiceMock, atLeast(5)).deleteTodo("Learn to Dance");
        //verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        //verify(todoServiceMock, never()).deleteTodo("Learn Spring");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

        //Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC" , "Learn Spring","Learn to Dance");

        given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        //Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), Is.is("Learn to Dance"));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {

        //Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn to Rock and Roll" , "Learn Spring","Learn to Dance");

        given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        //Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getAllValues().size(), Is.is(2));
    }
}

