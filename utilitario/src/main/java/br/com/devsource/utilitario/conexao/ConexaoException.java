package br.com.devsource.utilitario.conexao;

/**
 * @author Guilherme Pacheco
 */
public class ConexaoException extends Exception {
  private static final long serialVersionUID = 1L;

  public ConexaoException(String message, Throwable cause, boolean enableSuppression,
                          boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ConexaoException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConexaoException(String message) {
    super(message);
  }

  public ConexaoException(Throwable cause) {
    super(cause);
  }

}
