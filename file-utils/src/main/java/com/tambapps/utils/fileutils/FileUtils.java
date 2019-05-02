package com.tambapps.utils.fileutils;

import java.io.File;
import java.io.IOException;

public final class FileUtils {

  private FileUtils() { }

  /**
   * Returns a new file with the given name or null if none was found.
   * If a file with the given name already exists, it will find a file named name_xxx.extension
   * with xxx a 3 digits number
   * <p>
   * Examples:
   * myText_001.txt
   * binary_098
   *
   * @param directory the directory in which to create the file
   * @param name      the name of the file created
   * @return an available  file
   */
  public static File newAvailableFile(String directory, String name) {
    File file = new File(directory, name);
    if (file.exists()) { //searching available file name
      for (int i = 0; file.exists() && i < 999; i++) {
        String number = nDigitsNumber(i, 3);
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
    return file.exists() ? null : file;
  }

  /**
   * Creates a new file with the given name and returns it.
   * If a file with the given name already exists, it will find a file named name_xxx.extension
   * with xxx a 3 digits number
   * <p>
   * Examples:
   * myText_001.txt
   * binary_098
   *
   * @param directory the directory in which to create the file
   * @param name      the name of the file created
   * @return an available (created) file
   * @throws IOException if no available file is found or in case of error during file creation
   */
  public static File newAvailableCreatedFile(String directory, String name) throws IOException {
    File file = newAvailableFile(directory, name);
    if (file == null) {
      throw new IOException("There isn't an available file for name " + name);
    }
    if (!file.createNewFile()) {
      throw new IOException("Couldn't create new file");
    }
    return file;
  }

  public static String nDigitsNumber(int number, int n) {
    StringBuilder sNumber = new StringBuilder().append(number);
    while (sNumber.length() < n) {
      sNumber.insert(0, '0');
    }
    return sNumber.toString();
  }

}