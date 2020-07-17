package cs5352.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoin extends ForkJoinTask<Integer> implements Runnable {
    private final int ID;

    ForkJoin(int ID) {
        this.ID = ID;
    }
    public static void main(String...args) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);

        RecursiveActionExample recursiveAction = new RecursiveActionExample(1);

        ForkJoinTask<Void> recursiveActionExample = recursiveAction.fork();

        recursiveActionExample.fork();
    }

    @Override
    public void run() {
        System.out.println("Hi, I am thread with ID: " + this.ID);
        System.out.println(Thread.currentThread().getName()
                + ", executing run() method!");
    }

    @Override
    public Integer getRawResult() {
        return 0;
    }

    @Override
    protected void setRawResult(Integer i) {

    }

    @Override
    protected boolean exec() {
        return false;
    }
}
