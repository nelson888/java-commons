package com.tambapps.utils.ioutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class IOUtils {

  private IOUtils() {}

  public static BufferedReader newReader(File file) throws IOException {
    return new BufferedReader(new FileReader(file));
  }

  public static BufferedWriter newWriter(File file) throws IOException {
    return new BufferedWriter(new FileWriter(file));
  }

  public static BufferedReader newReader(InputStream inputStream) {
    return new BufferedReader(new InputStreamReader(inputStream));
  }

  public static BufferedReader newReader(Class thisClass, String resourcePath) {
    return new BufferedReader(new InputStreamReader(thisClass.getResourceAsStream(resourcePath)));
  }

}
