package br.com.devsource.utilitario.quantidade.unidade;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Guilherme Pacheco
 */
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

  private final UnidadeValue unidadeValue;

  private Massa(String nome, String simbolo, double multiplo) {
    unidadeValue = new UnidadeValue(nome, simbolo, multiplo);
  }

  public Collection<Massa> convencionais() {
    return Arrays.asList(MILIGRAMA, GRAMA, QUILOGRAMA);
  }

  @Override
  public UnidadeValue getValue() {
    return unidadeValue;
  }

  @Override
  public Massa getDefaultSI() {
    return GRAMA;
  }
}
