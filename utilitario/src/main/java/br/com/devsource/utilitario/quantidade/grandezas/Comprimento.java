package br.com.devsource.utilitario.quantidade.grandezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.devsource.utilitario.quantidade.Quantidade;
import br.com.devsource.utilitario.ratio.Ratio;

/**
 * Grandezas de comprimento.
 * @author Guilherme Freitas em 28/05/2014.
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

  private static final Collection<Comprimento> convencionais = new ArrayList<>(7);

  static {
    convencionais.add(Comprimento.MILIMETRO);
    convencionais.add(Comprimento.CENTIMETRO);
    convencionais.add(Comprimento.METRO);
    convencionais.add(Comprimento.QUILOMETRO);
  }

  private final String nome;
  private final String simbolo;
  private final double multiplo;

  private Comprimento(String nome, String simbolo, double multiplo) {
    this.nome = nome;
    this.simbolo = simbolo;
    this.multiplo = multiplo;
  }

  /**
   * @return Retorna coleção de valores de uso mais convêncional.
   * @author Guilherme Freitas em 28/05/2014.
   */
  public final Collection<Comprimento> convencionais() {
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
  public Comprimento padraoSI() {
    return METRO;
  }

  @Override
  public Quantidade<Comprimento> de(Number quantida) {
    return Quantidade.de(quantida, this);
  }

  public Quantidade<Comprimento> um() {
    return Quantidade.de(Ratio.UM, this);
  }

}
