package data.sharing.between.threads;

public class DataSharingDemo {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter =new InventoryCounter();
        Thread increment = new Thread(new IncrementInventoryCounter(inventoryCounter));
        Thread decrement = new Thread(new DecrementInventoryCounter(inventoryCounter));
        increment.start();decrement.start();
        increment.join();
        decrement.join();
        inventoryCounter.getItem();

    }
    public static class IncrementInventoryCounter implements  Runnable{
        InventoryCounter inventoryCounter ;
        public  IncrementInventoryCounter(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }

        }
    }
    public static class DecrementInventoryCounter implements Runnable{
        InventoryCounter inventoryCounter;
        public DecrementInventoryCounter(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }
    public static class InventoryCounter{
        private  int  item = 0;
       synchronized public  void increment(){
            item++;
        }
        synchronized public  void decrement(){
            item--;
        }
        public void getItem(){
            System.out.println(item);
        }
    }
}
