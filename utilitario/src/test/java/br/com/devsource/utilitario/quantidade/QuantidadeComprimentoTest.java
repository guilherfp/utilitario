package br.com.devsource.utilitario.quantidade;

import static br.com.devsource.utilitario.test.AssertException.assertThrown;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.devsource.utilitario.quantidade.unidade.Comprimento;
import br.com.devsource.utilitario.ratio.Ratio;

public class QuantidadeComprimentoTest {

  @Test
  public void testDe_numeroInvalido() throws Exception {
    assertThrown(NullPointerException.class, () -> Quantidade.de(null, Comprimento.METRO));
    assertThrown(NullPointerException.class, () -> Quantidade.de(1, null));
    assertThrown(IllegalArgumentException.class, () -> Quantidade.de(-0.0001, Comprimento.METRO));
  }

  @Test
  public void testDe() throws Exception {
    Quantidade<Comprimento> quantidade = Quantidade.de(Ratio.UM, Comprimento.METRO);
    assertEquals(quantidade, Quantidade.de(1, Comprimento.METRO));
  }

  @Test
  public void testeConversao() {
    Quantidade<Comprimento> milMetros = Quantidade.de(Ratio.valueOf(1_000), Comprimento.METRO);
    assertEquals(Quantidade.de(Ratio.valueOf(1), Comprimento.QUILOMETRO), milMetros
      .to(Comprimento.QUILOMETRO));

    Quantidade<Comprimento> cemMetros = Quantidade.de(Ratio.valueOf(100), Comprimento.METRO);
    assertEquals(Quantidade.de(Ratio.valueOf(0.1), Comprimento.QUILOMETRO), cemMetros
      .to(Comprimento.HECTOMETRO));

    Quantidade<Comprimento> dezDecametros =
        Quantidade.de(Ratio.valueOf(100), Comprimento.DECAMETRO);
    assertEquals(Quantidade.de(Ratio.valueOf(1), Comprimento.QUILOMETRO), dezDecametros);

    assertEquals(Quantidade.de(Ratio.valueOf(1_000_000), Comprimento.MILIMETRO), milMetros
      .to(Comprimento.MILIMETRO));
  }

  @Test
  public void testAdd() {
    Quantidade<Comprimento> milMetros = Quantidade.de(Ratio.valueOf(1_000), Comprimento.METRO);
    Quantidade<Comprimento> cemMetros = Quantidade.de(Ratio.valueOf(100), Comprimento.METRO);

    Quantidade<Comprimento> resultado = milMetros.add(cemMetros);

    assertEquals(Quantidade.de(Ratio.valueOf(1_100), Comprimento.METRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(110), Comprimento.DECAMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(11), Comprimento.HECTOMETRO), resultado);

    assertEquals(Quantidade.de(Ratio.valueOf(1.1), Comprimento.QUILOMETRO), resultado);

    assertEquals(Quantidade.de(Ratio.valueOf(0.001_1), Comprimento.MEGAMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(0.000_001_1), Comprimento.GIGAMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(0.000_000_001_1), Comprimento.TERAMETRO), resultado);
  }

  @Test
  public void testMinus() {
    Quantidade<Comprimento> milMetros = Quantidade.de(Ratio.valueOf(1_000), Comprimento.METRO);
    Quantidade<Comprimento> cemMetros = Quantidade.de(Ratio.valueOf(100), Comprimento.METRO);

    Quantidade<Comprimento> resultado = milMetros.minus(cemMetros);

    assertEquals(Quantidade.de(Ratio.valueOf(0.9), Comprimento.QUILOMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(9), Comprimento.HECTOMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(90), Comprimento.DECAMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(900), Comprimento.METRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(9_000), Comprimento.DECIMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(90_000), Comprimento.CENTIMETRO), resultado);
    assertEquals(Quantidade.de(Ratio.valueOf(900_000), Comprimento.MILIMETRO), resultado);
  }

  @Test
  public void testQuantidadeDeUnidade() throws Exception {
    Quantidade<Comprimento> quantidade = Quantidade.de(Ratio.UM, Comprimento.METRO);
    assertEquals(Quantidade.de(Ratio.UM, Comprimento.METRO), quantidade);
  }
}
