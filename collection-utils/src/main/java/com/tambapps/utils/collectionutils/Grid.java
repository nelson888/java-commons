package com.tambapps.utils.collectionutils;

import java.util.Arrays;
import java.util.Objects;

/**
 * A complex 2-dimensional array of size M, N. <br>
 * M is the number of rows, N is the number of columns
 */
@SuppressWarnings("unchecked")
public class Grid<T> { // TODO make me extends collection and make function toIterable for Column and Row?

  public interface Vector<T> {
    T getAt(int i);
    void setAt(int i, T value);
    int getSize();
  }

  private final int M; // number of rows
  private final int N; // number of columns
  private final Object[] array; // we store the 2D array in a 1D array of size N * M

  /**
   * Creates a 2D array of size M, N
   *
   * @param M the number of rows
   * @param N the number of columns
   */
  public Grid(int M, int N) {
    this.M = M;
    this.N = N;
    array = new Object[M * N];
  }

  /**
   * Creates a 2D array of size M, N with the given values.
   *
   * @param M      the number of rows
   * @param N      the number of columns
   * @param values the values
   */
  public Grid(int M, int N, T[] values) {
    if (values.length != M * N) {
      throw new IllegalArgumentException("Array should be of size N * M: " + M * N);
    }
    this.M = M;
    this.N = N;
    array = Arrays.copyOf(values, values.length);
  }

  /**
   * Get the complex number at the index [row][col]
   *
   * @param row the row
   * @param col the col
   * @return the complex at the given indexes
   */
  public T get(int row, int col) {
    checkIndex(row, col);
    return (T) array[getIndex(row, col)];
  }

  /**
   * Get the complex number at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the 1d index
   * @return the complex at the given index
   */
  public T get(int i) {
    checkIndex(i);
    return (T) array[i];
  }

  /**
   * Sets the complex at the index [row][col]
   *
   * @param row the row
   * @param col the col
   */
  public void set(int row, int col, T value) {
    checkIndex(row, col);
    array[getIndex(row, col)] = Objects.requireNonNull(value, "Cannot set a null value");
  }

  /**
   * Sets the complex number at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the column index
   */
  public void set(int i, T value) {
    checkIndex(i);
    array[i] = Objects.requireNonNull(value, "Cannot set a null value");
  }

  private void checkIndex(int row, int col) {
    if (row < 0 || row >= getM() || col < 0 || col >= getN()) {
      throw new IndexOutOfBoundsException(String
        .format("Tried to access index (%d, %d) of array of size (%d, %d)", row, col, getM(),
          getN()));
    }
  }

  private void checkIndex(int i) {
    if (i < 0 || i >= array.length) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access index %d of array of size %d", i, getM() * getN()));
    }
  }

  private int getIndex(int row, int col) {
    return row * getN() + col;
  }

  /**
   * Returns the number of rows
   *
   * @return the number of rows
   */
  public int getM() {
    return M;
  }

  /**
   * Returns the number of columns
   *
   * @return the number of columns
   */
  public int getN() {
    return N;
  }

  /**
   * Returns the row at the index i
   *
   * @param i the index
   * @return the i-th row
   */
  public Vector<T> getRow(int i) {
    if (i >= M) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access row %d of array of size (%d, %d)", i, getM(), getN()));
    }
    return new Row(i);
  }

  /**
   * Returns the column at the index i
   *
   * @param i the index
   * @return the i-th column
   */
  public Vector<T> getColumn(int i) {
    if (i >= N) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access column %d of array of size (%d, %d)", i, getM(), getN()));
    }
    return new Column(i);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder().append("(")
      .append(getM()).append(", ").append(getN()).append(")\n");
    for (int i = 0; i < getN() * getM(); i++) {
      stringBuilder.append("(").append(get(i)).append(")\t");
      if ((i + 1) % getN() == 0) {
        stringBuilder.append("\n");
      }
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Grid)) {
      return false;
    }
    Grid a = (Grid) o;
    if (a.getM() != getM() || a.getN() != getN()) {
      return false;
    }

    return Arrays.equals(array, a.array);
  }

  /**
   * Returns a copy of this array
   *
   * @return the copy
   */
  public Grid<T> copy() {
    int size = getM() * getN();
    Grid<T> grid = new Grid<>(getM(), getN());
    System.arraycopy(array, 0, grid.array, 0, size);
    return grid;
  }

  private class Column implements Vector<T> {
    final int c;

    Column(int c) {
      this.c = c;
    }

    @Override
    public T getAt(int i) {
      return get(i, c);
    }

    @Override
    public void setAt(int i, T value) {
      set(i, c, value);
    }

    @Override
    public int getSize() {
      return getM();
    }
  }

  private class Row implements Vector<T> {
    private int r;

    Row(int r) {
      this.r = r;
    }

    @Override
    public T getAt(int i) {
      return get(r, i);
    }

    @Override
    public void setAt(int i, T value) {
      set(r, i, value);
    }

    @Override
    public int getSize() {
      return getN();
    }
  }

}
