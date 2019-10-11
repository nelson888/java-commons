package com.tambaps.utils.collectionuils;


import com.tambapps.utils.collectionutils.Grid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class GridTest {

  abstract Grid<Integer> newGrid(int M, int N);
  abstract Grid<Integer> newGrid(int M, int N, Integer... cells);

  @Test
  public void rowColumnTests() {
    rowColumnTest(newGrid(3, 2));
    rowColumnTest(newGrid(10, 10));
    rowColumnTest(newGrid(5, 15));
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
    Grid<Integer> grid = filledGrid(6, 6);
    assertEquals("Should be equal", grid, grid.copy());
  }

  @Test
  public void rowColStreamTest() {
    Grid<Integer> grid = filledGrid(2, 2);
    assertEquals(Arrays.asList(0, 1), grid.getRow(0).stream().collect(Collectors.toList()));
    assertEquals(Arrays.asList(2, 3), grid.getRow(1).stream().collect(Collectors.toList()));
    assertEquals(Arrays.asList(0, 2), grid.getColumn(0).stream().collect(Collectors.toList()));
    assertEquals(Arrays.asList(1, 3), grid.getColumn(1).stream().collect(Collectors.toList()));
  }

  private Grid<Integer> filledGrid(int M, int N) {
    Grid<Integer> grid = newGrid(M, N);
    for (int i = 0; i < grid.size(); i++) {
      grid.set(i, i);
    }
    return grid;
  }

  @Test
  public void subGridTest() {
    Grid<Integer> grid = filledGrid(4, 4);
    assertEquals("Should be equal", grid, grid.subGrid(0, 0, 4, 4));

    assertEquals(newGrid(2, 3, 0, 1, 2, 4, 5, 6), grid.subGrid(0, 0, 2, 3));
    assertEquals(newGrid(2, 2, 1, 2, 5, 6), grid.subGrid(0, 1, 2, 2));
  }
}