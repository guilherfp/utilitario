package br.com.devsource.utilitario.ratio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.devsource.utilitario.TextoUtils;

/**
 * Ratio (Rácio,Razão, Proporção).</br>
 * Permitir manipular frações minimizando as operações de divisão.
 * @author Guilherme Freitas.
 */
public final class Ratio extends Number implements Comparable<Ratio>, Serializable {
  private static final long serialVersionUID = -8761854570136047473L;

  /** Proporção igual a ZERO */
  public static final Ratio ZERO = new Ratio(BigDecimal.ZERO, BigDecimal.ONE);
  /** Proporção igual a UM */
  public static final Ratio UM = new Ratio(BigDecimal.ONE, BigDecimal.ONE);
  /** Proporção igual a DEZ */
  public static final Ratio DEZ = new Ratio(BigDecimal.TEN, BigDecimal.ONE);

  private BigDecimal numerador;
  private BigDecimal denominador;

  Ratio() {
    super();
  }

  Ratio(BigDecimal numerador, BigDecimal denominador) {
    Validate.notNull(numerador, "Numerador inválido");
    Validate.notNull(denominador, "Denominador inválido");
    Validate.isTrue(!denominador.equals(BigDecimal.ZERO), "Denominador não pode ser igual a zero");
    this.numerador = numerador;
    this.denominador = denominador;
  }

  public Ratio negate() {
    return new Ratio(numerador.negate(), denominador);
  }

  public Ratio add(Ratio other) {
    BigDecimal ad = numerador.multiply(other.denominador);
    BigDecimal cd = denominador.multiply(other.numerador);
    BigDecimal bd = denominador.multiply(other.denominador);
    return new Ratio(ad.add(cd), bd);
  }

  public Ratio minus(Ratio other) {
    return add(other.negate());
  }

  public Ratio mutiply(Ratio other) {
    // o produto de a/b x c/d é igual a ac/bd
    BigDecimal ac = numerador.multiply(other.numerador);
    BigDecimal bd = denominador.multiply(other.denominador);
    return new Ratio(ac, bd);
  }

  public Ratio mutiply(int other) {
    return mutiply(Ratio.valueOf(other));
  }

  public Ratio divide(Ratio other) {
    BigDecimal ad = numerador.multiply(other.denominador);
    BigDecimal bc = denominador.multiply(other.numerador);
    return new Ratio(ad, bc);
  }

  public BigDecimal asNumber() {
    if (numerador.signum() == 0) {
      return BigDecimal.ZERO;
    } else if (denominador.compareTo(BigDecimal.ONE) == 0) {
      return numerador;
    } else {
      return numerador.divide(denominador, 15, RoundingMode.HALF_EVEN);
    }
  }

  public boolean isZero() {
    return numerador.compareTo(BigDecimal.ZERO) == 0;
  }

  public boolean isOne() {
    return numerador.compareTo(denominador) == 0;
  }

  public boolean isNegative() {
    return asNumber().signum() == -1;
  }

  public static Ratio valueOf(BigDecimal numerador, BigDecimal denominador) {
    return new Ratio(numerador, denominador);
  }

  public static Ratio valueOf(BigDecimal numerador) {
    return Ratio.valueOf(numerador, BigDecimal.ONE);
  }

  public static Ratio valueOf(String numerador, String denominador) {
    return Ratio.valueOf(new BigDecimal(numerador), new BigDecimal(denominador));
  }

  public static Ratio valueOf(String numerador) {
    return Ratio.valueOf(new BigDecimal(numerador));
  }

  public static Ratio valueOf(Number numerador) {
    return new Ratio(BigDecimal.valueOf(numerador.doubleValue()), BigDecimal.ONE);
  }

  @Override
  public int compareTo(Ratio other) {
    // a/b == c/d => ad == cb
    BigDecimal bd = denominador.multiply(other.numerador);
    BigDecimal ad = numerador.multiply(other.denominador);
    return ad.compareTo(bd);
  }

  public Ratio porcentageRelative(Ratio value) {
    return divide(value).mutiply(Ratio.valueOf(100));
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(numerador).append(denominador).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == null || !(obj instanceof Number)) {
      return false;
    }
    Number other = (Number) obj;
    return compareTo(valueOf(other)) == 0;
  }

  @Override
  public String toString() {
    return TextoUtils.formataQuantidade(this);
  }

  public boolean isMultiply(Ratio other) {
    return asNumber().remainder(other.asNumber()).compareTo(BigDecimal.ZERO) == 0;
  }

  public boolean maiorOuIqualA(Ratio other) {
    return compareTo(other) >= 0;
  }

  public boolean maiorQue(Ratio other) {
    return compareTo(other) > 0;
  }

  public boolean menorQue(Ratio other) {
    return compareTo(other) < 0;
  }

  @Override
  public int intValue() {
    return asNumber().intValue();
  }

  @Override
  public long longValue() {
    return asNumber().longValue();
  }

  @Override
  public float floatValue() {
    return asNumber().floatValue();
  }

  @Override
  public double doubleValue() {
    return asNumber().doubleValue();
  }
}
