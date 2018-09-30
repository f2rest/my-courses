package com.in28minutes.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
public class InvokingPrivateMethodTest {

    @Mock
    Dependency dependencyMock;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void testRetriveTodosRelatedToSpring_usingAMock() throws Exception {
        List<Integer> stats = Arrays.asList(1, 2, 3);

        Mockito.when(dependencyMock.retrieveAllStats()).thenReturn(stats);
        long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");

        Assert.assertEquals(6, result);
    }

}

