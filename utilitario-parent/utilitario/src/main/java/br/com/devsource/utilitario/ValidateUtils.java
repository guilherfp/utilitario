package br.com.devsource.utilitario;

import org.apache.commons.lang3.Validate;

import br.com.devsource.utilitario.money.Money;
import br.com.devsource.utilitario.ratio.Ratio;

/**
 * Classe de validação e valor.
 * @author Guilherme Freitas em 28/05/2014.
 */
public class ValidateUtils {

  private static final String DEFAULT_NAO_E_MAIOR_QUE_UM_CENTAVO = "%s não é maior que um centavo";
  private static final String DEFAULT_MAIOR_DO_QUE = "%s não é maior do que %s";
  private static final String DEFAULT_MAIOR_OU_IGUAL_A = "%s não é maior nem igual a %s";
  private static final String DEFAULT_MENOR_DO_QUE = "%s não é menor do que %s";

  private ValidateUtils() {
    super();
  }

  /**
   * Valida de o valor monetário é maior que um centávo.
   * @param money Valor a ser validado.
   * @param message Formato da mensagem de erro.
   * @param values Valor a mensagem.
   * @throws IllegalArgumentException Se a expressão for falsa.
   * @author Guilherme Freitas em 28/05/2014.
   */
  public static void isMaiorQueUmCentavo(Money money, String message, Object... values) {
    Validate.notNull(money);
    Validate.isTrue(money.maiorQueUmCentavo(), message, values);
  }

  /**
   * Valida de o valor monetário é maior que um centavo.
   * @param excepted Valor a ser validado.
   * @throws IllegalArgumentException Se a expressão for falsa.
   * @author Guilherme Freitas em 28/05/2014.
   */
  public static void isMaiorQueUmCentavo(Money excepted) {
    isMaiorQueUmCentavo(excepted, DEFAULT_NAO_E_MAIOR_QUE_UM_CENTAVO, excepted);
  }

  public static void isMaiorDoQue(Money excepted, Money actual, String message, Object... values) {
    Validate.notNull(excepted);
    Validate.notNull(actual);
    Validate.isTrue(excepted.maiorQue(actual), message, values);
  }

  public static void isMaiorDoQue(Money excepted, Money actual) {
    isMaiorDoQue(excepted, actual, DEFAULT_MAIOR_DO_QUE, excepted, actual);
  }

  public static void isMaiorDoQue(Ratio excepted, Ratio actual, String message, Object... values) {
    Validate.notNull(excepted);
    Validate.notNull(actual);
    Validate.isTrue(excepted.maiorQue(actual), message, values);
  }

  public static void isMaiorDoQue(Ratio excepted, Ratio actual) {
    isMaiorDoQue(excepted, actual, DEFAULT_MAIOR_DO_QUE, excepted, actual);
  }

  public static void isMaiorOuIgualA(Ratio excepted, Ratio actual, String message,
      Object... values) {
    Validate.notNull(excepted);
    Validate.notNull(actual);
    Validate.isTrue(excepted.maiorOuIqualA(actual), message, values);
  }

  public static void isMaiorOuIgualA(Ratio excepted, Ratio actual) {
    isMaiorOuIgualA(excepted, actual, DEFAULT_MAIOR_OU_IGUAL_A, excepted, actual);
  }

  public static void isMaiorOuIgualA(Money excepted, Money actual, String message,
      Object... values) {
    Validate.notNull(excepted);
    Validate.notNull(actual);
    Validate.isTrue(excepted.maiorOuIgualA(actual), message, values);
  }

  public static void isMaiorOuIqualA(Money excepted, Money actual) {
    isMaiorOuIgualA(excepted, actual, DEFAULT_MAIOR_OU_IGUAL_A, excepted, actual);
  }

  public static void isMenorDoQue(Money excepted, Money actual, String message, Object... values) {
    Validate.notNull(excepted);
    Validate.notNull(actual);
    Validate.isTrue(excepted.isMenorQue(actual), message, values);
  }

  public static void isMenorDoQue(Money excepted, Money actual) {
    isMenorDoQue(excepted, actual, DEFAULT_MENOR_DO_QUE, excepted, actual);
  }

  public static ValidateSize size(String string) {
    return new ValidateSize(string);
  }
}
