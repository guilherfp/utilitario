package br.com.devsource.utilitario.quantidade.unidade;

import java.io.Serializable;

/**
 * @author Guilherme Pacheco
 */
public interface Unidade extends Serializable {

  Unidade getDefaultSI();

  UnidadeValue getValue();

  default double getMultiplo() {
    return getValue().getMultiplo();
  }

}
