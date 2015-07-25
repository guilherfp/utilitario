package br.com.devsource.utilitario.quantidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import br.com.devsource.utilitario.quantidade.unidade.Massa;

/**
 * @author Guilherme Pacheco
 */
public class QuantidadeMassaTest {

  @Test
  public void testEquals() throws Exception {
    Quantidade<Massa> umQuilo = Quantidade.de(1, Massa.QUILOGRAMA);
    assertTrue(umQuilo.equals(Quantidade.de(1000, Massa.GRAMA)));
    assertTrue(umQuilo.equals(Quantidade.de(1_000_000, Massa.MILIGRAMA)));
  }

  @Test
  public void testAdd() throws Exception {
    Quantidade<Massa> umQuilo = Quantidade.de(1, Massa.QUILOGRAMA);
    Quantidade<Massa> cemGramas = Quantidade.de(100, Massa.GRAMA);
    assertEquals(Quantidade.de(1.1, Massa.QUILOGRAMA), umQuilo.add(cemGramas));
    assertEquals(Quantidade.de(11, Massa.HECTOGRAMA), umQuilo.add(cemGramas));
    assertEquals(Quantidade.de(110, Massa.DECAGRAMA), umQuilo.add(cemGramas));
    assertEquals(Quantidade.de(1_100, Massa.GRAMA), umQuilo.add(cemGramas));
    assertEquals(Quantidade.de(11_000, Massa.DECIGRAMA), umQuilo.add(cemGramas));
    assertEquals(Quantidade.de(110_000, Massa.CENTIGRAMA), umQuilo.add(cemGramas));
    assertEquals(Quantidade.de(1_100_000, Massa.MILIGRAMA), umQuilo.add(cemGramas));
  }

  @Test
  public void testMinus() throws Exception {
    Quantidade<Massa> umaGrama = Quantidade.de(1, Massa.GRAMA);
    Quantidade<Massa> umMiligrama = Quantidade.de(1, Massa.MILIGRAMA);
    assertEquals(Quantidade.de(0.999, Massa.GRAMA), umaGrama.minus(umMiligrama));
  }

  @Test
  public void testConvertePara() throws Exception {
    Quantidade<Massa> umKilo = Quantidade.de(1, Massa.QUILOGRAMA);
    assertEquals(Quantidade.de(1_000, Massa.GRAMA), umKilo.to(Massa.GRAMA));
  }

  @Test
  public void testConverteParaPadrao() throws Exception {
    Quantidade<Massa> umKilo = Quantidade.de(10, Massa.QUILOGRAMA);
    assertEquals(Quantidade.de(10_000, Massa.GRAMA), umKilo.toPadrao());
  }

  @Test
  public void testCompareTo() throws Exception {
    Quantidade<Massa> umKilo = Quantidade.de(1, Massa.QUILOGRAMA);
    Quantidade<Massa> doisKilo = Quantidade.de(2, Massa.QUILOGRAMA);
    Quantidade<Massa> tresKilo = Quantidade.de(3, Massa.QUILOGRAMA);
    Quantidade<Massa> umaGrama = Quantidade.de(1, Massa.GRAMA);
    Quantidade<Massa> quinhentasGramas = Quantidade.de(500, Massa.GRAMA);

    List<Quantidade<Massa>> pesos = new ArrayList<>();
    pesos.add(umKilo);
    pesos.add(doisKilo);
    pesos.add(tresKilo);
    pesos.add(umaGrama);
    pesos.add(quinhentasGramas);

    Collections.sort(pesos);

    pesos.get(0).equals(umaGrama);
    pesos.get(1).equals(quinhentasGramas);
    pesos.get(2).equals(umKilo);
    pesos.get(3).equals(doisKilo);
    pesos.get(4).equals(tresKilo);
  }
}
