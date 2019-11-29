package com.tambapps.utils.math.vector;

import java.util.Arrays;
import java.util.Objects;

public class ArrayVector<T> implements Vector<T> {

  private final Object[] objects;

  public ArrayVector(T object) {
    objects = new Object[] { object };
  }

  private ArrayVector(Object... objects) {
    this.objects = objects;
  }

  @Override
  public T getAt(int i) {
    return (T) objects[i];
  }

  @Override
  public int getSize() {
    return objects.length;
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Vector)) return false;
    Vector v = (Vector) o;
    if (v.getSize() != getSize()) {
      return false;
    }
    for (int i = 0; i < getSize(); i++) {
      if (!Objects.equals(getAt(i), v.getAt(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(objects);
  }

  @Override
  public String toString() {
    return Arrays.toString(objects);
  }

  public static class Builder<T> {
    private final Object[] objects;

    public Builder(int n) {
      this.objects = new Object[n];
    }

    public Builder(T[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(int[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(long[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(char[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(short[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(byte[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(float[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder(double[] objects) {
      this.objects = new Object[objects.length];
      for (int i = 0; i < objects.length; i++) {
        this.objects[i] = objects[i];
      }
    }

    public Builder<T> set(T... array) {
      System.arraycopy(array, 0, objects, 0, array.length);
      return this;
    }

    public Vector<T> build() {
      return new ArrayVector<>(Arrays.copyOf(objects, objects.length));
    }

    public Builder<T> setAt(int i, T value) {
      objects[i] = value;
      return this;
    }

    public T getAt(int i) {
      return (T) objects[i];
    }
  }
}
