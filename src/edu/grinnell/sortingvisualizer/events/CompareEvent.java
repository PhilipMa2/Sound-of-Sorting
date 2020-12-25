package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T extends Comparable<T>> implements SortEvent<T> {
    private List<Integer> affectedIndices;
    private int i;
    private int j;
    public int result;

    public CompareEvent(int i, int j) {
        this.i = i;
        this.j = j;
        this.affectedIndices = new ArrayList<>();
    }

    @Override
    public void apply(T[] arr) {
        affectedIndices.add(0, i);
        affectedIndices.add(1, j);
        result = arr[i].compareTo(arr[j]);
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return affectedIndices;
    }

    @Override
    public boolean isEmphasized() {
        return false;
    }
}
    
