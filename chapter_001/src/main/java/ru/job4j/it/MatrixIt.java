package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int[] arr : data) {
            if (arr.length != 0) {
                return true;
            }
        }
        return ((row < data.length) && (column < data[row].length));
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (data[row].length == 0) {
            row++;
        }
        int element = data[row][column];
        column++;
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
        }
        return element;
    }
}
