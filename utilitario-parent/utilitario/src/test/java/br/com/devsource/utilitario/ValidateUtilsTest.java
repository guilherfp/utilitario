package br.com.devsource.utilitario;

import org.junit.Test;

import br.com.devsource.utilitario.ValidateUtils;
import br.com.devsource.utilitario.money.Money;
import br.com.devsource.utilitario.ratio.Ratio;

public class ValidateUtilsTest {

  @Test
  public void testMaiorQueUmCentao() throws Exception {
    ValidateUtils.isMaiorQueUmCentavo(Money.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaiorQueUmCentao_igualAZero() throws Exception {
    ValidateUtils.isMaiorQueUmCentavo(Money.ZERO);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaiorQueUmCentao_negativo() throws Exception {
    ValidateUtils.isMaiorQueUmCentavo(Money.valueOf(-1));
  }

  @Test
  public void testMoneyIsMaiorDoQue() throws Exception {
    ValidateUtils.isMaiorDoQue(Money.DEZ, Money.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMaiorDoQueInvalido() throws Exception {
    ValidateUtils.isMaiorDoQue(Money.UM, Money.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMaiorDoQueInvalido_valoresIguais() throws Exception {
    ValidateUtils.isMaiorDoQue(Money.UM, Money.UM);
  }

  @Test
  public void testMoneyIsMenorDoQue() throws Exception {
    ValidateUtils.isMenorDoQue(Money.UM, Money.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMenorDoQueInvalido() throws Exception {
    ValidateUtils.isMenorDoQue(Money.DEZ, Money.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsMenorDoQueInvalido_valoresIguais() throws Exception {
    ValidateUtils.isMenorDoQue(Money.DEZ, Money.DEZ);
  }

  @Test
  public void testRatioIsMaiorDoQue() throws Exception {
    ValidateUtils.isMaiorDoQue(Ratio.DEZ, Ratio.UM);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRatioIsMaiorDoQueInvalido() throws Exception {
    ValidateUtils.isMaiorDoQue(Ratio.UM, Ratio.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRatioIsMaiorDoQueInvalido_valoresIguais() throws Exception {
    ValidateUtils.isMaiorDoQue(Ratio.UM, Ratio.UM);
  }

  @Test
  public void testMoneyIsMaiorOuIgualA() throws Exception {
    ValidateUtils.isMaiorOuIqualA(Money.DEZ, Money.UM);
    ValidateUtils.isMaiorOuIqualA(Money.DEZ, Money.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoneyIsNaoMaiorOuIgualA() throws Exception {
    ValidateUtils.isMaiorOuIqualA(Money.UM, Money.DEZ);
  }

  @Test
  public void testRatioIsMaiorOuIgualA() throws Exception {
    ValidateUtils.isMaiorOuIgualA(Ratio.DEZ, Ratio.UM);
    ValidateUtils.isMaiorOuIgualA(Ratio.DEZ, Ratio.DEZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRatioIsNaoMaiorOuIgualA() throws Exception {
    ValidateUtils.isMaiorOuIgualA(Ratio.UM, Ratio.DEZ);
  }

}
