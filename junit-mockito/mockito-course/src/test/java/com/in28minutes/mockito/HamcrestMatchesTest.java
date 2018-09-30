package com.in28minutes.mockito;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsArrayWithSize;
import org.hamcrest.text.IsEmptyString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchesTest {

    @Test
    public void test() {
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

        Assert.assertThat(scores, Matchers.hasSize(4));
        Assert.assertThat(scores, Matchers.hasItems(99, 100));

        //every item : > 90
        Assert.assertThat(scores, CoreMatchers.everyItem(Matchers.greaterThan(90)));

        //every item < : 190
        Assert.assertThat(scores, CoreMatchers.everyItem(Matchers.lessThan(190)));

        //String
        Assert.assertThat("", IsEmptyString.isEmptyString());
        Assert.assertThat(null, IsEmptyString.isEmptyOrNullString());

        //Arrays
        Integer [] marks = {1, 2, 3};
        Assert.assertThat(marks, IsArrayWithSize.arrayWithSize(3));
        //Array containing all elements
        Assert.assertThat(marks, Matchers.arrayContaining(1, 2, 3));
        Assert.assertThat(marks, Matchers.arrayContainingInAnyOrder(1, 3, 2));

    }
}
