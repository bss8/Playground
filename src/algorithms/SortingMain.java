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
        long duration = 0L;
        for (int i = 0; i < 15001; i++) {
            int x = generateRandomInt();
            intList.add(x);
        }
        ArrayList<Integer> unsortedList = new ArrayList<>(intList);  // preserve unsorted array for other tests
        Sorting<Integer> sorting = new Sorting<>(intList);

        System.out.println("unsorted list to preserve: " + unsortedList.toString() + "\n");

        // BUBBLE SORT
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.bubbleSort();
        System.out.println("bubble sorted list: " + sorting.getObjList().toString());
        System.out.println("duration: " + duration + "\n");
        sorting.setObjList(unsortedList);  // set back to unsorted list to clear

        // INSERTION SORT
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.insertionSort();
        System.out.println("insertion sorted list: " + sorting.getObjList().toString() + "\n");
        System.out.println("duration: " + duration + "\n");
        sorting.setObjList(unsortedList); // set back to unsorted list to clear

        // TODO: SELECTION SORT

        // MERGE SORT
        System.out.println("original list: " + sorting.getObjList().toString());
        duration = sorting.mergeSort(0, 14999);  // low is start index, high is size - 1
        System.out.println("merge sorted list: " + sorting.getObjList().toString());
        System.out.println("duration: " + duration + "\n");
        sorting.setObjList(unsortedList); // set back to unsorted list to clear

        // TODO: QUICK SORT

        // TODO: HEAP SORT

        // TODO: RADIX SORT
    }

    private static int generateRandomInt() {
        Random r = new Random(System.nanoTime());
        return r.nextInt(15000) + 1;
    }

    private static int findMax(ArrayList<Integer> list) {
        int max = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }

        return max;
    }

    private static int findMin(ArrayList<Integer> list) {
        int min = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }

        return min;
    }
}
