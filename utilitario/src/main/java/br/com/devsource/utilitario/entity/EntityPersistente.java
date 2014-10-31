package br.com.devsource.utilitario.entity;

import java.io.Serializable;

/**
 * Implementação para {@link Entity} para entidade persistente.
 * @see Entity
 * @author Guilherme Freitas em 09/05/2014.
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo de identificador da entidade.
 */
public abstract class EntityPersistente<T extends Entity<T, ID>, ID extends Serializable> extends EntitySupport<T, ID> {

  /** ID de identificação da entidade para persistência. */
  private long id;

  protected EntityPersistente() {}

  long getId() {
    return id;
  }
}
