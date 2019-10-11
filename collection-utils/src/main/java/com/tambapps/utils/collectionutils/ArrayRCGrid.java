package com.tambapps.utils.collectionutils;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Array Grid with rows and columns stored in memory (instead
 * of instanciated one at each getColumn/getRow call)
 * @param <T> the type of the grid cells
 */
public class ArrayRCGrid<T> extends ArrayGrid<T> {

  private final Vector[] rows;
  private final Vector[] columns;

  public ArrayRCGrid(int M, int N) {
    super(M, N);
    this.rows = new Vector[M];
    this.columns = new Vector[N];
    initializeRowsCols();
  }

  public ArrayRCGrid(int M, int N, T... values) {
    super(M, N, values);
    this.rows = new Vector[M];
    this.columns = new Vector[N];
    initializeRowsCols();
  }

  public ArrayRCGrid(int M, int N, T value) {
    super(M, N, value);
    this.rows = new Vector[M];
    this.columns = new Vector[N];
    initializeRowsCols();
  }

  private void initializeRowsCols() {
    for (int i = 0; i < rows.length; i++) {
      rows[i] = super.getRow(i);
    }
    for (int i = 0; i < columns.length; i++) {
      columns[i] = super.getColumn(i);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public Vector<T> getRow(int i) {
    if (i >= M) {
      throw new IndexOutOfBoundsException(
          String.format("Tried to access row %d of array of size (%d, %d)", i, M, N));
    }
    return rows[i];
  }

  @SuppressWarnings("unchecked")
  @Override
  public Vector<T> getColumn(int i) {
    if (i >= N) {
      throw new IndexOutOfBoundsException(
          String.format("Tried to access column %d of array of size (%d, %d)", i, M, N));
    }
    return columns[i];
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stream<Vector<T>> rows() {
    return Arrays.stream(rows);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stream<Vector<T>> columns() {
    return Arrays.stream(columns);
  }
}
