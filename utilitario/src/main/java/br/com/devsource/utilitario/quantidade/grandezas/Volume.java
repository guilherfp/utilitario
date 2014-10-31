package br.com.devsource.utilitario.quantidade.grandezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.devsource.utilitario.quantidade.Quantidade;

public enum Volume implements Unidade {

  /** yoctômetro (µm³³): 10^-24 metros cúbicos. */
  YOCTOMETRO_CUBICO("yoctômetro", "ym", -24),
  /** zeptômetro (µm³): 10^-21 metros cúbicos. */
  ZEPTOMETRO_CUBICO("zepômetro", "zm", -21),
  /** attômetro (µm³): 10^-18 metros cúbicos. */
  ATTOMETRO_CUBICO("attômetro", "am", -18),
  /** fentômetro (µm³): 10^-15 metros cúbicos. */
  FENTOMETRO_CUBICO("fentômetro", "fm", -15),
  /** picômetro (µm³): 10^-12 metros cúbicos. */
  PICOMETRO_CUBICO("picômetro", "pm", -12),
  /** nanômetro (µm³): 10^-9 metros cúbicos. */
  NANOMETRO_CUBICO("nanômetro", "nm", -9),
  /** micrômetro (µm³): 10^-6 metros cúbicos. */
  MICOMETRO_CUBICO("micrometro", "µm", -6),
  /** milímetro (dm³): 10^-3 metros cúbicos. */
  MILIMETRO_CUBICO("milímetro", "mm", -3),
  /** centímetro (dm³): 10^-2 metros cúbicos. */
  CENTIMETRO_CUBICO("centímetro", "cm", -2),
  /** decímetro (dm³): 10^-1 metros cúbicos. */
  DECIMETRO_CUBICO("decímetro", "dm", -1),
  /** Metro (m³): Unidade básica do SI. */
  METRO_CUBICO("metro", "m", 0),
  /** decâmetro (dam³): 10 metros cúbicos. */
  DECAMETRO_CUBICO("decâmetro", "dam", 1),
  /** hectômetro (hm³): 10^2 metros cúbicos. */
  HECTOMETRO_CUBICO("hectômetro", "hm", 2),
  /** quilômetro (km³): 10^3 metros cúbicos. */
  QUILOMETRO_CUBICO("quilometro", "km", 3),
  /** megametro (Mm³): 10^6 metros cúbicos. */
  MEGAMETRO_CUBICO("megametro", "Mm", 6),
  /** gigametro (Gm³): 10^9 metros cúbicos. */
  GIGAMETRO_CUBICO("gigametro", "Gm", 9),
  /** terametro (Tm³): 10^12 metros cúbicos. */
  TERAMETRO_CUBICO("terametro", "Tm", 12),
  /** petametro (Pm³): 10^15 metros cúbicos. */
  PETAMETRO_CUBICO("petametro", "Pm", 15),
  /** exametro (Em³): 10^18 metros cúbicos. */
  EXAMETRO_CUBICO("exametro", "Em", 18),
  /** zettametro (Zm³): 10^21 metros cúbicos. */
  ZETTAMETRO_CUBICO("zettametro", "Zm", 21),
  /** yottametro (Ym³): 10^24 metros cúbicos. */
  YOTTAMETRO_CUBICO("yottametro", "Ym", 24);

  private static final Collection<Volume> convencionais = new ArrayList<>(7);

  static {
    convencionais.add(Volume.MILIMETRO_CUBICO);
    convencionais.add(Volume.CENTIMETRO_CUBICO);
    convencionais.add(Volume.METRO_CUBICO);
  }

  private final String nome;
  private final String simbolo;
  private final double multiplo;

  private Volume(String nome, String simbolo, double multiplo) {
    this.nome = nome;
    this.simbolo = simbolo;
    this.multiplo = multiplo;
  }

  public static Collection<Volume> convencionais() {
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
  public Volume padraoSI() {
    return METRO_CUBICO;
  }

  @Override
  public Quantidade<Volume> de(Number quantida) {
    return Quantidade.de(1, this);
  }
}
