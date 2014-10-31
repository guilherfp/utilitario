package br.com.devsource.utilitario.excessao;

/**
 * Exceção para classes utilitárias.
 * @author Guilherme Freitas
 */
public class UtilException extends RuntimeException implements ExcecaoDetalhada {
  private static final long serialVersionUID = 1L;
  private String descricao;

  /**
   * Construtor padrão.
   */
  public UtilException() {
    super();
  }

  /**
   * construtor define a mensagem de erro.
   * @param message {@link String} com a mensagem de erro.
   */
  public UtilException(String message) {
    super(message);
  }

  /**
   * Construtor define a mensagem de erro e a descrição do erro.
   * @param message {@link String} com a mensagem de erro.
   * @param descricao {@link String} com a descrição do erro.
   */
  public UtilException(String message, String descricao) {
    super(message);
    this.descricao = descricao;
  }

  @Override
  public String getDescricao() {
    return descricao;
  }

  /**
   * Define a descrição do erro.
   * @param format Formato da mensagem.
   * @param args Parâmetros da mensagem.
   * @author Guilherme Freitas em 28/05/2014.
   */
  public void setDescricao(String format, Object... args) {
    descricao = String.format(format, args);
  }
}
