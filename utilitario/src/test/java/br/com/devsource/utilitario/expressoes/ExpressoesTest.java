package br.com.devsource.utilitario.expressoes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Classe de teste da classe Expressoes.java
 * @author Guilherme Freitas
 */
public class ExpressoesTest {

  /**
   * Teste a expressão {@link Expressoes#TELEFONE}.
   */
  @Test
  public void testeExpressaoTelefone() {
    assertTrue(Expressoes.isValido("(62) 9234-1234", Expressoes.TELEFONE));
    assertFalse(Expressoes.isValido("(62) 1234-1234", Expressoes.TELEFONE));
    assertFalse(Expressoes.isValido("(02) 9234-1234", Expressoes.TELEFONE));
    assertFalse(Expressoes.isValido("(62) 92342-1234", Expressoes.TELEFONE));
    assertFalse(Expressoes.isValido("(62) 8342-123892", Expressoes.TELEFONE));
    assertFalse(Expressoes.isValido("(62) 8342-123a2", Expressoes.TELEFONE));
  }

  /**
   * Teste a expressão {@link Expressoes#CEP}.
   */
  @Test
  public void testeExpressaoCEP() {
    assertTrue(Expressoes.isValido("12332-123", Expressoes.CEP));
    assertFalse(Expressoes.isValido("123322-123", Expressoes.CEP));
    assertFalse(Expressoes.isValido("12332-1223", Expressoes.CEP));
    assertFalse(Expressoes.isValido("1233a-1223", Expressoes.CEP));
    assertFalse(Expressoes.isValido("1233-1q223", Expressoes.CEP));
  }

  @Test
  public void testSomenteNumero() throws Exception {
    assertTrue(Expressoes.isValido("1234", Expressoes.SOMENTE_NUMERO));
    assertTrue(Expressoes.isValido("1", Expressoes.SOMENTE_NUMERO));
    assertFalse(Expressoes.isValido("a12", Expressoes.SOMENTE_NUMERO));
    assertFalse(Expressoes.isValido("1a2", Expressoes.SOMENTE_NUMERO));
    assertFalse(Expressoes.isValido("12a", Expressoes.SOMENTE_NUMERO));
    assertFalse(Expressoes.isValido("", Expressoes.SOMENTE_NUMERO));
  }
}
