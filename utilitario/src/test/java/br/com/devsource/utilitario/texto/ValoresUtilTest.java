package br.com.devsource.utilitario.texto;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de teste da classe ValoresUtil.java
 * @author Guilherme Freitas
 */
public class ValoresUtilTest {

	/**
	 * Testa método {@link ValoresUtil#mascaraMonetaria(BigDecimal, java.text.DecimalFormat)}
	 */
	@Test
	public void testeMascaraMonetaria() {
		BigDecimal valor = BigDecimal.valueOf(1520.2);
		Assert.assertEquals("R$ 1.520,20", ValoresUtil.mascaraMonetaria(valor, ValoresUtil.REAL));
		Assert.assertEquals("$ 1,520.20", ValoresUtil.mascaraMonetaria(valor, ValoresUtil.DOLAR));
		Assert.assertEquals("€ 1.520,20", ValoresUtil.mascaraMonetaria(valor, ValoresUtil.EURO));
	}
}
