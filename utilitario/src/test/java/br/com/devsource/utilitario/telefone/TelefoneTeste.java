package br.com.devsource.utilitario.telefone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TelefoneTeste {

  @Test
  public void testConstructor() throws Exception {
    try {
      new Telefone(null, null);
      fail();
    } catch (Exception e) {
    }
    try {
      new Telefone("", null);
      fail();
    } catch (Exception e) {
    }
    try {
      new Telefone("", "");
      fail();
    } catch (Exception e) {
    }
    try {
      new Telefone("", "12341234");
      fail();
    } catch (Exception e) {
    }
    try {
      new Telefone("62", "");
      fail();
    } catch (Exception e) {
    }
    try {
      new Telefone("62", "1234567");
      fail();
    } catch (Exception e) {
    }
    try {
      new Telefone("62", "1234-567");
      fail();
    } catch (Exception e) {
    }
  }

  @Test
  public void testToString() throws Exception {
    Telefone telefone = new Telefone("62", "12341234");
    assertEquals("(62) 1234-1234", telefone.toString());
  }

}
