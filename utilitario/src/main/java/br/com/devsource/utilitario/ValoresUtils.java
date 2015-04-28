package br.com.devsource.utilitario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Classe utilitária para tratamento de valores monetários.
 * @author Guilherme Freitas
 */
public final class ValoresUtils {

  private static final String FORMAT = "¤ ###,###,##0.00";

  /** Símbolos específicos do Dólar Americano */
  private static final DecimalFormatSymbols DFS_DOLAR = new DecimalFormatSymbols(Locale.US);
  /** Mascara de dinheiro para Dólar Americano */
  public static final DecimalFormat DOLAR = new DecimalFormat(FORMAT, DFS_DOLAR);
  /** Símbolos específicos do Euro */
  private static final DecimalFormatSymbols DFS_EURO = new DecimalFormatSymbols(Locale.GERMANY);
  /** Mascara de dinheiro para Euro */
  public static final DecimalFormat EURO = new DecimalFormat(FORMAT, DFS_EURO);
  /** Locale Brasileiro */
  private static final Locale BRAZIL = new Locale("pt", "BR");
  /** Símbolos específicos do Real Brasileiro */
  private static final DecimalFormatSymbols DFS_REAL = new DecimalFormatSymbols(BRAZIL);
  /** Mascara de dinheiro para Real Brasileiro */
  public static final DecimalFormat REAL = new DecimalFormat(FORMAT, DFS_REAL);

  private ValoresUtils() {
    super();
  }

  /**
   * Mascara texto com formatação monetária.
   * @param valor Valor a ser mascarado.
   * @param moeda Padrão monetário a ser usado.
   * @return Valor mascarado de acordo com o padrão especificado.
   */
  public static String mascaraMonetaria(double valor, DecimalFormat moeda) {
    return moeda.format(valor);
  }

  /**
   * Mascara texto com formatação monetária.
   * @param valor Valor a ser mascarado.
   * @param moeda Padrão monetário a ser usado.
   * @return Valor mascarado de acordo com o padrão especificado.
   */
  public static String mascaraMonetaria(BigDecimal valor, DecimalFormat moeda) {
    return moeda.format(valor);
  }
}
