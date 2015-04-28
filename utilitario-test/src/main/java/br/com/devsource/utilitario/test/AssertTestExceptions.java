package br.com.devsource.utilitario.test;

import org.junit.Assert;

/**
 * @author Guilherme Pacheco
 */
public class AssertTestExceptions {

  private AssertTestExceptions() {
    super();
  }

  public static <E extends Exception> void assertThrown(Class<E> exception, TestRun testRun) {
    try {
      testRun.run();
      Assert.fail("Exceção esperada não foi lançada");
    } catch (AssertionError ex) {
      throw ex;
    } catch (Exception ex) {
      if (ex.getClass() != exception) {
        throw new AssertionError("Exceção esperada não é do mesmo tipo esperado", ex);
      }
    }
  }
}
