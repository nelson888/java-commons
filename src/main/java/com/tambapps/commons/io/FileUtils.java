package com.tambapps.commons;

import java.io.File;
import java.io.IOException;

public final class FileUtils {

  private FileUtils() {}

  public static File newFile(String directory, String name) throws IOException {
    File file = new File(directory, name);
    if (file.exists()) { //searching available file name
      for (int i = 0; file.exists() && i < 999; i++) {
        String number = StringUtils.nDigitsNumber(i, 3);
        String fileName;
        if (name.contains(".")) {
          int dotIndex = name.indexOf('.');
          fileName = name.substring(0, dotIndex) + '_' + number + name.substring(dotIndex);
        } else {
          fileName = name + '_' + number;
        }
        file = new File(directory, fileName);
      }
    }
    if (!file.createNewFile()) {
      throw new IOException("Couldn't create new file");
    }
    return file;
  }
}
