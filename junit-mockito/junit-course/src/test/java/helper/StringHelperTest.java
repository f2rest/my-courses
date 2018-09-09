package helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringHelperTest {

    //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
    private StringHelper stringHelper;
    private static final String erroMessage = "Because the input value is: ";

    @Before
    public void before(){
        stringHelper = new StringHelper();
    }

    @Test
    public void testTruncateAInFirst2Positions_AinFirst2Position(){
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("AACD"));
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));

    }

    @Test
    public void testTruncateAInFirst2Position_AinFirstPosition(){
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    public void testTruncateAInFirst2Position_NoAinFirst2Positions(){
        assertEquals("CDEF", stringHelper.truncateAInFirst2Positions("CDEF"));
        assertEquals("CDAA", stringHelper.truncateAInFirst2Positions("CDAA"));
    }

    //ABCD => false, ABAB => true, AB => true, A => false,
    @Test
    public void tesTareFirstAndLastTwoCharactersTheSame_BasicNegativeScenario(){
        String inputValue1 = "ABCD";
        String inputValue2 = "A";
        assertFalse(erroMessage + inputValue1,stringHelper.areFirstAndLastTwoCharactersTheSame(inputValue1));
        assertFalse(erroMessage + inputValue2,stringHelper.areFirstAndLastTwoCharactersTheSame(inputValue2));

    }

    @Test
    public void tesTareFirstAndLastTwoCharactersTheSame_BasicPositiveScenario(){
        String inputValue1 = "ABAB";
        String inputValue2 = "AB";
        assertTrue(erroMessage + inputValue1, stringHelper.areFirstAndLastTwoCharactersTheSame(inputValue1));
        assertTrue(erroMessage + inputValue2, stringHelper.areFirstAndLastTwoCharactersTheSame(inputValue2));
    }
}