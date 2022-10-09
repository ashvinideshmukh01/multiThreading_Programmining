package threadCoordination;

public class ThreadCoordinationIntteruptway {
    public static void main(String[] args) {
            BlockingTask thread = new BlockingTask();
            thread.start();
            thread.interrupt();
    }

    static  class BlockingTask extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(500000000);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted Exception!!");
            }
        }
    }
}
