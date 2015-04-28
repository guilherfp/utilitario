package br.com.devsource.utilitario.quantidade.unidade;

import java.util.Arrays;
import java.util.Collection;

/**
 * Grandezas de comprimento.
 * @author Guilherme Freitas
 */
public enum Comprimento implements Unidade {
  /** yoctômetro (µm): 10^-24 metros. */
  YOCTOMETRO("yoctômetro", "ym", -24),
  /** zeptômetro (µm): 10^-21 metros. */
  ZEPTOMETRO("zepômetro", "zm", -21),
  /** attômetro (µm): 10^-18 metros. */
  ATTOMETRO("attômetro", "am", -18),
  /** fentômetro (µm): 10^-15 metros. */
  FENTOMETRO("fentômetro", "fm", -15),
  /** picômetro (µm): 10^-12 metros. */
  PICOMETRO("picômetro", "pm", -12),
  /** nanômetro (µm): 10^-9 metros. */
  NANOMETRO("nanômetro", "nm", -9),
  /** micrômetro (µm): 10^-6 metros. */
  MICOMETRO("micrometro", "µm", -6),
  /** milímetro (dm): 10^-3 metros. */
  MILIMETRO("milímetro", "mm", -3),
  /** centímetro (dm): 10^-2 metros. */
  CENTIMETRO("centímetro", "cm", -2),
  /** decímetro (dm): 10^-1 metros. */
  DECIMETRO("decímetro", "dm", -1),
  /** Metro (m): Unidade básica do SI. */
  METRO("metro", "m", 0),
  /** decâmetro (dam): 10 metros. */
  DECAMETRO("decâmetro", "dam", 1),
  /** hectômetro (hm): 10^2 metros. */
  HECTOMETRO("hectômetro", "hm", 2),
  /** quilômetro (km): 10^3 metros. */
  QUILOMETRO("quilometro", "km", 3),
  /** megametro (Mm): 10^6 metros. */
  MEGAMETRO("megametro", "Mm", 6),
  /** gigametro (Gm): 10^9 metros. */
  GIGAMETRO("gigametro", "Gm", 9),
  /** terametro (Tm): 10^12 metros. */
  TERAMETRO("terametro", "Tm", 12),
  /** petametro (Pm): 10^15 metros. */
  PETAMETRO("petametro", "Pm", 15),
  /** exametro (Em): 10^18 metros. */
  EXAMETRO("exametro", "Em", 18),
  /** zettametro (Zm): 10^21 metros. */
  ZETTAMETRO("zettametro", "Zm", 21),
  /** yottametro (Ym): 10^24 metros. */
  YOTTAMETRO("yottametro", "Ym", 24);

  private final UnidadeValue unidadeValue;

  private Comprimento(String nome, String simbolo, double multiplo) {
    unidadeValue = new UnidadeValue(nome, simbolo, multiplo);
  }

  public Collection<Comprimento> convencionais() {
    return Arrays.asList(MILIMETRO, CENTIMETRO, METRO, QUILOMETRO);
  }

  @Override
  public UnidadeValue getValue() {
    return unidadeValue;
  }

  @Override
  public Comprimento padraoSI() {
    return METRO;
  }
}
