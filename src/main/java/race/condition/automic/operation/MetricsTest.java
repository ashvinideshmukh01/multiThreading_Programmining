package race.condition.automic.operation;

public class MetricsTest extends  Thread {

    public static void main(String[] args) throws InterruptedException {

        Metrics metrics = new Metrics();
        Business businessThread1 = new Business(metrics);
        Business businessThread2 = new Business(metrics);
        MetricsPrinter printer = new MetricsPrinter(metrics);

        businessThread1.start();
        businessThread2.start();
        printer.start();

        businessThread1.join();
        businessThread2.join();
        printer.join();

    }
    public  static  class MetricsPrinter extends Thread{
        private Metrics metrics;
        public MetricsPrinter(Metrics metrics){
            this.metrics = metrics;
        }
        public void run(){
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Avereage is "+metrics.getAverage());
            }
        }
    }
    public static class Business extends Thread{

        private Metrics metrics;

        public Business(Metrics metrics){
            this.metrics = metrics;
        }

        public  void  run() {
            while (true){
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }

    }

    public static class Metrics{

        private long count = 0;
        private volatile double average = 0;

        public void addSample(long sample){
            double currentSum = average * count;
            count++;
            average = (currentSum + sample)/count;
        }

        public double getAverage(){
            return average;
        }
    }
}
