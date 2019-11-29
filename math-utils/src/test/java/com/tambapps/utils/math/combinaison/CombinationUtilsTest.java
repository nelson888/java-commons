package com.tambapps.utils.math.combinaison;

import static org.junit.Assert.assertEquals;

import com.tambapps.utils.math.vector.ArrayVector;
import com.tambapps.utils.math.vector.Vector;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CombinationUtilsTest {

  @Test
  public void combinationTest() {
    int N = 12;
    int R = 6;
    List<int[]> selectionArray = CombinationUtils.generateArrays(N, R);
    List<Vector<Integer>> selectionVector = CombinationUtils.generateVectors(N, R);

    assertEquals(selectionVector, selectionArray.stream().map(this::toVector).collect(Collectors.toList()));
    for (Vector<Integer> combination: selectionVector) {
      System.out.println(combination);
    }
  }

  private Vector<Integer> toVector(int[] array) {
    return new ArrayVector.Builder<Integer>(array).build();
  }
}
