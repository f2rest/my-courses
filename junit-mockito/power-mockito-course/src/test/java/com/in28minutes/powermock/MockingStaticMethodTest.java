package com.in28minutes.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {

    @Mock
    Dependency dependencyMock;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void testRetriveTodosRelatedToSpring_usingAMock() {
        List<Integer> stats = Arrays.asList(1, 2, 3);

        Mockito.when(dependencyMock.retrieveAllStats()).thenReturn(stats);
        PowerMockito.mockStatic(UtilityClass.class);
        Mockito.when(UtilityClass.staticMethod(6)).thenReturn(150);

        int result = systemUnderTest.methodCallingAStaticMethod();
        Assert.assertEquals(150, result);

        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);

    }

}

