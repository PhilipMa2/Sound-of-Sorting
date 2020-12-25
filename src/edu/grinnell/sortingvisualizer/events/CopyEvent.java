package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CopyEvent<T extends Comparable<T>> implements SortEvent<T> {
    private List<Integer> affectedIndices;
    private T val;
    private int ind;

    public CopyEvent(int ind, T val) {
        this.ind = ind;
        this.val = val;
        this.affectedIndices = new ArrayList<>();
        affectedIndices.add(ind);
    }

    @Override
    public void apply(T[] arr) {
        arr[ind] = val;
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
