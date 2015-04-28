package br.com.devsource.utilitario.quantidade;

import br.com.devsource.utilitario.quantidade.grandezas.Unidade;

public class QuantidadeIncompativelException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;

  public QuantidadeIncompativelException(Unidade unidadeLeft, Unidade unidadeRight) {
    super(formatMessage(unidadeLeft, unidadeRight));
  }

  private static String formatMessage(Unidade unidadeLeft, Unidade unidadeRight) {
    String format = "Unidades [%s, %s]não são compatíveis";
    return String.format(format, unidadeLeft, unidadeRight);
  }

}
