package br.com.devsource.utilitario.quantidade.unidade;

import java.io.Serializable;

/**
 * @author Guilherme Pacheco
 */
public class UnidadeValue implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String nome;
  private final String simbolo;
  private final double multiplo;

  UnidadeValue(String nome, String simbolo, double multiplo) {
    this.nome = nome;
    this.simbolo = simbolo;
    this.multiplo = multiplo;
  }

  public String getNome() {
    return nome;
  }

  public String getSimbolo() {
    return simbolo;
  }

  public double getMultiplo() {
    return multiplo;
  }

}
