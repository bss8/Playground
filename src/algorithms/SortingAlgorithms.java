package algorithms;

import java.util.ArrayList;

public interface SortingAlgorithms<T> {
    void bubbleSort(T dataObj);
    void insertionSort(ArrayList<T> objList);
    void selectionSort(T dataObj);
    void mergeSort(T dataObj);
    void quickSort(T dataObj);
    void heapSort(T dataObj);
    void radixSort(T dataObj);


}
