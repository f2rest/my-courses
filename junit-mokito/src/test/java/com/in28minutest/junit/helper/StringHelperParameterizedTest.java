package com.in28minutest.junit.helper;

import com.in28minutes.junit.helper.StringHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

    //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
    private StringHelper stringHelper;
    private String input;
    private String expectedOutput;

    public StringHelperParameterizedTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection testConditions(){
        String expectedOutputs [] [] = {{"AAD", "CD"}, {"ACD", "CD"}, {"ABAB", "daskash"}};

        return Arrays.asList(expectedOutputs);
    }

    @Before
    public void before(){
        stringHelper = new StringHelper();
    }

    @Test
    public void testTruncateAInFirst2Positions(){
        assertEquals(expectedOutput, stringHelper.truncateAInFirst2Positions(input));
    }
}