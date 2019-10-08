package com.tambapps.utils.collectionutils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * A 2-dimensional array of size M, N. <br>
 * M is the number of rows, N is the number of columns
 */
@SuppressWarnings("unchecked")
public class Grid<T> implements Collection<T> {

  public interface Vector<T> extends Iterator<T>, Iterable<T> {
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
   * Get the element at the index [row][col]
   *
   * @param row the row
   * @param col the col
   * @return the element at the given indexes
   */
  public T get(int row, int col) {
    checkIndex(row, col);
    return (T) array[getIndex(row, col)];
  }

  /**
   * Get the element at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the 1d index
   * @return the element at the given index
   */
  public T get(int i) {
    checkIndex(i);
    return (T) array[i];
  }

  /**
   * Sets the element at the index [row][col]
   *
   * @param row the row
   * @param col the col
   */
  public void set(int row, int col, T value) {
    checkIndex(row, col);
    array[getIndex(row, col)] = Objects.requireNonNull(value, "Cannot set a null value");
  }

  /**
   * Sets the element at the index i, a 1D index.
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
    Grid<T> grid = new Grid<>(getM(), getN());
    System.arraycopy(array, 0, grid.array, 0, size());
    return grid;
  }

  private static abstract class AbstractVector<T> implements Vector<T> {

    @Override
    public Iterator<T> iterator() {
      return this;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  private class Column extends AbstractVector<T> {
    final int c;
    private int i;

    Column(int c) {
      this.c = c;
      this.i = 0;
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

    @Override
    public boolean hasNext() {
      return i < getN();
    }

    @Override
    public T next() {
      return getAt(i++);
    }
  }

  private class Row extends AbstractVector<T> {
    private int r;
    private int i;

    Row(int r) {
      this.r = r;
      this.i = 0;
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

    @Override
    public boolean hasNext() {
      return i < getM();
    }

    @Override public T next() {
      return getAt(i++);
    }
  }

  @Override
  public int size() {
    return array.length;
  }

  @Override
  public boolean isEmpty() {
    for (int i = 0; i < size(); i++) {
      if (array[i] != null) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean contains(Object o) {
    for (int i = 0; i < size(); i++) {
      if (Objects.equals(o, array[i])) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new GridIterator();
  }

  @Override
  public Object[] toArray() {
    Object[] copy = new Object[array.length];
    System.arraycopy(array, 0, copy, 0, size());
    return copy;
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean add(T t) {
    throw new UnsupportedOperationException("Cannot add an element to a Grid");
  }

  @Override
  public boolean remove(Object o) {
    for (int i = 0; i < size(); i++) {
      if (Objects.equals(o, array[i])) {
        array[i] = null;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    for (Object o : c) {
      if (!contains(o)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException("Cannot add element to a Grid");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean changed = false;
    for (Object o : c) {
      changed = remove(o) || changed;
    }
    return changed;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    boolean changed = false;
    for (int i = 0; i < size(); i++) {
      if (array[i] != null && !c.contains(array[i])) {
        array[i] = null;
        changed = true;
      }
    }
    return changed;
  }

  @Override
  public void clear() {
    Arrays.fill(array, null);
  }

  private class GridIterator implements Iterator<T> {
    private int i = 0;

    @Override
    public boolean hasNext() {
      return i < size();
    }

    @Override
    public T next() {
      return get(i++);
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
