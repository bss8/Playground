package algorithms;

import java.util.ArrayList;
import java.util.Random;

/**
 * @param <T>
 */
public class Sorting<T extends Comparable<? super T>> implements SortingAlgorithms<T> {
    public void bubbleSort(T dataObj) {

    }

    public void insertionSort(ArrayList<T> objList) {

        for (int i = 1; i < objList.size(); i++) {
            T key = objList.get(i);
            int j = i - 1;

            while ((j >= 0) && objList.get(j).compareTo(key) > 0) {
                objList.set(j + 1, objList.get(j));
                j = j - 1;
            }
            objList.set(j + 1, key);
        }
    }

    @Override
    public void selectionSort(T dataObj) {

    }

    public void mergeSort(T dataObj) {

    }

    public void quickSort(T dataObj) {

    }

    public void heapSort(T dataObj) {

    }

    public void radixSort(T dataObj) {

    }

    private int generateRandomInt() {
        Random r = new Random(System.nanoTime());
        return r.nextInt(100) + 1;
    }

    public static void main(String...args) {
        ArrayList<Integer> intList = new ArrayList<>();
        Sorting sorting = new Sorting();

        for (int i = 0; i < 11; i++) {
            int x = sorting.generateRandomInt();
            intList.add(x);
        }

        System.out.println("Unsorted list: " + intList.toString());

        sorting.insertionSort(intList);

        System.out.println("Sorted list: " + intList.toString());
    }

}
