package br.com.devsource.utilitario.quantidade.unidade;

import java.io.Serializable;

public interface Unidade extends Serializable {

  Unidade getDefaultSI();

  UnidadeValue getValue();

}