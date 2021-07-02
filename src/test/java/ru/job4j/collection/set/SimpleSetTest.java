package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAdd() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertTrue(set.add(2));
        assertTrue(set.contains(2));
        assertFalse(set.add(1));
    }

    @Test
    public void whenContainsEmptySet() {
        Set<Integer> set = new SimpleSet<>();
        assertFalse(set.contains(1));
    }

    @Test
    public void whenIterate() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertThat(1, is(it.next()));
        assertTrue(it.hasNext());
        assertThat(2, is(it.next()));
        assertFalse(it.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateException() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertThat(1, is(it.next()));
        assertFalse(it.hasNext());
        it.next();
    }

}