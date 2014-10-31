package br.com.devsource.utilitario.money;

import static br.com.devsource.utilitario.money.Money.REAL;
import static br.com.devsource.utilitario.money.Money.real;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import br.com.devsource.utilitario.ratio.Ratio;

/**
 * Teste classe money.
 * @author Guilherme Freitas em 30/04/2014.
 */
public class MoneyTest {

  @Test
  public void testeEquals() {
    Money a = real(30);
    Money b = real(45);
    assertFalse(a.equals(b));
    Money c = real(30);
    assertTrue(a.equals(c));
  }

  @Test
  public void testAdd() {
    Money valorA = real(20);
    Money valorB = real(200);
    assertEquals(Money.valueOf(220, REAL), valorA.add(valorB));

    valorA = real(20.56);
    valorB = real(200.14);
    assertEquals(Money.valueOf(220.70, REAL), valorA.add(valorB));

    valorA = real(20.566);
    valorB = real(200.131);
    assertEquals(Money.valueOf(220.697, REAL), valorA.add(valorB));

    valorA = real(-20);
    valorB = real(-200);
    assertEquals(Money.valueOf(-220, REAL), valorA.add(valorB));

    valorA = real(-20.96);
    valorB = real(-200.14);
    assertEquals(Money.valueOf(-221.10, REAL), valorA.add(valorB));

    valorA = real(-20.968);
    valorB = real(-200.147);
    assertEquals(Money.valueOf(-221.10, REAL), valorA.add(valorB));

    valorA = real(-20.96884375982342);
    valorB = real(-200.1474875298374);
    assertEquals(Money.valueOf(-221.10, REAL), valorA.add(valorB));
  }

  @Test
  public void testeMinus() {
    Money valorA = real(200);
    Money valorB = real(20);
    assertEquals(Money.valueOf(180, REAL), valorA.minus(valorB));

    valorA = real(20.56);
    valorB = real(200.14);
    assertEquals(Money.valueOf(-179.58, REAL), valorA.minus(valorB));

    valorA = real(20.566);
    valorB = real(200.131);
    assertEquals(Money.valueOf(-179.57, REAL), valorA.minus(valorB));

    valorA = real(20.56643523452345234);
    valorB = real(200.13123523452345234);
    assertEquals(Money.valueOf(-179.57, REAL), valorA.minus(valorB));

    valorA = real(-20);
    valorB = real(-200);
    assertEquals(Money.valueOf(180, REAL), valorA.minus(valorB));

    valorA = real(-20.96);
    valorB = real(-200.14);
    assertEquals(Money.valueOf(179.18, REAL), valorA.minus(valorB));

    valorA = real(-20.968);
    valorB = real(-200.147);
    assertEquals(Money.valueOf(179.18, REAL), valorA.minus(valorB));

    valorA = real(-20.96523452345234528);
    valorB = real(-200.1423452345234527);
    assertEquals(Money.valueOf(179.18, REAL), valorA.minus(valorB));
  }

  @Test
  public void testeMultiply() {
    assertEquals(Money.valueOf(600, REAL), real(200).multiply(3));
    assertEquals(Money.valueOf(3.09, REAL), real(1.03).multiply(3));
    assertEquals(Money.valueOf(3.09, REAL), real(1.037).multiply(3));
    assertEquals(Money.valueOf(3.09, REAL), real(1.03123412341234123412347).multiply(3));
    assertEquals(Money.valueOf(-600, REAL), real(200).multiply(-3));

    assertEquals(Money.valueOf(-600, REAL), real(-200).multiply(3));
    assertEquals(Money.valueOf(-3.09, REAL), real(-1.03).multiply(3));
    assertEquals(Money.valueOf(-3.09, REAL), real(-1.037).multiply(3));
    assertEquals(Money.valueOf(3.09, REAL), real(-1.037).multiply(-3));
    assertEquals(Money.valueOf(3.09, REAL), real(-1.0371234123412341234123).multiply(-3));
  }

