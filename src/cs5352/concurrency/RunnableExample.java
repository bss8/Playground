package cs5352.concurrency;

public class RunnableExample implements Runnable {

    public static void main(String[] args)
    {
        System.out.println("Main thread is- "
                + Thread.currentThread().getName());

        Thread t1 = new Thread(new RunnableExample());
        t1.start();
    }



        public void run()
        {
            System.out.println(Thread.currentThread().getName()
                    + ", executing run() method!");
        }

}


