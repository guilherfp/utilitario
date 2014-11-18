package br.com.devsource.utilitario.validator;

public class ValidateUtils {

  public static ValidateSize size(String string) {
    return new ValidateSize(string);
  }

}
