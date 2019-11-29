package com.tambapps.utils.math.permutation;

import static org.junit.Assert.assertEquals;

import com.tambapps.utils.math.vector.ArrayVector;
import com.tambapps.utils.math.vector.Vector;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PermutationUtilsTest {

  @Test
  public void permutation1Test() {
    Set<Vector<Character>> expected = setOf(vector('a'));
    assertEquals(expected, new HashSet<>(PermutationUtils.generate('a')));
    assertEquals(expected, new HashSet<>(PermutationUtils.generate(new ArrayVector.Builder<>(1).set('a').build())));
  }

  @Test
  public void permutation2Test() {
    Set<Vector<Character>> expected = setOf(vector('a', 'b'), vector('b', 'a'));
    assertEquals(expected, new HashSet<>(PermutationUtils.generate('a', 'b')));
    assertEquals(expected, new HashSet<>(PermutationUtils.generate(new ArrayVector.Builder<>(2).set('a', 'b').build())));
  }

  private Vector<Character> vector(Character... chars) {
    return new ArrayVector.Builder<>(chars).build();
  }

  private Set<Vector<Character>> setOf(Vector<Character>... vectors) {
    return new HashSet<>(Arrays.asList(vectors));
  }
}
