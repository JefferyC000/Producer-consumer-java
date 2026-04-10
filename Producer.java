public class Producer extends Thread {

    private final SharedBuffer buffer;
    private final String[] words;
    private final String color;

    public Producer(SharedBuffer buffer, String[] words, String color) {
        this.buffer = buffer;
        this.words = words;
        this.color = color;
    }

    @Override
    public void run() {
        for (String word : words) {
            buffer.produce(word);
            System.out.println(color + "Producer produced: " + word + Main.RESET);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer.setFinished();
    }
}