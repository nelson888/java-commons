package com.tambapps.commons.string;

public final class StringUtils {

  private StringUtils() {}

  public static String nDigitsNumber(int number, int n) {
    StringBuilder sNumber = new StringBuilder().append(number);
    while (sNumber.length() < n) {
      sNumber.insert(0, '0');
    }
    return sNumber.toString();
  }

}
