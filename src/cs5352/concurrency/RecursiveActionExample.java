package cs5352.concurrency;

import java.util.concurrent.RecursiveAction;

public class RecursiveActionExample extends RecursiveAction {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;

    RecursiveActionExample(int ID) {
        this.ID = ID;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName()
                + ", " + "ID: " + ID + "executing compute() method!");
    }
}
