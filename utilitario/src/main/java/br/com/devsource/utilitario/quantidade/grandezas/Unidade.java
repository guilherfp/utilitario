package br.com.devsource.utilitario.quantidade.grandezas;

import java.io.Serializable;

public interface Unidade extends Serializable {

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

}
