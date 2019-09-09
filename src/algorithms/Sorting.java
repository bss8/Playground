package algorithms;

import java.util.ArrayList;

/**
 *
 * {@code compareTo()} returns a negative int if this.object is less than, zero if equal, or a positive if greater than
 * the specified object.
 *
 * {@code <? super T>} T is is the lower bound for the wildcard ?
 * {@code extends Comparable<? super T>} type T must implement Comparable of T or one of its super classes
 *
 * Use @Override annotation to take advantage of the compiler checking to make sure you actually are overriding a method.
 *
 * @param <T>
 */
public class Sorting<T extends Comparable<? super T>> implements SortingAlgorithms<T> {

    private ArrayList<T> objList;

    Sorting(ArrayList<T> unsortedList) {
        this.objList = unsortedList;
    }

    void setObjList(ArrayList<T> objList) {
        this.objList = objList;
    }

    ArrayList getObjList() {
        return this.objList;
    }

    /**
     * O(n^2) sorting algorithm, due to number of passes
     */
    @Override
    public void bubbleSort() {
        boolean swapPerformed;

        do {
            swapPerformed = false;

            for (int i = 0; i < objList.size() - 1; i++) {
                if (objList.get(i).compareTo(objList.get(i + 1)) > 0) {
                    T tmp = objList.get(i + 1);
                    objList.set(i + 1, objList.get(i));
                    objList.set(i, tmp);

                    swapPerformed = true;
                }
            }
        } while (swapPerformed);
    }

    /**
     * O(n^2) sorting algorithm, due to nested loops
     */
    @Override
    public void insertionSort() {

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

    /**
     *
     */
    @Override
    public void selectionSort() {

    }

    /**
     *
     */
    @Override
    public void mergeSort() {

    }

    /**
     *
     */
    @Override
    public void quickSort() {

    }

    /**
     *
     */
    @Override
    public void heapSort() {

    }

    /**
     *
     */
    @Override
    public void radixSort() {

    }
} // end class
