package br.com.devsource.utilitario.telefone;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
 */
public class TelefoneTest {

  @Test(expected = NullPointerException.class)
  public void testTelefone_DDDNulo() throws Exception {
    new Telefone(null, "234234324");
  }

  @Test(expected = NullPointerException.class)
  public void testTelefone_NumeroNulo() throws Exception {
    new Telefone("62", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTelefone_DDDVazio() throws Exception {
    new Telefone("", "12345678");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTelefone_NumeroVazio() throws Exception {
    new Telefone("52", "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTelefone_NumeroPequeno() throws Exception {
    new Telefone("52", "123456");
  }

  @Test
  public void testToString() throws Exception {
    Telefone telefone = new Telefone("62", "12341234");
    assertEquals("(62) 1234-1234", telefone.toString());
  }

}
