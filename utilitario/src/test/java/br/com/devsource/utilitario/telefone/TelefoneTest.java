package br.com.devsource.utilitario.telefone;

import static br.com.devsource.utilitario.test.AssertException.assertThrown;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TelefoneTest {

  @Test
  public void testConstructor() throws Exception {
    assertThrown(NullPointerException.class, () -> new Telefone(null, null));
    assertThrown(IllegalArgumentException.class, () -> new Telefone("", null));
    assertThrown(IllegalArgumentException.class, () -> new Telefone("", ""));
    assertThrown(IllegalArgumentException.class, () -> new Telefone("", "12341234"));
    assertThrown(IllegalArgumentException.class, () -> new Telefone("62", ""));
    assertThrown(IllegalArgumentException.class, () -> new Telefone("62", "1234567"));
    assertThrown(IllegalArgumentException.class, () -> new Telefone("62", "1234-567"));
  }

  @Test
  public void testToString() throws Exception {
    Telefone telefone = new Telefone("62", "12341234");
    assertEquals("(62) 1234-1234", telefone.toString());
  }

}
