package br.com.devsource.utilitario.quantidade.grandezas;

import br.com.devsource.utilitario.quantidade.Quantidade;

public interface Unidade {

  /**
   * @return Retorna o nome.
   */
  String getNome();

  /**
   * @return Retorna o símbolo.
   */
  String getSimbolo();

  /**
   * @return Retorna quantidade múltipla referente ao seu valor padrão no SI.
   */
  double getMultiplo();

  Unidade padraoSI();

  Quantidade<?> de(Number quantida);

}
