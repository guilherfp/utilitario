package br.com.devsource.utilitario.validador;

import org.junit.Test;

import br.com.devsource.utilitario.money.Money;
import br.com.devsource.utilitario.ratio.Ratio;
import br.com.devsource.utilitario.validator.ValidateUtil;

public class ValidateUtilTest {

  @Test
  public void testMaiorQueUmCentao() throws Exception {
    ValidateUtil.isMaiorQueUmCentavo(Money.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaiorQueUmCentao_igualAZero() throws Exception {
    ValidateUtil.isMaiorQueUmCentavo(Money.ZERO);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaiorQueUmCentao_negativo() throws Exception {
    ValidateUtil.isMaiorQueUmCentavo(Money.valueOf(-1));
  }

  @Test
  public void testMoneyIsMaiorDoQue() throws Exception {
    ValidateUtil.isMaiorDoQue(Money.DEZ, Money.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMaiorDoQueInvalido() throws Exception {
    ValidateUtil.isMaiorDoQue(Money.UM, Money.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMaiorDoQueInvalido_valoresIguais() throws Exception {
    ValidateUtil.isMaiorDoQue(Money.UM, Money.UM);
  }

  @Test
  public void testMoneyIsMenorDoQue() throws Exception {
    ValidateUtil.isMenorDoQue(Money.UM, Money.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMenorDoQueInvalido() throws Exception {
    ValidateUtil.isMenorDoQue(Money.DEZ, Money.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMenorDoQueInvalido_valoresIguais() throws Exception {
    ValidateUtil.isMenorDoQue(Money.DEZ, Money.DEZ);
  }

  @Test
  public void testRatioIsMaiorDoQue() throws Exception {
    ValidateUtil.isMaiorDoQue(Ratio.DEZ, Ratio.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRatioIsMaiorDoQueInvalido() throws Exception {
    ValidateUtil.isMaiorDoQue(Ratio.UM, Ratio.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRatioIsMaiorDoQueInvalido_valoresIguais() throws Exception {
    ValidateUtil.isMaiorDoQue(Ratio.UM, Ratio.UM);
  }

  @Test
  public void testMoneyIsMaiorOuIgualA() throws Exception {
    ValidateUtil.isMaiorOuIqualA(Money.DEZ, Money.UM);
    ValidateUtil.isMaiorOuIqualA(Money.DEZ, Money.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsNaoMaiorOuIgualA() throws Exception {
    ValidateUtil.isMaiorOuIqualA(Money.UM, Money.DEZ);
  }

  @Test
  public void testRatioIsMaiorOuIgualA() throws Exception {
    ValidateUtil.isMaiorOuIgualA(Ratio.DEZ, Ratio.UM);
    ValidateUtil.isMaiorOuIgualA(Ratio.DEZ, Ratio.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRatioIsNaoMaiorOuIgualA() throws Exception {
    ValidateUtil.isMaiorOuIgualA(Ratio.UM, Ratio.DEZ);
  }

}
