public class Consumer extends Thread {

    private final SharedBuffer buffer;
    private final String name;
    private final String color;

    public Consumer(SharedBuffer buffer, String name, String color) {
        this.buffer = buffer;
        this.name = name;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            String word = buffer.consume();

            if (word == null) {
                break;
            }

            int length = word.length();
            String reversed = new StringBuilder(word).reverse().toString();

            System.out.println(color + name + " consumed: " + word +
                    " | Length: " + length +
                    " | Reversed: " + reversed + Main.RESET);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}