package threadCoordination;

public class ThreadBusyWait {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new FirstThread());
        Thread s = new Thread(new SecondThread(t));
        t.start();
        s.start();
        s.join();

    }
static  class FirstThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("first thread complated!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

static class SecondThread implements Runnable{
    Thread waitForFirstThread;
    SecondThread(Thread waitForFirstThread){
        this.waitForFirstThread = waitForFirstThread;
    }
    @Override
    public void run() {
        while(waitForFirstThread.isAlive()){

        }
        System.out.println("second thread completed!!");
    }
}
}
