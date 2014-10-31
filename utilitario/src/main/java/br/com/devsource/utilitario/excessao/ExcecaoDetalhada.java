package br.com.devsource.utilitario.excessao;

/**
 * Interface para Exceções com mensagem de descrição
 * @author Guilherme Freitas
 */
public interface ExcecaoDetalhada {
  /**
   * Obtem a mensagem do erro.
   * @return {@link String} com mensagem do erro.
   */
  String getMessage();

  /**
   * Obtem a descrição do erro.
   * @return {@link String} com possível descrição do erro.
   */
  String getDescricao();
}
