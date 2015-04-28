package br.com.devsource.utilitario;

import java.math.BigDecimal;

import org.junit.Assert;
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
    Assert.assertEquals("R$ 1.520,20", ValoresUtils.mascaraMonetaria(valor, ValoresUtils.REAL));
    Assert.assertEquals("$ 1,520.20", ValoresUtils.mascaraMonetaria(valor, ValoresUtils.DOLAR));
    Assert.assertEquals("€ 1.520,20", ValoresUtils.mascaraMonetaria(valor, ValoresUtils.EURO));
  }
}
