package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T extends Comparable<T>> implements SortEvent<T> {
    private List<Integer> affectedIndices;
    private int i;
    private int j;

    public SwapEvent(int i, int j) {
        this.i = i;
        this.j = j;
        affectedIndices = new ArrayList<>();
        affectedIndices.add(i);
        affectedIndices.add(j);
    }

    @Override
    public void apply(T[] arr) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return affectedIndices;
    }

    @Override
    public boolean isEmphasized() {
        return true;
    }
}