  @Test
  public void testeIncrease() {
    assertEquals(Money.valueOf(5.61, REAL), real(5.5).increasePercentage(2));
    assertEquals(Money.valueOf(5.6386, REAL), real(5.5).increasePercentage(2.52));
    assertEquals(Money.valueOf(5.63, REAL), real(5.5).increasePercentage(BigDecimal.valueOf(2.52)));
  }

  @Test
  public void testeDeduction() {
    assertEquals(Money.valueOf(5.39, REAL), real(5.5).deductionPercentage(2));
    assertEquals(Money.valueOf(5.37, REAL), real(5.5).deductionPercentage(2.52));
    assertEquals(Money.valueOf(5.37, REAL), real(5.5).deductionPercentage(BigDecimal.valueOf(2.52)));
  }

  @Test
  public void testeDistribute() {
    Money valorA = real(10);
    Money[] distruibutionA = new Money[] { real(3.34), real(3.33), real(3.33) };
    System.out.println(Arrays.asList(valorA.distribute(3)));
    Assert.assertArrayEquals(distruibutionA, valorA.distribute(3));

    Money valorB = real(10);
    Money[] distruibutionB = new Money[] { real(5), real(5) };
    Assert.assertArrayEquals(distruibutionB, valorB.distribute(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDistribute_invalido() {
    real(3).distribute(0);
  }

  @Test
  public void testNegate() {
    assertEquals(real(-200), real(200).negate());
    assertEquals(real(200), real(-200).negate());
  }

  @Test
  public void testeIsMaiorQue() {
    assertTrue(real(400).maiorQue(real(300)));

    assertFalse(real(400).maiorQue(real(400)));
    assertFalse(real(400).maiorQue(real(500)));
  }

  @Test
  public void testIsMenorQue() {
    assertTrue(real(300).isMenorQue(real(400)));

    assertFalse(real(400).isMenorQue(real(400)));
    assertFalse(real(300).isMenorQue(real(200)));
  }

  @Test
  public void testComparable() {
    assertTrue(real(400).compareTo(real(300)) > 0);
    assertTrue(real(400).compareTo(real(400)) == 0);
    assertTrue(real(300).compareTo(real(400)) < 0);
  }

  @Test(expected = CurrencyMissmatchException.class)
  public void testeCurrency_incompativeis() {
    Money.valueOf(10, Currency.getInstance(Locale.US)).add(Money.valueOf(10, REAL));
  }

  @Test
  public void testToString() {
    Money valor = Money.valueOf(2.20);
    assertEquals("R$ 2,20", valor.toString());
  }

  @Test
  public void testIsZero() {
    assertTrue(Money.valueOf(0).isZero());
    assertFalse(Money.valueOf(1).isZero());
    assertFalse(Money.valueOf(0.1).isZero());
    assertFalse(Money.valueOf(3.2).isZero());
  }

  @Test
  public void testMaiorQueUmCentavo() {
    assertFalse(Money.valueOf(0).maiorQueUmCentavo());
    assertFalse(Money.valueOf(0.009).maiorQueUmCentavo());
    assertFalse(Money.valueOf(-0.1D).maiorQueUmCentavo());
    assertFalse(Money.valueOf(-3).maiorQueUmCentavo());

    assertTrue(Money.valueOf(0.01).maiorQueUmCentavo());
    assertTrue(Money.valueOf(0.1).maiorQueUmCentavo());
    assertTrue(Money.valueOf(3.2).maiorQueUmCentavo());
  }

  @Test
  public void testGetCentavos() throws Exception {
    assertEquals(30, Money.valueOf(10.30).getCentavos());
    assertEquals(0, Money.valueOf(10.0).getCentavos());
    assertEquals(-53, Money.valueOf(-10.53).getCentavos());
  }

  @Test
  public void testGetParInteira() throws Exception {
    assertEquals(10, Money.valueOf(10.30).getParInteira());
    assertEquals(10, Money.valueOf(10.99).getParInteira());
    assertEquals(-123, Money.valueOf(-123.99).getParInteira());
  }

  @Test
  public void testIncreasePorcentageAndDistribute() throws Exception {
    Money compra = Money.valueOf(170);
    Money valorComDesconto = compra.deductionPercentage(10);
    assertEquals(Money.valueOf(153), valorComDesconto);
    Money money21e85 = Money.valueOf(21.85);
    Money[] parcelas =
        new Money[] { Money.valueOf(21.90), money21e85, money21e85, money21e85, money21e85, money21e85, money21e85 };
    Assert.assertArrayEquals(parcelas, valorComDesconto.distribute(7));
  }

  @Test
  public void testGetPorcentage() throws Exception {
    Money valor = Money.valueOf(100);
    assertEquals(Money.valueOf(50), valor.getPorcentage(50));
    assertEquals(Money.valueOf(75), valor.getPorcentage(75));
  }

  @Test
  public void testGetPorcentageOfMoney() throws Exception {
    assertEquals(Ratio.valueOf(50), Money.valueOf(100).porcentageRelative(Money.valueOf(50)));
    assertEquals(Ratio.valueOf(38.7897595034), Money.valueOf(12.89).porcentageRelative(5));
    assertEquals(Ratio.valueOf(175), Money.valueOf(100).porcentageRelative(Money.valueOf(175)));
    assertEquals(Ratio.valueOf(8.3333333333), Money.valueOf(120).porcentageRelative(Money.valueOf(10)));
    assertEquals(Ratio.valueOf(0.0813008130), Money.valueOf(12.30).porcentageRelative(Money.UM_CENTAVO));
  }

  @Test
  public void testGetPorcentageEDeduzirPorcentagem() throws Exception {
    final Money valor = Money.valueOf(12.89);
    Ratio porcentageRelative = valor.porcentageRelative(2.89);
    assertEquals(Ratio.valueOf(22.4204809930), porcentageRelative);
    assertEquals(Money.valueOf(2.89), valor.getPorcentage(porcentageRelative));
  }

  @Test
  public void testIsMultiple() throws Exception {
    assertTrue(Money.DEZ.isMultiple(Money.valueOf(5)));
    Assert.assertFalse(Money.valueOf(5).isMultiple(Money.DEZ));
    assertTrue(Money.valueOf(35.55).isMultiple(Money.valueOf(2.37)));
    assertTrue(Money.valueOf(23.70).isMultiple(Money.valueOf(2.37)));
  }

  @Test
  public void testToFormat() throws Exception {
    assertEquals("-0,23", Money.valueOf(-0.23).toFormat());
    assertEquals("0,00", Money.ZERO.toFormat());
    assertEquals("10,00", Money.DEZ.toFormat());
    assertEquals("10,23", Money.valueOf(10.23).toFormat());
  }

  @Test
  public void testToStringReal() throws Exception {
    assertEquals("R$ -0,23", Money.real(-0.23).toString());
    assertEquals("R$ 0,00", Money.real(0).toString());
    assertEquals("R$ 10,00", Money.real(10).toString());
    assertEquals("R$ 10,23", Money.real(10.23).toString());
  }

  @Test
  public void testIsNegative() throws Exception {
    assertTrue(Money.valueOf(-10).isNegative());
    assertFalse(Money.valueOf(0).isNegative());
    assertFalse(Money.valueOf(10).isNegative());
  }

  @Test
  public void testAbs() throws Exception {
    assertEquals(Money.DEZ, Money.valueOf(-10).abs());
    assertEquals(Money.ZERO, Money.valueOf(0).abs());
    assertEquals(Money.DEZ, Money.valueOf(10).abs());
  }

  @Test
  public void testMaiorOuIgualA() throws Exception {
    assertTrue(Money.DEZ.maiorOuIgualA(Money.DEZ));
    assertTrue(Money.DEZ.maiorOuIgualA(Money.UM));
    assertFalse(Money.DEZ.maiorOuIgualA(Money.valueOf(100)));
  }

  @Test
  public void testCopy() throws Exception {
    Money copia = Money.valueOf(12.34).copy();
    assertEquals(Money.valueOf(12.34), copia);
  }

}
