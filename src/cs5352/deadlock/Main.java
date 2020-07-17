package cs5352.deadlock;

import java.util.stream.IntStream;

/**
 * Driver class for the 5 Dining Philosopher's program.
 * Philosophers are sitting at a round table. Each has a left and a right chopstick next to them.
 * There are a total of 5 philosophers and 5 chopsticks on the table. Can they all eat without starving?
 * That is - can the limited chopstick resources be shared without causing deadlock? We must avoid these conditions:
 *   - circular wait
 *   - no preemption
 *   - mutual exclusion
 *   - hold and wait
 */
public class Main {

    public static void main(String... args) {
        String[] names = {"Plato", "Aristotle", "Socrates", "Confucius", "Demosthenes"};
        Chopstick[] chopsticks;
        Philosopher[] philosopher = new Philosopher[5];

        chopsticks = IntStream.range(0, 5).mapToObj(Chopstick::new).toArray(Chopstick[]::new);

        IntStream.range(0, philosopher.length).forEach(i -> {
            // If we are at the last Philosopher, we have a problem to solve.
            // We need to give them a chopstick but we cannot simply do i+1 as that would be out of bounds
            // So, we loop around and we make their left chopstick the first chopstick. Now the first philosopher's right and the
            // last philosopher's left chopsticks are the same.
            if (i == philosopher.length - 1) {
                philosopher[i] = new Philosopher(chopsticks[i], chopsticks[0], names[i]);
            } else {
                philosopher[i] = new Philosopher(chopsticks[i], chopsticks[i + 1], names[i]);
            }
            philosopher[i].start();
        });
    }
} // end class Main
