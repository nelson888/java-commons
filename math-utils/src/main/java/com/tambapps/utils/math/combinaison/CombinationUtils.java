package com.tambapps.utils.math.combinaison;

import com.tambapps.utils.math.vector.ArrayVector;
import com.tambapps.utils.math.vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class CombinationUtils {

  // source: https://www.baeldung.com/java-combinations-algorithm
  public static List<int[]> generateArrays(int n, int r) {
    List<int[]> combinations = new ArrayList<>();
    int[] combination = new int[r];
    // initialize with lowest lexicographic combination
    for (int i = 0; i < r; i++) {
      combination[i] = i;
    }
    while (combination[r - 1] < n) {
      combinations.add(combination.clone());
      // generate next combination in lexicographic order
      int t = r - 1;
      while (t != 0 && combination[t] == n - r + t) {
        t--;
      }
      combination[t]++;
      for (int i = t + 1; i < r; i++) {
        combination[i] = combination[i - 1] + 1;
      }
    }
    return combinations;
  }

  public static List<Vector<Integer>> generateVectors(int n, int r) {
    List<Vector<Integer>> combinations = new ArrayList<>();
    ArrayVector.Builder<Integer> combination = new ArrayVector.Builder<>(r);
    // initialize with lowest lexicographic combination
    for (int i = 0; i < r; i++) {
      combination.setAt(i, i);
    }
    while (combination.getAt(r - 1) < n) {
      combinations.add(combination.build());
      // generate next combination in lexicographic order
      int t = r - 1;
      while (t != 0 && combination.getAt(t) == n - r + t) {
        t--;
      }
      combination.setAt(t, combination.getAt(t) + 1);
      for (int i = t + 1; i < r; i++) {
        combination.setAt(i, combination.getAt(i - 1) + 1);
      }
    }
    return combinations;
  }
}
