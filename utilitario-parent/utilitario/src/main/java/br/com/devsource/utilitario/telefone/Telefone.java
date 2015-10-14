package br.com.devsource.utilitario.telefone;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.devsource.utilitario.TextoUtils;

/**
 * Classe representa um número telefônico.
 * @author Guilherme Freitas
 */
public class Telefone implements Comparable<Telefone> {

  private String ddd;
  private String numero;

  Telefone() {
    super();
  }

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

  public Telefone copy() {
    return new Telefone(ddd, numero);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(ddd).append(numero).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != Telefone.class) {
      return false;
    }
    Telefone other = (Telefone) obj;
    return new EqualsBuilder().append(ddd, other.ddd)
        .append(numero, other.numero).isEquals();
  }

  private static void validarNumero(String numero) {
    Validate.notBlank(numero, "Número inválido");
    String numeroTelefone = TextoUtils.removerCaracteresEspeciais(numero);
    Validate.isTrue(numeroTelefone.length() >= 8, "Número deve conter no minímo 8 dígitos");
  }
}
