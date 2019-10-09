package com.tambaps.utils.collectionuils;


import com.tambapps.utils.collectionutils.Grid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridTest {

  @Test
  public void rowColumnTests() {
    rowColumnTest(new Grid<>(3, 2));
    rowColumnTest(new Grid<>(10, 10));
    rowColumnTest(new Grid<>(5, 15));
  }

  private void rowColumnTest(Grid<Integer> array) {
    int M = array.getM(), N = array.getN();

    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        array.set(i, j, i * array.getM() + j);
      }
    }

    for (int j = 0; j < N; j++) {
      Grid.Vector<Integer> column = array.getColumn(j);
      for (int i = 0; i < M; i++) {
        assertEquals("Should be equal", i * array.getM() + j, (int) column.getAt(i));
      }
    }

    for (int i = 0; i < M; i++) {
      Grid.Vector<Integer> row = array.getRow(i);
      for (int j = 0; j < N; j++) {
        assertEquals("Should be equal", i * array.getM() + j, (int) row.getAt(j));
      }
    }
  }

  @Test
  public void copyTest() {
    Grid<Integer> grid = new Grid<>(6, 6);
    for (int i = 0; i < grid.size(); i++) {
      grid.set(i, i);
    }
    assertEquals("Should be equal", grid, grid.copy());
  }


}