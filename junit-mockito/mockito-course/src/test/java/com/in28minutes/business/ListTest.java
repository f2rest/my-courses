package com.in28minutes.business;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

public class ListTest {

    @Test
    public void letsMockListSizeMethod() {
        List listMock = mock(List.class);

        Mockito.when(listMock.size()).thenReturn(2);

        assertEquals(2 , listMock.size());
    }

    @Test
    public void letsMockListSize_ReturnMutlipleValues() {
        List listMock = mock(List.class);

        Mockito.when(listMock.size()).thenReturn(2).thenReturn(3);

        assertEquals(2 , listMock.size());
        assertEquals(3 , listMock.size());
    }

    @Test
    public void letsMockListGet() {
        List listMock = mock(List.class);
        Mockito.when(listMock.get(anyInt())).thenReturn("prueba mia");
        //Argummnt Matcher
        assertEquals("prueba mia" , listMock.get(0));
        assertEquals("prueba mia" , listMock.get(1));
    }

    @Test
    public void letsMockListGet_usingBDD() {
        //Given
        List<String> listMock = mock(List.class);
        given(listMock.get(anyInt())).willReturn("prueba mia");

        //When
        String firstElement = listMock.get(0);

        //Then
        assertThat("prueba mia", Is.is(firstElement));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_throwAnException() {
        List listMock = mock(List.class);

        Mockito.when(listMock.get(anyInt())).thenThrow(new RuntimeException());
        listMock.get(0);
    }
}
