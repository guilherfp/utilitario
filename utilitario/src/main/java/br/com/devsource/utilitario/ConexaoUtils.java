package br.com.devsource.utilitario;

import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária para verificar conexão com a internet.
 * @author Guilherme Freitas
 */
public final class ConexaoUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConexaoUtils.class);

  private ConexaoUtils() {
    super();
  }

  /**
   * Verifica conexão com a internet.
   * @throws ConexaoException Caso não possua conexão com a internet
   */
  public static void possuiInternet() throws ConexaoException {
    try {
      URL url = new URL("http://www.google.com.br");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDefaultUseCaches(false);
      connection.setConnectTimeout(2000);
      connection.setReadTimeout(2000);
      connection.setUseCaches(false);
      connection.connect();
      connection.disconnect();
    } catch (Exception ex) {
      LOGGER.warn(ex.getMessage(), ex);
      throw new ConexaoException("Erro na conexão com a internet", ex);
    }
  }

  /**
   * Verifica se há conexão com a internet.
   * @return <code>true</code> se possuir </br> <code>false</code> se não possui
   */
  public static boolean possuiConexaoInternet() {
    try {
      URL url = new URL("http://www.google.com.br");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDefaultUseCaches(false);
      connection.setConnectTimeout(2000);
      connection.setReadTimeout(2000);
      connection.setUseCaches(false);
      connection.connect();
      connection.disconnect();
      return true;
    } catch (Exception ex) {
      LOGGER.warn(ex.getMessage(), ex);
      return false;
    }
  }
}
