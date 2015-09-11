package br.com.devsource.utilitario;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária para verificar conexão com a internet.
 * 
 * @author Guilherme Freitas
 */
public final class ConexaoUtils {

  private static final String URL_GOOGLE = "http://www.google.com.br";
  private static final Logger LOGGER = LoggerFactory.getLogger(ConexaoUtils.class);

  private ConexaoUtils() {
    super();
  }

  /**
   * Verifica se há conexão com a internet.
   * 
   * @return <code>true</code> se possuir, <code>false</code> se não possui
   */
  public static boolean isPossuiConexaoInternet() {
    try {
      URL url = new URL(URL_GOOGLE);
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

  /**
   * Verifica conexão com a internet.
   * 
   * @throws ConexaoException Caso não possua conexão com a internet
   */
  public static void isPossuiInternet() throws ConexaoException {
    try {
      Validate.isTrue(isPossuiConexaoInternet());
    } catch (Exception ex) {
      LOGGER.warn(ex.getMessage(), ex);
      throw new ConexaoException("Erro na conexão com a internet", ex);
    }
  }

}
