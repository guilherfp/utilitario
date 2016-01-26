package br.com.devsource.utilitario.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import org.apache.commons.lang3.builder.EqualsBuilder;

import br.com.devsource.utilitario.ratio.Ratio;

/**
 * Classe para tratamento de valores monetários.
 * @author Guilherme Pacheco
 */
public final class Money implements Comparable<Money>, Serializable {
  private static final long serialVersionUID = 1L;

  private static RoundingMode ARREDONDAMENTO = RoundingMode.DOWN;
  private RoundingMode arredondamento;

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,##0.00");
  /** Moeda padrão brasileiro (Real). */
  public static final Currency REAL = Currency.getInstance(new Locale("pt", "BR"));
  /** Valor monetário zero. */
  public static final Money ZERO = valueOf(0);
  /** Valor monetário de um centavo. */
  public static final Money UM_CENTAVO = valueOf(0.01);
  /** Valor monetário igual a um. */
  public static final Money UM = valueOf(1);
  /** Valor monetário igual a dez. */
  public static final Money DEZ = valueOf(10);

  private long amount;
  private Currency currency;

  private Money(BigDecimal amount, Currency currency, RoundingMode arredondamento) {
    this.amount = toLongRepresentation(amount, currency);
    this.arredondamento = arredondamento;
    this.currency = currency;
  }

