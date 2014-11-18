package br.com.devsource.utilitario.validator;

import org.apache.commons.lang3.Validate;

public class ValidateSize {

  private int minSize = -1;
  private int maxSize = -1;
  private String string;

  public ValidateSize(String string) {
    this.string = string;
  }

  public ValidateSize min(int size) {
    minSize = size;
    Validate.isTrue(size > 0);
    if (containsMaxSize() && (size > maxSize)) {
      throw new IllegalArgumentException();
    }
    return this;
  }

  public ValidateSize max(int size) {
    maxSize = size;
    Validate.isTrue(size > 0);
    if (containsMinSize() && (size < minSize)) {
      throw new IllegalArgumentException();
    }
    return this;
  }

  public ValidateSize exact(int size) {
    minSize = size;
    maxSize = size;
    return this;
  }

  boolean containsMinSize() {
    return minSize != -1;
  }

  boolean containsMaxSize() {
    return maxSize != -1;
  }

  public void isValid() {
    if (containsMinSize()) {
      Validate.isTrue(string.length() >= minSize);
    }
    if (containsMaxSize()) {
      Validate.isTrue(string.length() <= maxSize);
    }
  }

  public void isValid(String message, Object... values) {
    if (containsMinSize()) {
      Validate.isTrue(string.length() >= minSize, message, values);
    }
    if (containsMaxSize()) {
      Validate.isTrue(string.length() <= maxSize, message, values);
    }
  }

}
