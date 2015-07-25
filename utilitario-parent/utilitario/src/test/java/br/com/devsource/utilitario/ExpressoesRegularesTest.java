package br.com.devsource.utilitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.devsource.utilitario.ExpressoesRegulares;

/**
 * Classe de teste da classe Expressoes.java
 * @author Guilherme Freitas
 */
public class ExpressoesRegularesTest {

  /**
   * Teste a expressão {@link ExpressoesRegulares#TELEFONE}.
   */
  @Test
  public void testeExpressaoTelefone() {
    assertTrue(ExpressoesRegulares.isValido("(62) 9234-1234", ExpressoesRegulares.TELEFONE));
    assertFalse(ExpressoesRegulares.isValido("(62) 1234-1234", ExpressoesRegulares.TELEFONE));
    assertFalse(ExpressoesRegulares.isValido("(02) 9234-1234", ExpressoesRegulares.TELEFONE));
    assertFalse(ExpressoesRegulares.isValido("(62) 92342-1234", ExpressoesRegulares.TELEFONE));
    assertFalse(ExpressoesRegulares.isValido("(62) 8342-123892", ExpressoesRegulares.TELEFONE));
    assertFalse(ExpressoesRegulares.isValido("(62) 8342-123a2", ExpressoesRegulares.TELEFONE));
  }

  /**
   * Teste a expressão {@link ExpressoesRegulares#CEP}.
   */
  @Test
  public void testeExpressaoCEP() {
    assertTrue(ExpressoesRegulares.isValido("12332-123", ExpressoesRegulares.CEP));
    assertFalse(ExpressoesRegulares.isValido("123322-123", ExpressoesRegulares.CEP));
    assertFalse(ExpressoesRegulares.isValido("12332-1223", ExpressoesRegulares.CEP));
    assertFalse(ExpressoesRegulares.isValido("1233a-1223", ExpressoesRegulares.CEP));
    assertFalse(ExpressoesRegulares.isValido("1233-1q223", ExpressoesRegulares.CEP));
  }

  @Test
  public void testSomenteNumero() throws Exception {
    assertTrue(ExpressoesRegulares.isValido("1234", ExpressoesRegulares.SOMENTE_NUMERO));
    assertTrue(ExpressoesRegulares.isValido("1", ExpressoesRegulares.SOMENTE_NUMERO));
    assertFalse(ExpressoesRegulares.isValido("a12", ExpressoesRegulares.SOMENTE_NUMERO));
    assertFalse(ExpressoesRegulares.isValido("1a2", ExpressoesRegulares.SOMENTE_NUMERO));
    assertFalse(ExpressoesRegulares.isValido("12a", ExpressoesRegulares.SOMENTE_NUMERO));
    assertFalse(ExpressoesRegulares.isValido("", ExpressoesRegulares.SOMENTE_NUMERO));
  }
}
