package sk.araed.aoc.aoc2023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author boris.brinza
 */
public abstract class AocBase<T> {

  protected String readInput(final String path) {
    final BufferedReader reader =
        new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(AocBase.class.getResourceAsStream(path))));
    String s = null;
    final StringBuilder ret = new StringBuilder();
    try {
      while ((s = reader.readLine()) != null) {
        ret.append(s).append("\n");
      }
    } catch (Exception e) {
      System.err.println("Cannot read file:" + path + ", error:" + e.toString());
      return null;
    }
    //remove last /n

    return  ret.substring(0, ret.length() - 1);

  }

  protected String[] readInputToLines(final String path) {
    return readInput(path).split(System.lineSeparator());
  }



}