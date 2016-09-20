package br.com.devsource.utilitario.money;

import java.util.Currency;

/**
 * Exceção de operação com tipos de moedas diferentes.
 * @author Guilherme Pacheco
 */
public class CurrencyMissmatchException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private static final String MENSAGEM = "Tipos de moedas não são compatíveis, %s (atual) e %s";

  /**
   * Construtor define mensagem.
   * @param esperado Tipo de moeda esperado.
   * @param excepted Tipo esperado.
   * @author Guilherme Freitas em 30/04/2014.
   */
  public CurrencyMissmatchException(Currency esperado, Currency excepted) {
    super(String.format(MENSAGEM, esperado.getSymbol(), excepted.getSymbol()));
  }
}
