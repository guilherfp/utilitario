package br.com.devsource.utilitario.quantidade;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;

import br.com.devsource.utilitario.quantidade.unidade.Unidade;
import br.com.devsource.utilitario.ratio.Ratio;

/**
 * Patern quantity.
 * @author Guilherme Freitas
 */
public final class Quantidade<U extends Unidade> implements Comparable<Quantidade<U>>, Serializable {
  private static final long serialVersionUID = 1L;

  private Ratio quantia;
  private U unidade;

  Quantidade() {
    super();
  }

  private Quantidade(Ratio ratio, U unidade) {
    Validate.notNull(ratio);
    Validate.notNull(unidade);
    Validate.isTrue(!ratio.isNegative(), "Quantidade negativa inválida");
    this.unidade = unidade;
    quantia = ratio;
  }

  public Ratio getQuantia() {
    return quantia;
  }

  public U getUnidade() {
    return unidade;
  }

  public Quantidade<U> add(Quantidade<U> other) {
    checarUnidade(other);
    Ratio result = padrao().add(other.padrao());
    return new Quantidade<U>(result.divide(base10(unidade)), unidade);
  }

  private void checarUnidade(Quantidade<U> other) {
    if (!unidade.getClass().equals(other.unidade.getClass())) {
      throw new QuantidadeIncompativelException(unidade, other.unidade);
    }
  }

  public Quantidade<U> minus(Quantidade<U> other) {
    checarUnidade(other);
    Ratio result = padrao().minus(other.padrao());
    return new Quantidade<U>(result.divide(base10(unidade)), unidade);
  }

  public Quantidade<U> multiply(Quantidade<U> other) {
    checarUnidade(other);
    return new Quantidade<U>(padrao().mutiply(other.padrao()), unidade);
  }

  public Quantidade<U> divide(Quantidade<U> other) {
    checarUnidade(other);
    return new Quantidade<U>(padrao().divide(other.padrao()), unidade);
  }

  private Ratio padrao() {
    return quantia.mutiply(base10(unidade));
  }

  private Ratio base10(U unidade) {
    return Ratio.valueOf(Math.pow(10, unidade.getMultiplo()));
  }

  public static <U extends Unidade> Quantidade<U> de(Ratio quantia, U unidade) {
    return new Quantidade<U>(quantia, unidade);
  }

  public static <U extends Unidade> Quantidade<U> de(Number quantia, U unidade) {
    return new Quantidade<U>(Ratio.valueOf(quantia), unidade);
  }

  public Quantidade<U> to(U unidadeDesejada) {
    double diferenca = unidade.getMultiplo() - unidadeDesejada.getMultiplo();
    Ratio quantidaDesejada = Ratio.valueOf(Math.pow(10, diferenca));
    return new Quantidade<U>(quantia.mutiply(quantidaDesejada), unidadeDesejada);
  }

  @SuppressWarnings("unchecked")
  public Quantidade<U> toPadrao() {
    return to((U) unidade.getDefaultSI());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((quantia == null) ? 0 : quantia.hashCode());
    result = (prime * result) + ((unidade == null) ? 0 : unidade.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || !(obj instanceof Quantidade<?>)) {
      return false;
    }
    Quantidade<?> other = (Quantidade<?>) obj;
    Ratio a = quantia.mutiply(Ratio.valueOf(Math.pow(10, unidade.getMultiplo())));
    Ratio b = other.quantia.mutiply(Ratio.valueOf(Math.pow(10, other.unidade.getMultiplo())));
    return a.equals(b);
  }

  @Override
  public int compareTo(Quantidade<U> o) {
    return toPadrao().getQuantia().compareTo(o.toPadrao().getQuantia());
  }

  @Override
  public String toString() {
    return String.format("%s %s", quantia.asNumber(), unidade.getValue().getSimbolo()).replace(".",
      ",");
  }
}
