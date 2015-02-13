package br.com.devsource.utilitario.ratio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class RatioTest {

  @Test
  public void testMinus() {
    assertEquals(Ratio.valueOf(-2), Ratio.valueOf(1).minus(Ratio.valueOf(3)));
    assertEquals(Ratio.valueOf(0.08), Ratio.valueOf(0.1).minus(Ratio.valueOf(0.02)));
    assertEquals(Ratio.valueOf(0.000_8), Ratio.valueOf(0.001).minus(Ratio.valueOf(0.000_2)));
  }

  @Test
  public void testMinus_negate() {
    assertEquals(Ratio.valueOf(4), Ratio.valueOf(1).minus(Ratio.valueOf(3).negate()));
    assertEquals(Ratio.valueOf(0.12), Ratio.valueOf(0.1).minus(Ratio.valueOf(0.02).negate()));
    assertEquals(Ratio.valueOf(0.001_2), Ratio.valueOf(0.001).minus(Ratio.valueOf(0.000_2).negate()));
  }

  @Test
  public void testAdd() {
    assertEquals(Ratio.valueOf(4), Ratio.valueOf(1).add(Ratio.valueOf(3)));
    assertEquals(Ratio.valueOf(0.12), Ratio.valueOf(0.1).add(Ratio.valueOf(0.02)));
    assertEquals(Ratio.valueOf(0.001_2), Ratio.valueOf(0.001).add(Ratio.valueOf(0.000_2)));
  }

  @Test
  public void testAdd_negate() {
    assertEquals(Ratio.valueOf(2), Ratio.valueOf(3).add(Ratio.valueOf(-1)));
    assertEquals(Ratio.valueOf(0.08), Ratio.valueOf(0.1).add(Ratio.valueOf(-0.02)));
    assertEquals(Ratio.valueOf(0.000_8), Ratio.valueOf(0.001).add(Ratio.valueOf(-0.000_2)));
  }

  @Test
  public void testMultiply() {
    assertEquals(Ratio.valueOf(24_948_522), Ratio.valueOf(124122).mutiply(Ratio.valueOf(201)));
    assertEquals(Ratio.valueOf(3), Ratio.valueOf(3).mutiply(Ratio.valueOf(1)));
    assertEquals(Ratio.valueOf(0.002), Ratio.valueOf(0.1).mutiply(Ratio.valueOf(0.02)));
    assertEquals(Ratio.valueOf(0.000_000_2), Ratio.valueOf(0.001).mutiply(Ratio.valueOf(0.000_2)));
  }

  @Test
  public void testMultiply_porInteiro() {
    assertEquals(Ratio.valueOf(3), Ratio.valueOf(3).mutiply(1));
    assertEquals(Ratio.valueOf(-3), Ratio.valueOf(3).mutiply(-1));
    assertEquals(Ratio.ZERO, Ratio.valueOf(3).mutiply(0));
    assertEquals(Ratio.valueOf(3), Ratio.valueOf(-3).mutiply(-1));
  }

  @Test
  public void testMultiplyNegate() {
    assertEquals(Ratio.valueOf(-24_948_522), Ratio.valueOf(124_122).mutiply(Ratio.valueOf(201).negate()));
    assertEquals(Ratio.valueOf(-3), Ratio.valueOf(3).mutiply(Ratio.valueOf(1)).negate());
    assertEquals(Ratio.valueOf(-0.002), Ratio.valueOf(0.1).mutiply(Ratio.valueOf(0.02)).negate());
    assertEquals(Ratio.valueOf(-0.000_000_2), Ratio.valueOf(0.001).mutiply(Ratio.valueOf(0.000_2).negate()));
  }

  @Test
  public void testDivide() {
    assertEquals(Ratio.valueOf(124_122), Ratio.valueOf(24_948_522).divide(Ratio.valueOf(201)));
    assertEquals(Ratio.valueOf(0.000_002_5), Ratio.valueOf(0.000_005).divide(Ratio.valueOf(2)));
  }

  @Test
  public void testDivide_negate() {
    assertEquals(Ratio.valueOf(-124_122), Ratio.valueOf(24_948_522).divide(Ratio.valueOf(201).negate()));
    assertEquals(Ratio.valueOf(-0.000_002_5), Ratio.valueOf(0.000_005).divide(Ratio.valueOf(2).negate()));
  }

  @Test
  public void testNegate() {
    assertEquals(Ratio.valueOf(-24_948_522), Ratio.valueOf(24_948_522).negate());
    assertEquals(Ratio.valueOf(24_948_522), Ratio.valueOf(-24_948_522).negate());
  }

  @Test
  public void testIsZero() {
    assertFalse(Ratio.valueOf(10).isZero());
    assertTrue(Ratio.valueOf(0).isZero());
    assertTrue(Ratio.valueOf(10).add(Ratio.valueOf(-10)).isZero());
    assertTrue(Ratio.valueOf(10).mutiply(Ratio.valueOf(0)).isZero());
  }

  @Test
  public void testIsOne() {
    Assert.assertFalse(Ratio.valueOf(10).isOne());
    assertTrue(Ratio.valueOf(1).isOne());
    assertTrue(Ratio.valueOf(10).divide(Ratio.valueOf(10)).isOne());
  }

  @Test
  public void testIsMultiply() {
    assertTrue(Ratio.DEZ.isMultiply(Ratio.UM));
    assertTrue(Ratio.valueOf(20).isMultiply(Ratio.valueOf(5)));
    assertTrue(Ratio.valueOf(-21).isMultiply(Ratio.valueOf(-7)));

    assertFalse(Ratio.valueOf(21).isMultiply(Ratio.DEZ));
  }

  @Test
  public void testMenorQue() {
    assertTrue(Ratio.UM.menorQue(Ratio.DEZ));
    assertTrue(Ratio.valueOf(-1).menorQue(Ratio.ZERO));
    assertFalse(Ratio.DEZ.menorQue(Ratio.UM));
  }

  @Test
  public void testMaiorQue() {
    assertTrue(Ratio.UM.maiorQue(Ratio.ZERO));
    assertTrue(Ratio.valueOf(-1).maiorQue(Ratio.valueOf(-1.2)));
    assertTrue(Ratio.UM.maiorQue(Ratio.valueOf(-1.3)));

    assertFalse(Ratio.UM.maiorQue(Ratio.DEZ));
    assertFalse(Ratio.valueOf(-0.0001).maiorQue(Ratio.ZERO));
  }

  @Test
  public void testMaiorOuIgualA() {
    assertTrue(Ratio.UM.maiorOuIqualA(Ratio.ZERO));
    assertTrue(Ratio.UM.maiorOuIqualA(Ratio.UM));
    assertTrue(Ratio.valueOf(-1).maiorOuIqualA(Ratio.valueOf(-1.2)));
    assertTrue(Ratio.UM.maiorOuIqualA(Ratio.valueOf(-1.3)));

    assertFalse(Ratio.UM.maiorOuIqualA(Ratio.DEZ));
    assertFalse(Ratio.valueOf(-0.0001).maiorOuIqualA(Ratio.ZERO));
  }

  @Test
  public void testPorcentageRelative() {
    assertEquals(Ratio.valueOf(10), Ratio.UM.porcentageRelative(Ratio.DEZ));
    assertEquals(Ratio.valueOf(50), Ratio.valueOf(5).porcentageRelative(Ratio.DEZ));
  }

  @Test
  public void testValueOf_int() throws Exception {
    assertEquals(Ratio.ZERO, Ratio.valueOf(0));
    assertEquals(Ratio.DEZ, Ratio.valueOf(10));
  }

  @Test
  public void testValueOf_long() throws Exception {
    assertEquals(Ratio.DEZ, Ratio.valueOf(10L));
    assertEquals(Ratio.ZERO, Ratio.valueOf(0L));
  }

  @Test
  public void testValueOf_BigDecimal() throws Exception {
    assertEquals(Ratio.DEZ, Ratio.valueOf(BigDecimal.TEN));
    assertEquals(Ratio.ZERO, Ratio.valueOf(BigDecimal.ZERO));
  }

  @Test
  public void testValueOf_String() throws Exception {
    assertEquals(Ratio.DEZ, Ratio.valueOf("10"));
    assertEquals(Ratio.ZERO, Ratio.valueOf("0"));

    assertEquals(Ratio.valueOf(5), Ratio.valueOf("10", "2"));
    assertEquals(Ratio.UM, Ratio.valueOf("10", "10"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueOf_StringDenominadorInvalido() throws Exception {
    Ratio.valueOf("1", "0");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueOf_BigDecimalDenominadorInvalido() throws Exception {
    Ratio.valueOf(BigDecimal.ONE, BigDecimal.ZERO);
  }

  @Test
  public void testAsNumber() throws Exception {
    assertTrue(BigDecimal.ZERO.compareTo(Ratio.ZERO.asNumber()) == 0);
    assertTrue(BigDecimal.TEN.compareTo(Ratio.DEZ.asNumber()) == 0);
    assertTrue(BigDecimal.valueOf(6).compareTo(Ratio.valueOf("12", "2").asNumber()) == 0);
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue(Ratio.ZERO.equals(Ratio.valueOf(0)));
    assertFalse(Ratio.ZERO.equals(null));
    assertTrue(Ratio.ZERO.asNumber().equals(BigDecimal.ZERO));
    assertFalse(Ratio.ZERO.equals(BigDecimal.TEN));
    assertFalse(Ratio.ZERO.equals("0"));
  }

  @Test
  public void testToString() throws Exception {
    assertEquals("-1,023", Ratio.valueOf(-1.023).toString());
    assertEquals("-1", Ratio.valueOf(-1).toString());
    assertEquals("0", Ratio.ZERO.toString());
    assertEquals("12", Ratio.valueOf(12).toString());
    assertEquals("12,234", Ratio.valueOf(12.234).toString());
  }

  @Test
  public void testIntValue() throws Exception {
    assertEquals(-1, Ratio.valueOf(-1).intValue());
    assertEquals(0, Ratio.ZERO.intValue());
    assertEquals(1, Ratio.UM.intValue());
  }

  @Test
  public void testLongValue() throws Exception {
    assertEquals(-1L, Ratio.valueOf(-1).longValue());
    assertEquals(0L, Ratio.ZERO.longValue());
    assertEquals(1L, Ratio.UM.longValue());
  }

  @Test
  public void testFloatValue() throws Exception {
    assertEquals(-1.0, Ratio.valueOf(-1).floatValue(), 0);
    assertEquals(-1.23, Ratio.valueOf(-1.23).floatValue(), 0.000001);
    assertEquals(0.0, Ratio.ZERO.floatValue(), 0);
    assertEquals(1.0, Ratio.UM.floatValue(), 0);
    assertEquals(4.64, Ratio.valueOf(4.64).floatValue(), 0.000001);
  }

  @Test
  public void testIsNegative() throws Exception {
    assertTrue(Ratio.valueOf(-0.12).isNegative());
    assertFalse(Ratio.valueOf(0.12).isNegative());
    assertFalse(Ratio.valueOf(0).isNegative());
    assertFalse(Ratio.valueOf(-0).isNegative());
    assertFalse(Ratio.valueOf(1).isNegative());
  }
}
