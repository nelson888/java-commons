package com.tambapps.utils.math.permutation;

import com.tambapps.utils.math.vector.ArrayVector;
import com.tambapps.utils.math.vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class PermutationUtils {

  // source: https://www.baeldung.com/java-array-permutations
  public static <T> List<Vector<T>> generate(T... array) {
    int n = array.length;
    ArrayVector.Builder<T> elements = new ArrayVector.Builder<>(n);
    elements.set(array);

    List<Vector<T>> vectors = new ArrayList<>();
    int[] indexes = new int[n];
    for (int i = 0; i < n; i++) {
      indexes[i] = 0;
    }

    vectors.add(elements.build());

    int i = 0;
    while (i < n) {
      if (indexes[i] < i) {
        swap(elements, i % 2 == 0 ?  0: indexes[i], i);
        vectors.add(elements.build());
        indexes[i]++;
        i = 0;
      }
      else {
        indexes[i] = 0;
        i++;
      }
    }
    return vectors;
  }

  private static <T> void swap(ArrayVector.Builder<T> elements, int i1, int i2) {
    T temp = elements.getAt(i2);
    elements.setAt(i2, elements.getAt(i1));
    elements.setAt(i1, temp);
  }

}
