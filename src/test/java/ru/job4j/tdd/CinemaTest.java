package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CinemaTest {

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session ses1 = new Session3D();
        Session ses2 = new Session3D();
        cinema.add(ses1);
        cinema.add(ses2);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(ses1, ses2)));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 13, 32, 25, 61);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 1, 1, 21, 0);
        Ticket ticket = cinema.buy(account, 0, 0, date);
    }

}