package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + '}';
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(
                1982, Calendar.APRIL, 2);
        User user1 = new User("John", 0, birthday);
        User user2 = new User("John", 0, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
        System.out.println();
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
    }
}
