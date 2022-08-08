package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && column == data[row].length) {
            column = 0;
            row++;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

    public static void main(String[] args) {
        int[][] in = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        MatrixIt it = new MatrixIt(in);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}