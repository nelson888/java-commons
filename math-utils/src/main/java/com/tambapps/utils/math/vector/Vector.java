package com.tambapps.utils.math.vector;

/**
 * Immutable Vector of fixed size
 * @param <T> the type of elements
 */
public interface Vector<T> {

  /**
   * Get the element at the i-th index
   * @param i the index
   * @return the element at the i-th index
   */
  T getAt(int i);

  /**
   * Get the size of this vector
   * @return the size of this vector
   */
  int getSize();

}
