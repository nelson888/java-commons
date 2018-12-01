package com.tambapps.common;

public final class StringUtils {

  public static String nDigitsNumber(int number, int n) {
    StringBuilder sNumber = new StringBuilder().append(n);
    while (sNumber.length() < n) {
      sNumber.insert(0, '0');
    }
    return sNumber.toString();
  }

}
