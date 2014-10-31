package br.com.devsource.utilitario.valueobject;

/**
 * Base class for value objects.
 * @param <T> Tipo do objeto valor.
 */
@SuppressWarnings("rawtypes")
public abstract class ValueObjectSupport<T extends ValueObject> implements ValueObject<T> {
  private static final long serialVersionUID = -7076414655412541416L;

  @SuppressWarnings("unchecked")
  @Override
  public final boolean equals(final Object o) {
    if (this == o) {
      return true;
    } else if ((o == null) || (getClass() != o.getClass())) {
      return false;
    } else {
      return sameValueAs((T) o);
    }
  }

  @Override
  public abstract int hashCode();
}
