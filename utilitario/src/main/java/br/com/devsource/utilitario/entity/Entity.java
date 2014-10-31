package br.com.devsource.utilitario.entity;

import java.io.Serializable;

/**
 * An entity, as explained in the DDD book.
 * @param <T> Tipo entidade.
 * @param <ID> Tipo da identidade da entidade.
 */
public interface Entity<T, ID extends Serializable> {

  /**
   * Entities compare by identity, not by attributes.
   * @param other The other entity.
   * @return true if the identities are the same, regardles of other attributes.
   */
  boolean sameIdentityAs(T other);

  /**
   * Identidade da entidade.
   * @return Entidade da entidade.
   * @author Guilherme Freitas em 05/03/2014.
   */
  ID identity();

}
