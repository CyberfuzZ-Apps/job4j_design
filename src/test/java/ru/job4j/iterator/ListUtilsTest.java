package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddBeforeFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 0, 5);
        assertThat(Arrays.asList(5, 1, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.addAfter(input, 2, 5);
        assertThat(Arrays.asList(0, 1, 2, 5, 3), Is.is(input));
    }

    @Test
    public void whenAddAfter2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.addAfter(input, 0, 5);
        assertThat(Arrays.asList(0, 5, 1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenRemoveIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, x -> x % 2 == 0);
        assertThat(Arrays.asList(1, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfLessThan2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, x -> x < 2);
        assertThat(Arrays.asList(2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveIfNotSatisfied() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, x -> x == 5);
        assertThat(Arrays.asList(0, 1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenReplaceIfOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, x -> x % 2 != 0, 0);
        assertThat(Arrays.asList(0, 0, 2, 0, 4), Is.is(input));
    }

    @Test
    public void whenReplaceIfLessThan3() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, x -> x < 3, 0);
        assertThat(Arrays.asList(0, 0, 0, 3, 4), Is.is(input));
    }

    @Test
    public void whenReplaceIfNotSatisfied() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, x -> x == 5, 0);
        assertThat(Arrays.asList(0, 1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> remove = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(input, remove);
        assertThat(Arrays.asList(0, 2, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAllNotSatisfied() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> remove = new ArrayList<>(Arrays.asList(5, 6));
        ListUtils.removeAll(input, remove);
        assertThat(Arrays.asList(0, 1, 2, 3, 4), Is.is(input));
    }
}