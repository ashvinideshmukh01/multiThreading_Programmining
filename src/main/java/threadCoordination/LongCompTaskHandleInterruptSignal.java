package threadCoordination;

import java.math.BigInteger;

public class LongCompTaskHandleInterruptSignal {
    public static void main(String[] args) {
        Thread t = new Thread(new ComputePower(new BigInteger("2"),new BigInteger("3")));
        t.start();
        t.interrupt();

    }
static  class ComputePower implements Runnable{
    private BigInteger base;
    private BigInteger power;

public ComputePower(BigInteger base, BigInteger power){
    this.base = base;
    this.power = power;
}
    @Override
    public void run() {
        System.out.println(pow(base,power));

    }

    private BigInteger pow(BigInteger base, BigInteger power) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0 ; i = i.add(BigInteger.ONE)) {
        if(Thread.currentThread().isInterrupted()){
            System.out.println("current thread interrupted");
            return  BigInteger.ZERO;
        }
            result = result.multiply(base);
        }
        return result;
    }
}
}
