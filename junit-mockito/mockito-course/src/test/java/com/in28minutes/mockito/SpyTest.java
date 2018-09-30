package com.in28minutes.mockito;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;


public class SpyTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ArrayList arrayListMock;

    @Spy
    ArrayList arrayListSpy;

    @Test
    public void testArrayListWhitMock() {
        //mock return the default value
        Assert.assertEquals(0, arrayListMock.size());

        Mockito.stub(arrayListMock.size()).toReturn(5);
        arrayListMock.add("Test");
        Assert.assertEquals(5, arrayListMock.size());
    }

    @Test
    public void testArrayListWhitSpy() {
        //mock return the default value
        Assert.assertEquals(0, arrayListSpy.size());
        arrayListSpy.add("Test");
        Assert.assertEquals(1, arrayListSpy.size());
    }

    @Test
    public void testArrayListWhitSpyOverrideMethod() {
        //mock return the default value
        Assert.assertEquals(0, arrayListSpy.size());
        Mockito.stub(arrayListSpy.size()).toReturn(5);
        Assert.assertEquals(5, arrayListSpy.size());
    }

    @Test
    public void testArrayListWhitSpyCheckMethodCall() {
        arrayListSpy.add("Dummy");
        Mockito.verify(arrayListSpy).add("Dummy");
        Mockito.verify(arrayListSpy, Mockito.never()).clear();
    }
}
