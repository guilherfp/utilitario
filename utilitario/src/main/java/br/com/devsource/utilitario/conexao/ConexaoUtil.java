package br.com.devsource.utilitario.conexao;

import java.net.HttpURLConnection;
import java.net.URL;

import br.com.devsource.utilitario.excessao.UtilException;

/**
 * Classe utilitária para verificar conexão com a internet.
 * @author Guilherme Freitas
 */
public final class ConexaoUtil {

  private ConexaoUtil() {
    super();
  }

  /**
   * Verifica conexão com a internet.
   * @throws UtilException se não possuir conexão com a internet
   */
  public static void possuiInternet() throws UtilException {
    try {
      URL url = new URL("http://www.google.com.br");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDefaultUseCaches(false);
      connection.setConnectTimeout(2000);
      connection.setReadTimeout(2000);
      connection.setUseCaches(false);
      connection.connect();
      connection.disconnect();
    } catch (Exception e) {
      UtilException exception = new UtilException("Erro na conexão com a internet");
      exception.setDescricao("Verifique a conexão do servidor!");
      throw exception;
    }
  }

  /**
   * Verifica se há conexão com a internet.
   * @return <code>true</code> se possuir </br> <code>false</code> se não possui
   */
  public static boolean possuiConexaoInternet() {
    boolean conectado = false;
    try {
      URL url = new URL("http://www.google.com.br");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDefaultUseCaches(false);
      connection.setConnectTimeout(2000);
      connection.setReadTimeout(2000);
      connection.setUseCaches(false);
      connection.connect();
      conectado = true;
      connection.disconnect();
    } catch (Exception e) {
      conectado = false;
    }
    return conectado;
  }
}
