package algorithms;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class SortingMain {

    /**
     * if {@code Sorting<Integer>} is declared as just {@code Sorting}, then compiler will issue warning
     * "unchecked call to member of raw type. More details here:
     * https://stackoverflow.com/questions/38036442/unchecked-call-to-member-of-raw-type?rq=1
     * @param args cli vars passed if needed
     */
    public static void main(String...args) {
        ArrayList<Integer> intList = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            int x = generateRandomInt();
            intList.add(x);
        }
        ArrayList<Integer> unsortedList = new ArrayList<>(intList);  // preserve unsorted array for other tests
        Sorting<Integer> sorting = new Sorting<>(intList);

        System.out.println("unsorted list to preserve: " + unsortedList.toString() + "\n");

        // BUBBLE SORT
        System.out.println("original list: " + sorting.getObjList().toString());
        sorting.bubbleSort();
        System.out.println("bubble sorted list: " + sorting.getObjList().toString() + "\n");
        sorting.setObjList(unsortedList);  // set back to unsorted list to clear

        // INSERTION SORT
        System.out.println("original list: " + sorting.getObjList().toString());
        sorting.insertionSort();
        System.out.println("insertion sorted list: " + sorting.getObjList().toString() + "\n");
        sorting.setObjList(unsortedList); // set back to unsorted list to clear

        // TODO: SELECTION SORT

        // TODO: MERGE SORT

        // TODO: QUICK SORT

        // TODO: HEAP SORT

        // TODO: RADIX SORT
    }

    private static int generateRandomInt() {
        Random r = new Random(System.nanoTime());
        return r.nextInt(100) + 1;
    }
}
