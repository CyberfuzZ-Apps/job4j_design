package ru.job4j.gc;

public class GCDemo {
    private static final long KB = 1024;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory() / KB;
        final long totalMemory = ENVIRONMENT.totalMemory() / KB;
        final long maxMemory = ENVIRONMENT.maxMemory() / KB;
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
        System.out.printf("Max: %d%n", maxMemory);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 1000; i++) {
            new User("Evgeniy ", i);
        }
        info();
    }
}
