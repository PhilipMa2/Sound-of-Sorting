package edu.grinnell.sortingvisualizer.events;

import java.util.List;

public interface SortEvent<T extends Comparable<T>> {
    /**
     * Applies this sort event to the given list
     * @param arr
     */
    void apply(T[] arr);

    /**
     * @return a list containing all of the indices that this event affects
     */
    List<Integer> getAffectedIndices();

    /**
     * @return true if this event should be emphasized by visualizer/
     *         audibilizer.
     */
    boolean isEmphasized();
}
