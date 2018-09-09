package helper;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class ArraysCompareTest {

    //Array.s.sort
    @Test
    public void testArraySort_RandomArray (){
        int [] numbers = {23, 3, 7, 1};
        int [] expected = {1, 3, 7, 23};

        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers);
    }

    @Test(expected = NullPointerException.class)
    public void testArraySort_NullArray () {
        int[] numbers = null;

        Arrays.sort(numbers);
    }

    @Test(timeout = 100)
    public void testSort_Performance(){
        int [] array = {23, 3, 7, 1};
        for (int i = 1; i <= 1000000; i++){
            array[0] = i;
            Arrays.sort(array);
        }
    }
}