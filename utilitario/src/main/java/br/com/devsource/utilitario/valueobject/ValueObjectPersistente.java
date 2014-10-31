package br.com.devsource.utilitario.valueobject;

public abstract class ValueObjectPersistente<T extends ValueObject<T>> extends ValueObjectSupport<T> {
  private static final long serialVersionUID = 1L;

  /** ID de identificação da entidade para persistência. */
  private long id;

  protected ValueObjectPersistente() {}

  long getId() {
    return id;
  }
}
