package br.com.devsource.utilitario.entity;

public abstract class Persistente {

  /** ID de identificação da entidade para persistência. */
  private long id;

  protected Persistente() {}

  long getId() {
    return id;
  }
}
