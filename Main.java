public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {

        SharedBuffer buffer = new SharedBuffer();

        String[] words = {
                "Elephant",
                "Computer",
                "Multithreading",
                "Producer",
                "Consumer",
                "Synchronization",
                "ReentrantLock",
                "Programming",
                "Assignment",
                "BufferData",
                "ThreadSafe",
                "JavaLanguage",
                "Concurrent",
                "ConditionVar",
                "ExecutionFlow"
        };

        Producer producer = new Producer(buffer, words, BLUE);

        Consumer c1 = new Consumer(buffer, "Consumer 1", RED);
        Consumer c2 = new Consumer(buffer, "Consumer 2", GREEN);
        Consumer c3 = new Consumer(buffer, "Consumer 3", YELLOW);

        producer.start();
        c1.start();
        c2.start();
        c3.start();

        try {
            producer.join();
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads finished.");
    }
}