package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.sortingvisualizer.events.CompareEvent;
import edu.grinnell.sortingvisualizer.events.CopyEvent;
import edu.grinnell.sortingvisualizer.events.SortEvent;
import edu.grinnell.sortingvisualizer.events.SwapEvent;

public class Sorts {
    public static <T extends Comparable<T>> void eventSort(T[] arr, List<SortEvent<T>> events) {
        for (int i = 0; i < events.size(); i++) {
            events.get(i).apply(arr);
        }
    }

    public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minId = i;
            for (int j = i + 1; j < n; j++) {
                CompareEvent<T> compare = new CompareEvent<>(j, minId);
                events.add(compare);
                if (arr[j].compareTo(arr[minId]) < 0)
                    minId = j;
            }

            SwapEvent<T> swap = new SwapEvent<>(i, minId);
            events.add(swap);
            swap(arr, i, minId);
        }
        return events;
    }

    public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                CompareEvent<T> c = new CompareEvent<>(j - 1, j);
                c.apply(arr);
                events.add(c);
                if (c.result <= 0) {
                    continue;
                } else {
                    SwapEvent<T> s = new SwapEvent<>(j, j - 1);
                    s.apply(arr);
                    events.add(s);
                }
            }
        }
        return events;
    }

    public static <T extends Comparable<T>> List<SortEvent<T>> bubbleSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                events.add(new CompareEvent<T>(j, j + 1));
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    events.add(new SwapEvent<T>(j, j + 1));
                    swap(arr, j, j + 1);
                }
            }
        }
        return events; 
    }

    public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        mergeSortH(arr, 0, arr.length, events);
        return events;
    }

    public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        quickSortH(arr, 0, arr.length, events);
        return events;
    }

    static <T extends Comparable<T>> void mergeSortH(T[] arr, int lo, int hi, List<SortEvent<T>> events) {
        if (lo < hi - 1) {
            int mid = (lo + hi) / 2;
            mergeSortH(arr, lo, mid, events);
            mergeSortH(arr, mid, hi, events);
            merge(arr, lo, mid, hi, events);
        }
    }

    static <T extends Comparable<T>> void merge(T[] arr, int lo, int mid, int hi, List<SortEvent<T>> events) {
        List<T> tmp = new ArrayList<>();
        int loT = lo;
        int midT = mid;
        for (int i = lo; i < hi; i++) {
            if (loT == mid)
                tmp.add(arr[midT++]);
            else if (midT == hi) 
                tmp.add(arr[loT++]);
            else {
                if (arr[loT].compareTo(arr[midT]) <= 0)
                    tmp.add(arr[loT++]);
                else 
                    tmp.add(arr[midT++]);
            }
        }
        for (int i = lo, j = 0; i < hi; i++, j++) {
            events.add(new CopyEvent<T>(i, tmp.get(j)));
            arr[i] = tmp.get(j);
        }
    }

    private static <T extends Comparable<T>> void quickSortH(T[] arr, int lb, int ub, List<SortEvent<T>> events) {
        if (ub - lb <= 1) {
            return;
        }

        int r = 0;
        int b = ub;

        int pivot = medianOfThree(arr, lb, ub - 1);
        events.add(new SwapEvent<T>(pivot, ub - 1));
        swap(arr, pivot, ub - 1);
        int front = lb;
        int end = ub - 2;
        while (front < end) {
            events.add(new CompareEvent<>(front, ub - 1));
            if (arr[front].compareTo(arr[ub - 1]) >= 0) {
                events.add(new SwapEvent<T>(front, end));
                swap(arr, front, end);
                end--;
            } else {
                front++;
            }
        }
        events.add(new CompareEvent<>(end, ub - 1));
        if (arr[end].compareTo(arr[ub - 1]) < 0)
            end++;
        r = end;
        end = ub - 2;
        front = r;
        while (front < end) {
            events.add(new CompareEvent<>(front, ub - 1));
            if (arr[front].compareTo(arr[ub - 1]) > 0) {
                events.add(new SwapEvent<T>(front, end));
                swap(arr, front, end);
                end--;
            } else 
                front++;
        }
        events.add(new CompareEvent<>(end, ub - 1));
        if (arr[end].compareTo(arr[ub - 1]) <= 0)
            end++;
        b = end + 1;
        events.add(new SwapEvent<T>(end, ub - 1));
        swap(arr, end, ub - 1);
        quickSortH(arr, lb, r, events);
        quickSortH(arr, b, ub, events);
    }

    private static <T extends Comparable<T>> int medianOfThree(T[] arr, int left, int right) {
        if (right < left)
            return left;
        int mid = (right - left) / 2;
        if (arr[mid].compareTo(arr[right]) <= 0 && arr[mid].compareTo(arr[left]) >= 0)
            return mid;
        else if (arr[right].compareTo(arr[mid]) <= 0 && arr[right].compareTo(arr[left]) >= 0) 
            return right;
        else 
            return left;
    }

    private static <T> void swap(T[] values, int i, int j) {
        T tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

}