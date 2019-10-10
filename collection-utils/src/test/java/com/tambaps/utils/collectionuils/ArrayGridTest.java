package com.tambaps.utils.collectionuils;


import com.tambapps.utils.collectionutils.ArrayGrid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayGridTest {

  @Test
  public void rowColumnTests() {
    rowColumnTest(new ArrayGrid<>(3, 2));
    rowColumnTest(new ArrayGrid<>(10, 10));
    rowColumnTest(new ArrayGrid<>(5, 15));
  }

  private void rowColumnTest(ArrayGrid<Integer> array) {
    int M = array.getM(), N = array.getN();

    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        array.set(i, j, i * array.getM() + j);
      }
    }

    for (int j = 0; j < N; j++) {
      ArrayGrid.Vector<Integer> column = array.getColumn(j);
      for (int i = 0; i < M; i++) {
        assertEquals("Should be equal", i * array.getM() + j, (int) column.getAt(i));
      }
    }

    for (int i = 0; i < M; i++) {
      ArrayGrid.Vector<Integer> row = array.getRow(i);
      for (int j = 0; j < N; j++) {
        assertEquals("Should be equal", i * array.getM() + j, (int) row.getAt(j));
      }
    }
  }

  @Test
  public void copyTest() {
    ArrayGrid<Integer> grid = new ArrayGrid<>(6, 6);
    for (int i = 0; i < grid.size(); i++) {
      grid.set(i, i);
    }
    assertEquals("Should be equal", grid, grid.copy());
  }


}