package br.com.devsource.utilitario.validador;

import org.junit.Test;

import br.com.devsource.utilitario.validator.ValidateUtils;

public class ValidateUtilsTest {

  @Test(expected = RuntimeException.class)
  public void testValidadeSize_minSize() throws Exception {
    ValidateUtils.size("a").min(2).isValid();
  }

  @Test(expected = RuntimeException.class)
  public void testValidadeSize_minSize_valorInvalido() throws Exception {
    ValidateUtils.size("a").min(-2);
  }

  @Test(expected = RuntimeException.class)
  public void testValidadeSize_maxSize() throws Exception {
    ValidateUtils.size("abc").max(2).isValid();
  }

  @Test(expected = RuntimeException.class)
  public void testValidadeSize_maxSize_valorInvalido() throws Exception {
    ValidateUtils.size("abc").max(-12);
  }

  @Test
  public void testValidadeSize_maxSize_minSize() throws Exception {
    ValidateUtils.size("ab").min(2).max(2).isValid();
  }

  @Test(expected = RuntimeException.class)
  public void testValidadeSize_exactInvalid() throws Exception {
    ValidateUtils.size("ab").exact(13).isValid();
  }

  @Test
  public void testValidadeSize_exact() throws Exception {
    ValidateUtils.size("1234567890123").exact(13).isValid();
  }
}
