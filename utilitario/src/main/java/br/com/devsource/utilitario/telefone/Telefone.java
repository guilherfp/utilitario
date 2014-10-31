package br.com.devsource.utilitario.telefone;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.devsource.utilitario.texto.TextoUtil;
import br.com.devsource.utilitario.valueobject.ValueObjectSupport;

/**
 * Classe representa um número telefônico.
 * @author Guilherme Freitas
 */
@SuppressWarnings("javadoc")
public class Telefone extends ValueObjectSupport<Telefone> implements Comparable<Telefone> {
  private static final long serialVersionUID = 1L;

  private String ddd;
  private String numero;

  /**
   * Construtor para hibernate.
   */
  Telefone() {}

  public Telefone(String ddd, String numero) {
    Validate.notBlank(ddd, "DDD inválido");
    validarNumero(numero);
    this.ddd = ddd;
    this.numero = numero;
  }

  @Override
  public int compareTo(Telefone o) {
    return toString().compareTo(o.toString());
  }

  public String ddd() {
    return ddd;
  }

  public String numero() {
    return numero;
  }

  public void ddd(String ddd) {
    this.ddd = ddd;
  }

  public void numero(String numero) {
    this.numero = numero;
  }

  /**
   * Retorna o representação do número telefônico no formato (XX) XXXX-XXXX.
   */
  @Override
  public String toString() {
    return String.format("(%2s) %4s-%4s", ddd, numero.subSequence(0, 4), numero.substring(4));
  }

  @Override
  public boolean sameValueAs(Telefone other) {
    return (other != null) && new EqualsBuilder().append(ddd, other.ddd).append(numero, other.numero).isEquals();
  }

  @Override
  public Telefone copy() {
    return new Telefone(ddd, numero);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(ddd).append(numero).hashCode();
  }

  private static void validarNumero(String numero) {
    Validate.notBlank(numero, "Número inválido");
    numero = TextoUtil.removerCaracteresEspeciais(numero);
    Validate.isTrue(numero.length() < 8, "Número deve conter no minímo 8 dígitos");
  }
}
