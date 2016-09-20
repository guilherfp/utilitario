package br.com.devsource.utilitario;

import java.util.regex.Pattern;

/**
 * Classe com conjunto de expressões regulares e validadores.
 * @author Guilherme Freitas
 */
public class ExpressoesRegulares {

  /** Expressão regular para telefone no formado (99) 9999-9999. */
  public static final String TELEFONE = "\\([1-9][1-9]\\) [2-9][0-9]{3}-[0-9]{4,5}";
  /** Expressão regular para CEP no formado 99999-999. */
  public static final String CEP = "[0-9]{5}-[0-9]{3}";
  public static final String SOMENTE_NUMERO = "[0-9]+";
  public static final String EMAIL = "(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})";

  private ExpressoesRegulares() {
    super();
  }

  /**
   * Método valida expressões regulares.
   * @param string {@link String} a ser validada.
   * @param expressao Expressão de validação.
   * @return <code>true</code> caso expressão seja atendida. <code>false</code> caso contrário.
   */
  public static boolean isValido(String string, String expressao) {
    return Pattern.compile(expressao).matcher(string).matches();
  }

}
