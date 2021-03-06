package com.tambaps.utils.collectionuils;

import com.tambapps.utils.collectionutils.ArrayGrid;
import com.tambapps.utils.collectionutils.Grid;

public class ArrayGridTest extends GridTest {
  @Override
  Grid<Integer> newGrid(int M, int N) {
    return new ArrayGrid<>(M, N);
  }

  @Override
  Grid<Integer> newGrid(int M, int N, Integer... cells) {
    return new ArrayGrid<>(M, N, cells);
  }
}
