package br.com.devsource.utilitario.valueobject;

import java.io.Serializable;

/**
 * A value object, as described in the DDD book.
 * @param <T> Tipo do objeto valor.
 */
public interface ValueObject<T> extends Serializable {

  /**
   * Value objects compare by the values of their attributes, they don't have an identity.
   * @param other The other value object.
   * @return <code>true</code> if the given value object's and this value object's attributes are the same.
   */
  boolean sameValueAs(T other);

  /**
   * Retorna uma copia o objeto valor.
   * @return Uma copia do objeto com o mesmo valor.
   * @author Guilherme Freitas em 05/03/2014.
   */
  T copy();

}
