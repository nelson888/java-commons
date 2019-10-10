package com.tambapps.utils.collectionutils;

import java.util.Collection;
import java.util.stream.Stream;

public interface Grid<T> extends Collection<T> {

  int LEFT = -1;
  int UP = -1;
  int RIGHT = - LEFT;
  int DOWN = - UP;

  interface Vector<T> extends Iterable<T> {
    T getAt(int i);
    void setAt(int i, T value);
    int size();
    Stream<T> stream();
  }


  /**
   * Get the element at the index [row][col]
   *
   * @param row the row
   * @param col the col
   * @return the element at the given indexes
   */
  T get(int row, int col);

  /**
   * Get the element at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the 1d index
   * @return the element at the given index
   */
  T get(int i);
  /**
   * Sets the element at the index [row][col]
   *
   * @param row the row
   * @param col the col
   */
  void set(int row, int col, T value);

  /**
   * Sets the element at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the column index
   */
  void set(int i, T value);
  /**
   * Returns the number of rows
   *
   * @return the number of rows
   */
  int getM();

  /**
   * Returns the number of columns
   *
   * @return the number of columns
   */
  int getN();

  /**
   * Returns the row at the index i
   *
   * @param i the index
   * @return the i-th row
   */
  Vector<T> getRow(int i);

  /**
   * Returns the column at the index i
   *
   * @param i the index
   * @return the i-th column
   */
  Vector<T> getColumn(int i);

  /**
   * Fill the grid with the provided value
   * @param value the value to fill the grid with
   */
  void fill(T value);

  /**
   * Returns a copy of this array
   *
   * @return the copy
   */
  ArrayGrid<T> copy();


}