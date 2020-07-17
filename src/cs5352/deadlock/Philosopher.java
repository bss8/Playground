package cs5352.deadlock;

import java.util.Arrays;

/**
 * Class to represent a Philosopher; extends Tread so we may invoke have each Philosopher run in their own thread.
 */
public class Philosopher extends Thread {
    private Chopstick chopstick_left;
    private Chopstick chopstick_right;
    private String name;
    private int numTimesEaten;

    /**
     * Constructor to create a Philosopher, accepts the IDs of a left and right chopsticks and a name
     * @param chopstick_left
     * @param chopstick_right
     * @param name
     */
    Philosopher(Chopstick chopstick_left, Chopstick chopstick_right, String name) {
        this.chopstick_left = chopstick_left;
        this.chopstick_right = chopstick_right;
        this.name = name;
        this.numTimesEaten = 0;
    }

    /**
     * Once the thread starts, run for the number of iterations defined.
     * By default, each philosopher will attempt to eat 10 times
     */
    @Override
    public void run() {
        int numIterations = 1;
        while (numIterations < 4) {
            System.out.println(name + " is trying to eat, turn " + numIterations);
            eat();
            numIterations++;
        }
    }

    /**
     * Philosopher attempts to eat by picking up first the left chopstick, then the right.
     * If both are available, he or she may eat. After eating, the philosopher returns the
     * chopsticks back.
     */
    private void eat() {
        if (chopstick_left.pickUp()) {
            System.out.println(name + " took left chopstick.");
            if (chopstick_right.pickUp()) {
                System.out.println(name + " took right chopstick.");
                try {
                    this.numTimesEaten++;
                    System.out.println(name + " is eating. Successfully ate " + numTimesEaten + " times!");
                    sleep(500);  // brief pause
                } catch (InterruptedException ex) {
                    System.err.println(Arrays.toString(ex.getStackTrace()));
                }
                chopstick_right.putDown();
            }
            chopstick_left.putDown();
        }
    }
}
