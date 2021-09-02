package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SoftDemo {
    public static void main(String[] args) {
        //example1();
        //example2();
        String s = new String("Hello");
        String s1 = "Hello";
        System.out.println(s == s1); // false (ссылки не равны)

        String st = new String("Hello").intern();
        String st1 = "Hello";
        System.out.println(st == st1); // true (ссылки равны)
        // в st1 вернулась ссылка на ""Hello" из пула строк
    }

    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        // do something
        strong = soft.get();
        if (strong == null) {
            strong = new Object();
        }
        System.out.println(strong);

        Object strong1 = new Object();
        WeakReference<Object> weak = new WeakReference<>(strong1);
        strong1 = null;
        // do something
        strong1 = weak.get();
        if (strong1 == null) {
            strong1 = new Object();
        }
        System.out.println(strong1);
    }

    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

}
