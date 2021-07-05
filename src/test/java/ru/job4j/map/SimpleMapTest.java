package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    private SimpleMap<Integer, Integer> map;

    @Before
    public void setUp() {
        map = new SimpleMap<>();
    }

    @Test
    public void whenPut() {
        map.put(111, 111);
        map.put(222, 222);
        map.put(333, 333);
        assertEquals(3, map.size());
    }

    @Test
    public void whenPutDuplicate() {
        map.put(111, 111);
        map.put(222, 222);
        map.put(111, 111);
        map.put(222, 222);
        assertEquals(2, map.size());
    }

    @Test
    public void whenPutTrue() {
        assertTrue(map.put(111, 111));
    }

    @Test
    public void whenPutFalse() {
        map.put(111, 111);
        assertFalse(map.put(111, 111));
    }

    @Test
    public void whenPutNullKey() {
        assertTrue(map.put(null, 111));
    }

    @Test
    public void whenGet() {
        map.put(111, 111);
        assertThat(111, is(map.get(111)));
    }

    @Test
    public void whenNoGet() {
        map.put(111, 111);
        assertNull(map.get(222));
    }

    @Test
    public void whenGetNullKey() {
        map.put(null, 111);
        assertThat(111, is(map.get(null)));
    }

    @Test
    public void whenRemove() {
        map.put(111, 111);
        map.put(222, 222);
        assertEquals(2, map.size());
        assertTrue(map.remove(111));
        assertEquals(1, map.size());
    }

    @Test
    public void whenNotRemove() {
        map.put(111, 111);
        map.put(222, 222);
        assertFalse(map.remove(333));
        assertEquals(2, map.size());
    }

    @Test
    public void iterator() {
        map.put(111, 111);
        map.put(222, 222);
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator2() {
        map.put(111, 111);
        map.put(222, 222);
        map.put(333, 333);
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test (expected = ConcurrentModificationException.class)
    public void iteratorException() {
        map.put(111, 111);
        map.put(222, 222);
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        map.put(333, 333);
        iterator.hasNext();
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorException2() {
        map.put(111, 111);
        map.put(222, 222);
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
        iterator.next();
    }
}