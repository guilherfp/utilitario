package br.com.devsource.utilitario.quantidade;

import br.com.devsource.utilitario.quantidade.grandezas.Unidade;

public class QuantidadeIncompativelException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;

  public QuantidadeIncompativelException(Unidade unidade, Unidade unidade2) {
    super("Unidades não são compatíveis");
  }

}
