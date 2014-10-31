package br.com.devsource.utilitario.quantidade.grandezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.devsource.utilitario.quantidade.Quantidade;
import br.com.devsource.utilitario.ratio.Ratio;

public enum Massa implements Unidade {
  /** miligrama: 10^-3 gramas. */
  MILIGRAMA("miligrama", "mg", -3),
  /** centrigrama: 10^-2 gramas. */
  CENTIGRAMA("centigrama", "cg", -2),
  /** decigrama: 10^-1 gramas. */
  DECIGRAMA("decigrama", "dg", -1),
  /** grama: Unidade básica do SI. */
  GRAMA("grama", "g", 0),
  /** decagrama: 10 gramas. */
  DECAGRAMA("decagrama", "dag", 1),
  /** hectograma: 10^2 gramas. */
  HECTOGRAMA("hectograma", "hg", 2),
  /** quillograma: 10^3 gramas. */
  QUILOGRAMA("quilograma", "kg", 3),
  /** miriagrama: 10^4 gramas. */
  MIRIAGRAMA("miriagrama", "mag", 4);

  private static final Collection<Massa> convencionais = new ArrayList<>(7);

  static {
    convencionais.add(Massa.QUILOGRAMA);
    convencionais.add(Massa.GRAMA);
    convencionais.add(Massa.MILIGRAMA);
  }

  private final String nome;
  private final String simbolo;
  private final double multiplo;

  private Massa(String nome, String simbolo, double multiplo) {
    this.nome = nome;
    this.simbolo = simbolo;
    this.multiplo = multiplo;
  }

  public final Collection<Massa> convencionais() {
    return Collections.unmodifiableCollection(convencionais);
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getSimbolo() {
    return simbolo;
  }

  @Override
  public double getMultiplo() {
    return multiplo;
  }

  @Override
  public Massa padraoSI() {
    return GRAMA;
  }

  @Override
  public Quantidade<Massa> de(Number quantida) {
    return Quantidade.de(quantida, this);
  }

  public Quantidade<Massa> um() {
    return Quantidade.de(Ratio.UM, this);
  }
}
