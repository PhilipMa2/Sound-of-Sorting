package edu.grinnell.sortingvisualizer;

import java.util.Random;

/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {

    private Integer[] notes;
    private boolean[] highlights;

    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
        notes = new Integer[n];
        highlights = new boolean[n];
        for (int i = 0; i < n; i++) {
            notes[i] = i;
            highlights[i] = false;
        }
    }
    
    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size.  The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initializeAndShuffle(int n) {
        notes = new Integer[n];
        highlights = new boolean[n];
        for (int i = 0; i < n; i++) {
            notes[i] = i;
            highlights[i] = false;
        }

        Random r = new Random();
        for (int i = 0; i < 2 * n; i++) {
            int id1 = r.nextInt(n);
            int id2 = r.nextInt(n);

            int tmp = notes[id1];
            notes[id1] = notes[id2];
            notes[id2] = tmp;
        }
    }
    
    /** @return the indices of this NoteIndices object */
    public Integer[] getNotes() { 
        return notes;
    }

    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
        highlights[index] = true;
    }
    
    /** @return true if the given index is highlighted */
    public boolean isHighlighted(int index) {
        return highlights[index];
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
        for (int i = 0; i < highlights.length; i++) {
            highlights[i] = false;
        }
    }
}
