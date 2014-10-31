package br.com.devsource.utilitario.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe base para as entidades.
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo da chave primária da entidade.
 * @author Guilherme Freitas em 09/03/2014.
 */

public abstract class BaseModel<T extends Entity<T, ID>, ID extends Serializable> extends EntitySupport<T, ID> {

  protected boolean ativa = true;
  protected Date dataCadastro;
  protected Date dataAtualizacao;
  protected Date dataDesativacao;

  protected BaseModel() {}

  private long id;

  long getId() {
    return id;
  }

  /**
   * Atributo valida se entidade está ativa ou não.
   * @return <code>true</code> caso entidade esteja ativa ou <code>false</code> caso contrário.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public boolean isAtiva() {
    return ativa;
  }

  /**
   * Define o valor do atributo ativa.
   * @param ativa Tipo permitido do atributo é {@link boolean}.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public void ativo(boolean ativa) {
    this.ativa = ativa;
  }

  /**
   * @return Data em que a entidade foi cadastrada.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public Date dataCadastro() {
    return dataCadastro;
  }

  /**
   * Define o valor do atributo dataCadastro.
   * @param dataCadastro Tipo permitido do atributo é {@link Date}.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public void dataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  /**
   * @return Data da última atualização da entidade.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public Date dataAtualizacao() {
    return dataAtualizacao;
  }

  /**
   * Define o valor do atributo dataAtualizacao.
   * @param dataAtualizacao Tipo permitido do atributo é {@link Date}.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public void dataAtualizacao(Date dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  /**
   * @return Data de desativação da entidade.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public Date dataDesativacao() {
    return dataDesativacao;
  }

  /**
   * Define o valor do atributo dataDesativacao.
   * @param dataDesativacao Tipo permitido do atributo é {@link Date}.
   * @author Guilherme Freitas em 09/03/2014.
   */
  public void dataDesativacao(Date dataDesativacao) {
    this.dataDesativacao = dataDesativacao;
  }
}
