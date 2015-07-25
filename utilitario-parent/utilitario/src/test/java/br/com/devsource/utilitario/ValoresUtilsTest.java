package br.com.devsource.utilitario;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Classe de teste da classe ValoresUtil.java
 * @author Guilherme Freitas
 */
public class ValoresUtilsTest {

  /**
   * Testa método {@link ValoresUtils#mascaraMonetaria(BigDecimal, java.text.DecimalFormat)}
   */
  @Test
  public void testeMascaraMonetaria() {
    BigDecimal valor = BigDecimal.valueOf(1520.2);
    assertEquals("R$ 1.520,20", ValoresUtils.mascaraMonetaria(valor, ValoresUtils.REAL));
    assertEquals("$ 1,520.20", ValoresUtils.mascaraMonetaria(valor, ValoresUtils.DOLAR));
    assertEquals("€ 1.520,20", ValoresUtils.mascaraMonetaria(valor, ValoresUtils.EURO));
  }
}