  private Money(long amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static void setArredondamentoGeral(RoundingMode arredondamento) {
    org.apache.commons.lang3.Validate.notNull(arredondamento, "Arredondamento não é válido");
    ARREDONDAMENTO = arredondamento;
  }

  public static void setArredondamentoPadrao(RoundingMode arredondamento) {
    ARREDONDAMENTO = arredondamento;
  }

  private RoundingMode getArredondamento() {
    return arredondamento != null ? arredondamento : ARREDONDAMENTO;
  }

  public void arredondamento(RoundingMode arredondamento) {
    this.arredondamento = arredondamento;
  }

  public int scale() {
    return currency.getDefaultFractionDigits();
  }

  /**
   * @return Tipo da moeda.
   */
  public Currency getCurrency() {
    return currency;
  }

  /**
   * @return Montante da moeda.
   */
  public BigDecimal getAmount() {
    return fromLongRepresentation(amount, currency);
  }

  /**
   * Compara se o valor é maior do que o informado.
   * @param other Valor a ser comparado.
   * @return <code>true</code> caso o valor seja maior que o informado e <code>false</code> caso
   *         contrário.
   */
  public boolean maiorQue(Money other) {
    return compareTo(other) > 0;
  }

  /**
   * Verifica se o valor é maior ou igual ao valor informado.
   * @param other Valor a ser comparado.
   * @return <code>true</code> caso valor seja maior que o informado, e <code>false</code> caso
   *         contrário.
   * @author Guilherme Freitas em 28/05/2014.
   */
  public boolean maiorOuIgualA(Money other) {
    return compareTo(other) >= 0;
  }

  /**
   * @return Retorna os centavos do valor.
   */
  public int getCentavos() {
    int cdp = currency.getDefaultFractionDigits();
    BigDecimal newAmount = getAmount().setScale(cdp, getArredondamento());
    return newAmount.remainder(BigDecimal.ONE).movePointRight(cdp).intValueExact();
  }

  /**
   * @return Retorna a parte inteira do valor.
   */
  public int getParInteira() {
    return getAmount().setScale(0, getArredondamento()).intValueExact();
  }

  /**
   * @return Retorna valor positivo.
   */
  public Money abs() {
    return isNegative() ? negate() : this;
  }

  /**
   * @return Retorna <code>true</code> caso valor seja negativo e <code>false</code> caso contrário.
   */
  public boolean isNegative() {
    return amount < 0;
  }

  /**
   * Compara se o valor é menor do que o informado.
   * @param other Valor a ser comparado.
   * @return <code>true</code> caso o valor seja menor que o informado e <code>false</code> caso
   *         contrário.
   */
  public boolean isMenorQue(Money other) {
    return compareTo(other) < 0;
  }

  /**
   * Nega o valor atual do montante alterando de positivo para negativo e vice-versa.
   * @return Valor atual negado.
   */
  public Money negate() {
    return new Money(amount * -1, currency);
  }

  @Override
  public int compareTo(Money o) {
    return BigInteger.valueOf(amount).compareTo(BigInteger.valueOf(o.amount));
  }

  /**
   * Realiza a soma do valor atual com o informado.
   * @param other Valor a ser somado.
   * @return Soma dos dois valores.
   */
  public Money add(Money other) {
    checkCurrency(other);
    return new Money(amount + other.amount, currency);
  }

  /**
   * Realiza a subtração do valor atual com o informado.
   * @param other Valor a ser subtraído.
   * @return Subtração dos dois valores.
   */
  public Money minus(Money other) {
    checkCurrency(other);
    return new Money(amount - other.amount, currency);
  }

  /**
   * Verifica se a moeda informada por parâmetro é a mesma da moeda atual.
   * @param other Moeda a ser comparada.
   */
  private void checkCurrency(Money other) {
    if (!other.currency.equals(currency)) {
      throw new CurrencyMissmatchException(currency, other.currency);
    }
  }

  /**
   * Múltiplica moeda atual por um número.
   * @param factor Fator de múltiplicação.
   * @return Resultado da múltiplicação da moeda por factor.
   */
  public Money multiply(Number factor) {
    BigDecimal bigFactor;
    if (factor instanceof BigDecimal) {
      bigFactor = (BigDecimal) factor;
    } else {
      bigFactor = new BigDecimal(factor.toString().replace(',', '.'));
    }
    long result = bigFactor.multiply(new BigDecimal(amount)).longValue();
    return new Money(result, currency);
  }

  /**
   * Múltiplica moeda atual por um número.
   * @param factor Fator de múltiplicação.
   * @return Resultado da múltiplicação da moeda por factor.
   */
  public Money multiply(double factor) {
    return multiply(BigDecimal.valueOf(factor));
  }

  /**
   * Realiza a distribuição monetária da moeda.
   * @param factor Fator de distruição (Porções que será distribuída).
   * @return Array de money com a quantidade informada pelo factor.
   * @throws IllegalArgumentException Caso factor não seja maior do que zero.
   */
  public Money[] distribute(int factor) {
    org.apache.commons.lang3.Validate.isTrue(factor > 0,
        "Fator de distribuição deve ser maior do que zero");
    BigInteger bigValue = BigInteger.valueOf(amount);
    BigInteger[] result = bigValue.divideAndRemainder(BigInteger.valueOf(factor));
    Money[] distribution = new Money[factor];
    Arrays.fill(distribution, new Money(result[0].longValue(), currency));
    distribution[0] = distribution[0].add(new Money(result[1].longValue(), currency));
    return distribution;
  }

  /**
   * Realiza a dedução (Desconto) sobre o valor.<br>
   * <b>Obs.:</b> Não é necessário realizar a divisão por 100, ele recebe o valor já em porcentagem.
   * <p>
   * Ex.: Valor = 100,00<br>
   * <b>deduction</b>= 2<br>
   * Equivale a: 100 - 2%(2,00) = 98,00
   * @param deduction Valor do desconto em porcentagem.
   * @return Retorna novo valor com o desconto.
   */
  public Money deductionPercentage(double deduction) {
    return minus(multiply(deduction / 100));
  }

  /**
   * Realiza a dedução (Desconto) sobre o valor.<br>
   * <b>Obs.:</b> Não é necessário realizar a divisão por 100, ele recebe o valor já em porcentagem.
   * <p>
   * Ex.: Valor = 100,00<br>
   * <b>deduction</b>= 2<br>
   * Equivale a: 100 - 2%(2,00) = 98,00
   * @param deduction Valor do desconto em porcentagem.
   * @return Retorna novo valor com o desconto.
   */
  public Money deductionPercentage(Number deduction) {
    return deductionPercentage(deduction.doubleValue());
  }

  /**
   * Realiza um incrmento (Acréscimo) sobre o valor.<br>
   * <b>Obs.:</b> Não é necessário realizar a divisão por 100, ele recebe o valor já em porcentagem.
   * <p/>
   * Ex.: Valor = 100,00<br>
   * <b>deduction</b>= 2<br>
   * Equivale a: 100 + 2%(2,00) = 102,00
   * @param increase Valor do acréscimo.
   * @return Retorna novo valor com o acréscimo.
   */
  public Money increasePercentage(Number increase) {
    return increasePercentage(increase.doubleValue());
  }

  /**
   * Realiza um incrmento (Acréscimo) sobre o valor.<br>
   * <b>Obs.:</b> Não é necessário realizar a divisão por 100, ele recebe o valor já em porcentagem.
   * <p>
   * Ex.: Valor = 100,00<br>
   * <b>deduction</b>= 2<br>
   * Equivale a: 100 + 2%(2,00) = 102,00
   * @param increase Valor do acréscimo.
   * @return Retorna novo valor com o acréscimo.
   */
  public Money increasePercentage(double increase) {
    return add(multiply(increase / 100));
  }

  /**
   * Verifica se o valor é igual a zero.
   * @return <code>true</code> caso valor seja iegual a zero e <code>false</code> caso contrário.
   * @author Guilherme Freitas em 28/05/2014.
   */
  public boolean isZero() {
    return amount == 0;
  }

  /**
   * Verifica o o valor é maior ou igual a um centavo.
   * @return Retorna <code>true</code> caso seja maior ou igual e <code>false</code> caso contrário.
   */
  public boolean maiorQueUmCentavo() {
    return amount >= 1;
  }

  public Money getPorcentage(double value) {
    return this.multiply(value / 100);
  }

  public Money getPorcentage(Ratio value) {
    Ratio divisor = value.divide(Ratio.valueOf(100));
    BigDecimal multiply = getAmount().multiply(divisor.asNumber(), MathContext.DECIMAL32);
    return new Money(multiply, currency, arredondamento);
  }

  public Ratio porcentageRelative(Money value) {
    return porcentageRelative(value.getAmount().doubleValue());
  }

  public Ratio porcentageRelative(double value) {
    BigDecimal cem = BigDecimal.valueOf(100);
    return Ratio.valueOf(BigDecimal.valueOf(value).multiply(cem)
        .divide(getAmount(), 10, getArredondamento()));
  }

  private static long toLongRepresentation(BigDecimal value, Currency currency) {
    return value.movePointRight(currency.getDefaultFractionDigits()).longValue();
  }

  private static BigDecimal fromLongRepresentation(long amount, Currency currency) {
    BigDecimal value = new BigDecimal(amount);
    return value.movePointLeft(currency.getDefaultFractionDigits());
  }

  /**
   * Cria uma nova moeda de acordo com tipo de moeda informado..
   * @param amount Valor da moeda.
   * @param currency Tipo do moeda.
   * @return Uma nova moeda corrente de acordo com o parâmetro informado.
   */
  public static Money valueOf(String amount, Currency currency) {
    return valueOf(new BigDecimal(amount), currency);
  }

  /**
   * Cria uma nova moeda de acordo com tipo de moeda informado..
   * @param amount Valor da moeda.
   * @param currency Tipo da moeda.
   * @return Uma nova moeda corrente de acordo com o parâmetro informado.
   */
  public static Money valueOf(double amount, Currency currency) {
    return valueOf(BigDecimal.valueOf(amount), currency);
  }

  /**
   * Cria uma nova moeda de acordo com tipo de moeda informado..
   * @param amount Valor da moeda.
   * @param currency Tipo da moeda.
   * @return Uma nova moeda corrente de acordo com o parâmetro informado.
   */
  public static Money valueOf(BigDecimal amount, Currency currency) {
    return new Money(toLongRepresentation(amount, currency), currency);
  }

  /**
   * Cria uma nova moeda corrente no padrão da máquina.
   * @param amount Valor da moeda.
   * @return Uma nova moeda corrente no padrão da máquina.
   */
  public static Money valueOf(double amount) {
    return valueOf(amount, Currency.getInstance(Locale.getDefault()));
  }

  /**
   * Cria uma nova moeda corrente no padrão da máquina.
   * @param amount Valor da moeda.
   * @return Uma nova moeda corrente no padrão da máquina.
   */
  public static Money valueOf(BigDecimal amount) {
    return valueOf(amount, Currency.getInstance(Locale.getDefault()));
  }

  /**
   * Cria uma nova moeda corrente no padrão brasileiro.
   * @param amount Valor da moeda.
   * @return Uma nova moeda de Real.
   */
  public static Money real(BigDecimal amount) {
    return valueOf(amount, REAL);
  }

  /**
   * Cria uma nova moeda corrente no padrão brasileiro.
   * @param amount Valor da moeda.
   * @return Uma nova moeda de Real.
   */
  public static Money real(double amount) {
    return valueOf(amount, REAL);
  }

  /**
   * @return Representação da moeda no seguinte formado: R$ 999.999,99.
   */
  @Override
  public String toString() {
    return String.format("%s %s", currency.getSymbol(), toFormat());
  }

  /**
   * @return Retorna valor sem a representação de moeda. Ex.: 99.999,99.
   */
  public String toFormat() {
    return DECIMAL_FORMAT.format(getAmount().doubleValue());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (amount ^ amount >>> 32);
    result = prime * result + (currency == null ? 0 : currency.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Money other = (Money) obj;
    return new EqualsBuilder().append(amount, other.amount)
        .append(currency, other.currency).isEquals();
  }

  public Money copy() {
    return new Money(getAmount(), getCurrency(), arredondamento);
  }

  public boolean isMultiple(Money other) {
    return getAmount().remainder(other.getAmount()).compareTo(BigDecimal.ZERO) == 0;
  }
}
