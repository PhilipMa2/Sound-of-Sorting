package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.grinnell.sortingvisualizer.events.SortEvent;
import edu.grinnell.sortingvisualizer.utils.TestUtils;

public class SortsTest {

    void checkQuicksort(Integer expected[], Integer[] values) {
        Integer[] copy = values.clone();
        List<SortEvent<Integer>> lst = Sorts.quickSort(values);
        Sorts.eventSort(copy, lst);
        assertArrayEquals(expected, values);
        assertArrayEquals(expected, copy);
    } 

    void checkQuicksort(Integer[] sorted) {
        Integer[] copy = sorted.clone();
        TestUtils.randomlyPermute(copy);
        checkQuicksort(sorted, copy);
    }

    void checkInsertionsort(Integer expected[], Integer[] values) {
        Integer[] copy = values.clone();
        List<SortEvent<Integer>> lst = Sorts.insertionSort(values);
        Sorts.eventSort(copy, lst);
        assertArrayEquals(expected, values);
        assertArrayEquals(expected, copy);
    } 

    void checkInsertionsort(Integer[] sorted) {
        Integer[] copy = sorted.clone();
        TestUtils.randomlyPermute(copy);
        checkInsertionsort(sorted, copy);
    }

    void checkMergesort(Integer expected[], Integer[] values) {
        Integer[] copy = values.clone();
        List<SortEvent<Integer>> lst = Sorts.mergeSort(values);
        Sorts.eventSort(copy, lst);
        assertArrayEquals(expected, values);
        assertArrayEquals(expected, copy);
    } 

    void checkMergesort(Integer[] sorted) {
        Integer[] copy = sorted.clone();
        TestUtils.randomlyPermute(copy);
        checkMergesort(sorted, copy);
    }

    void checkSelectionsort(Integer expected[], Integer[] values) {
        Integer[] copy = values.clone();
        List<SortEvent<Integer>> lst = Sorts.selectionSort(values);
        Sorts.eventSort(copy, lst);
        assertArrayEquals(expected, values);
        assertArrayEquals(expected, copy);
    } 

    void checkSelectionsort(Integer[] sorted) {
        Integer[] copy = sorted.clone();
        TestUtils.randomlyPermute(copy);
        checkSelectionsort(sorted, copy);
    }

    void checkBubblesort(Integer expected[], Integer[] values) {
        Integer[] copy = values.clone();
        List<SortEvent<Integer>> lst = Sorts.bubbleSort(values);
        Sorts.eventSort(copy, lst);
        assertArrayEquals(expected, values);
        assertArrayEquals(expected, copy);
    } 

    void checkBubblesort(Integer[] sorted) {
        Integer[] copy = sorted.clone();
        TestUtils.randomlyPermute(copy);
        checkBubblesort(sorted, copy);
    }

    @Test
    void testEmpty() {
        checkQuicksort(new Integer[0]);
        checkMergesort(new Integer[0]);
        checkInsertionsort(new Integer[0]);
        checkSelectionsort(new Integer[0]);
        checkBubblesort(new Integer[0]);
    } // testEmpty

    @Test
    void testOrdered() {
        for (int size = 1; size < 20; size++) {
            Integer[] sorted = new Integer[size];
            for (int i = 0; i < size; i++) {
                sorted[i] = i;
            } // for
            checkQuicksort(sorted, sorted.clone());
            checkMergesort(sorted, sorted.clone());
            checkInsertionsort(sorted, sorted.clone());
            checkSelectionsort(sorted, sorted.clone());
            checkBubblesort(sorted, sorted.clone());
        } // for
    } // testOrdered

    @Test
    void testBackwards() {
        for (int size = 1; size < 20; size++) {
            Integer[] sorted = new Integer[size];
            Integer[] backwards = new Integer[size];
            for (int i = 0; i < size; i++) {
                backwards[i] = size - i;
                sorted[i] = i + 1;
            } // for
            checkQuicksort(sorted, backwards);
            checkMergesort(sorted, backwards);
            checkInsertionsort(sorted, backwards);
            checkSelectionsort(sorted, backwards);
            checkBubblesort(sorted, backwards);
        } // for
    } // testBackwards

    @Test
    void testRandom() {
        for (int trials = 0; trials < 5; trials++) {
            // Some comparatively small ones
            for (int size = 1; size < 20; size++) {
                checkQuicksort(TestUtils.randomInts(size));
                checkMergesort(TestUtils.randomInts(size));
                checkInsertionsort(TestUtils.randomInts(size));
                checkSelectionsort(TestUtils.randomInts(size));
                checkBubblesort(TestUtils.randomInts(size));
            } // for size
              // Some larger ones
            for (int size = 30; size < 1000; size = size * 2 + 1) {
                checkQuicksort(TestUtils.randomInts(size));
                checkMergesort(TestUtils.randomInts(size));
                checkInsertionsort(TestUtils.randomInts(size));
                checkSelectionsort(TestUtils.randomInts(size));
                checkBubblesort(TestUtils.randomInts(size));
            } // for size
        } // for trials
    } // testRandom()
}
