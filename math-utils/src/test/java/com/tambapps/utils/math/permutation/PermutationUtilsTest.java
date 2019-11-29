package com.tambapps.utils.math.permutation;

import static org.junit.Assert.assertEquals;

import com.tambapps.utils.math.vector.ArrayVector;
import com.tambapps.utils.math.vector.Vector;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationUtilsTest {

  @Test
  public void permutation1Test() {
    List<Vector<Character>> permutations = PermutationUtils.generate('a');
    assertEquals(setOf(vector('a')), new HashSet<>(permutations));
  }

  @Test
  public void permutation2Test() {
    List<Vector<Character>> permutations = PermutationUtils.generate('a', 'b');
    assertEquals(setOf(vector('a', 'b'),
        vector('b', 'a')), new HashSet<>(permutations));
  }

  private Vector<Character> vector(Character... chars) {
    return new ArrayVector.Builder<>(chars).build();
  }
  private Set<Vector<Character>> setOf(Vector<Character>... vectors) {
    return new HashSet<>(Arrays.asList(vectors));
  }
}
