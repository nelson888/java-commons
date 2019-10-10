package com.tambapps.utils.collectionutils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A 2-dimensional array of size M, N. <br>
 * M is the number of rows, N is the number of columns
 */
public class ArrayGrid<T> implements Grid<T> {

  private final int M; // number of rows
  private final int N; // number of columns
  private final Object[] array; // we store the 2D array in a 1D array of size N * M

  /**
   * Creates a 2D array of size M, N
   *
   * @param M the number of rows
   * @param N the number of columns
   */
  public ArrayGrid(int M, int N) {
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
  public ArrayGrid(int M, int N, T[] values) {
    if (values.length != M * N) {
      throw new IllegalArgumentException("Array should be of size N * M: " + M * N);
    }
    this.M = M;
    this.N = N;
    array = Arrays.copyOf(values, values.length);
  }

  /**
   * Creates a 2D array of size M, N with the given values.
   *
   * @param M      the number of rows
   * @param N      the number of columns
   * @param value  the default value of all cells
   */
  public ArrayGrid(int M, int N, T value) {
    this(M, N);
    fill(value);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T get(int row, int col) {
    return (T) array[getIndex(row, col)];
  }

  @Override
  @SuppressWarnings("unchecked")
  public T get(int i) {
    checkIndex(i);
    return (T) array[i];
  }

  @Override
  public void set(int row, int col, T value) {
    array[getIndex(row, col)] = value;
  }

  @Override
  public void set(int i, T value) {
    checkIndex(i);
    array[i] = value;
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
    checkIndex(row, col);
    return row * getN() + col;
  }

  @Override
  public int getM() {
    return M;
  }

  @Override
  public int getN() {
    return N;
  }

  @Override
  public Vector<T> getRow(int i) {
    if (i >= M) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access row %d of array of size (%d, %d)", i, getM(), getN()));
    }
    return new Row(i);
  }

  @Override
  public Vector<T> getColumn(int i) {
    if (i >= N) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access column %d of array of size (%d, %d)", i, getM(), getN()));
    }
    return new Column(i);
  }

  @Override
  public void fill(T value) {
    Arrays.fill(array, value);
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
    if (this.getClass() == o.getClass()) {
      ArrayGrid a = (ArrayGrid) o;
      return a.M == M && a.N == N && Arrays.equals(array, a.array);
    }
    Grid grid = (Grid) o;
    if (grid.getM() != getM() || grid.getN() != getN()) {
      return false;
    }
    for (int i = 0; i < size(); i++) {
      if (get(i) != grid.get(i)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(array);
  }

  @Override
  public ArrayGrid<T> copy() {
    ArrayGrid<T> grid = new ArrayGrid<>(getM(), getN());
    System.arraycopy(array, 0, grid.array, 0, size());
    return grid;
  }

  private static abstract class AbstractVector<T> implements Vector<T> {

    @Override
    public Iterator<T> iterator() {
      return new VectorIterator<>(this);
    }

    @Override
    public Stream<T> stream() {
      return StreamSupport.stream(
          Spliterators.spliterator(iterator(), size(), Spliterator.ORDERED & Spliterator.SIZED),
          false);
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Vector)) {
        return false;
      }
      if (obj == this) {
        return true;
      }
      Vector v = (Vector)obj;
      if (v.size() != size()) {
        return false;
      }
      for (int i = 0; i < size(); i++) {
        if (!Objects.equals(getAt(i), v.getAt(i))) {
          return false;
        }
      }
      return true;
    }

    @Override
    public int hashCode() {
      int hash = 1;
      for (int i = 0; i < size(); i++) {
        hash = 31 * hash + Objects.hashCode(getAt(i));
      }
      return hash;
    }
  }

  private static class VectorIterator<T> implements Iterator<T> {
    private final Vector<T> vector;
    private int i;

    private VectorIterator(AbstractVector<T> vector) {
      this.vector = vector;
      this.i = 0;
    }

    @Override
    public boolean hasNext() {
      return i < vector.size();
    }

    @Override
    public T next() {
      return vector.getAt(i++);
    }

    @Override
    public void remove() {
      vector.setAt(i, null);
    }
  }

  private class Column extends AbstractVector<T> {
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
    public int size() {
      return getM();
    }

  }

  private class Row extends AbstractVector<T> {
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
    public int size() {
      return getN();
    }

  }

  @Override
  public int size() {
    return array.length;
  }

  /**
   * Returns whether this grid contains only null elements
   * @return true if this grid contains only null elements
   */
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
    return Arrays.copyOf(array, array.length);
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
      set(i, null);
    }
  }
}