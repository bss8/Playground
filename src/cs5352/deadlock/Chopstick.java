package cs5352.deadlock;

import java.util.concurrent.Semaphore;

/**
 * Class for a chopstick, represented as a semaphore to control the acquisition and release of this limited resource
 * When someone attempts to pick up a chopstick they will try to acquire it and do so only if it is not used by
 * anyone else.
 */
public class Chopstick {
    private Semaphore chopstick;
    public final int ID;

    /**
     *
     * @param ID identifies the chopstick with a unique int value
     */
    Chopstick(int ID) {
        // "Parameters: permits - the initial number of permits available.
        //  This value may be negative, in which case releases must occur before any acquires will be granted."
        this.chopstick = new Semaphore(1);
        this.ID = ID;
    }

    /**
     * Accessor method to get the ID but no mutator setID is provided
     * because we do not want to change the ID after it is created.
     * @return ID - the chopstick's unique identifier
     */
    public int getID() {
        return ID;
    }

    /**
     * Allows chopstick to be picked up if it is available
     * @return true if chopstick was acquired, false otherwise
     */
    public boolean pickUp() {
        return chopstick.tryAcquire();
    }

    /**
     * Releases the chopstick and makes it available to others
     */
    public void putDown() {
        chopstick.release();
    }
} // end class Chopstick