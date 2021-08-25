package ru.job4j.gc;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
//        User user1 = new User("Evgeniy", 39);
//        User user2 = new User("Evgeniy Alekseevich Petrov", 23);
        for (int i = 0; i < 100; i++) {
            new Person(i, "N" + i);
//            new User("Evgeniy", 39);
        }
        System.gc();
        info();
    }
}
