package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private SimpleArray<String> array;
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        array = new SimpleArray<>(3);
        iterator = array.iterator();
    }

    @Test
    public void whenAddThenGet() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        String rsl = array.get(2);
        assertThat("Third", is(rsl));
    }

    @Test
    public void whenAddThenGet2() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        String rsl = array.get(1);
        assertThat("Second", is(rsl));
    }

    @Test
    public void whenAddNullThenGet() {
        array.add("First");
        array.add(null);
        array.add("Third");
        assertNull(array.get(1));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddOutOfBounds() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.add("Exception");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenNoIndexForGet() {
        array.add("First");
        array.add("Second");
        array.get(5);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenNoIndexForGet2() {
        array.add("First");
        array.get(1);
    }

    @Test
    public void whenSetThenGet() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.set(0, "Replaced");
        String rsl = array.get(0);
        assertThat("Replaced", is(rsl));
    }

    @Test
    public void whenSetNullThenGet() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.set(0, null);
        assertNull(array.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenSetInEmptyArray() {
        array.set(0, "Exception");
    }

    @Test
    public void remove() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.remove(0);
        String rsl = array.get(0);
        assertThat("Second", is(rsl));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenNoIndexForRemove() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.remove(5);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenNoIndexForRemove2() {
        array.add("First");
        array.remove(1);
    }

    @Test
    public void iteratorHasNextTrue() {
        array.add("First");
        assertTrue(iterator.hasNext());
    }

    @Test
    public void iteratorNext() {
        array.add("First");
        String rsl = iterator.next();
        assertThat("First", is(rsl));
    }

    @Test
    public void iteratorHasNextFalse() {
        array.add("First");
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorMulti() {
        array.add("First");
        array.add("Second");
        array.add("Third");
        assertTrue(iterator.hasNext());
        assertThat("First", is(iterator.next()));
        assertTrue(iterator.hasNext());
        assertThat("Second", is(iterator.next()));
        assertTrue(iterator.hasNext());
        assertThat("Third", is(iterator.next()));
        assertFalse(iterator.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorNoElementException() {
        array.add("First");
        iterator.next();
        iterator.next();
    }
}