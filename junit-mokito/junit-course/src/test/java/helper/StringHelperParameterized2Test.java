package helper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StringHelperParameterized2Test {

    //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
    private StringHelper stringHelper;
    private String input;

    public StringHelperParameterized2Test(String input) {
        this.input = input;
    }

    @Parameterized.Parameters
    public static Collection testConditions(){
        String expectedOutputs [] [] = {{"AAA"}, {"ACD"}, {"ABAB"}};

        return Arrays.asList(expectedOutputs);
    }

    @Before
    public void before(){
        stringHelper = new StringHelper();
    }


    @Test
    public void tesTareFirstAndLastTwoCharactersTheSame(){
        assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame(input));
    }
}