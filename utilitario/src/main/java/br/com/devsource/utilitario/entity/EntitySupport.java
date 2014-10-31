package br.com.devsource.utilitario.entity;

import java.io.Serializable;

/**
 * Classe base para as entidades.
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo da entidade da entidade.
 */
public abstract class EntitySupport<T extends Entity<T, ID>, ID extends Serializable> implements Entity<T, ID> {

  @Override
  public boolean sameIdentityAs(final T other) {
    return (other != null) && (identity().equals(other.identity()));
  }

  @Override
  public int hashCode() {
    return identity().hashCode();
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    } else if ((o == null) || (getClass() != o.getClass())) {
      return false;
    } else {
      return sameIdentityAs((T) o);
    }
  }
}
