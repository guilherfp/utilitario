package br.com.devsource.utilitario.test;

import org.junit.Assert;

public class AssertException {

  @SuppressWarnings("unchecked")
  public static <E extends Throwable> E assertThrown(Class<E> exception, TestRun testRun) {
    try {
      testRun.run();
      Assert.fail("Exceção esperada não foi lançada");
      return null;
    } catch (AssertionError ex) {
      throw ex;
    } catch (Throwable ex) {
      if (ex.getClass() != exception) {
        throw new RuntimeException("Exceção esperada não é do mesmo tipo esperado");
      } else {
        return (E) ex;
      }
    }
  }
}
