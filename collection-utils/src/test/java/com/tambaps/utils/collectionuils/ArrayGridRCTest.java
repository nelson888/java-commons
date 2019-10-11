package com.tambaps.utils.collectionuils;

import com.tambapps.utils.collectionutils.ArrayRCGrid;
import com.tambapps.utils.collectionutils.Grid;

public class ArrayGridRCTest extends GridTest {
  @Override
  Grid<Integer> newGrid(int M, int N) {
    return new ArrayRCGrid<>(M, N);
  }

  @Override
  Grid<Integer> newGrid(int M, int N, Integer... cells) {
    return new ArrayRCGrid<>(M, N, cells);
  }
}
