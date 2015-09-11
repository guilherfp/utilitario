package br.com.devsource.utilitario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classes fabrica conexões JDBC.
 * 
 * @author Guilherme Freitas
 */
public final class JDBCUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUtils.class);

  private JDBCUtils() {
    super();
  }

  /**
   * Obtem uma conexão JDBC com a base de dados da aplicação.
   * 
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @return Conexão com o banco de dados.
   * @throws ConexaoException Caso data source JNDI não seja encontrado.
   * @throws ConexaoException caso ocorra algum problema de conexão com a base de dados.
   */
  public static Connection getConnection(String jndiResource) throws ConexaoException {
    try {
      Context ctx = new InitialContext();
      return ((DataSource) ctx.lookup(jndiResource)).getConnection();
    } catch (Exception ex) {
      throw new ConexaoException(ex);
    }
  }

  /**
   * Fecha conexões JDBC.
   * 
   * @param connection conexão com banco.
   * @param pstm {@link PreparedStatement} utilizado na conexão.
   * @param rs {@link ResultSet} utilizado na conexão.
   */
  public static void close(Connection connection, PreparedStatement pstm, ResultSet rs) {
    try {
      if (rs != null && !rs.isClosed()) {
        rs.close();
      }
      if (pstm != null && !pstm.isClosed()) {
        pstm.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
  }

  /**
   * Cria a base de dados da aplicação.
   * 
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @param database nome do banco de dados.
   * @return <code>true</code> caso a base seja criada com sucesso. <code>false</code> caso ocorra
   *         algum problema.
   */
  public static boolean criarBaseDeDados(String jndiResource, String database) {
    String sql = "create database if not exists ?;";
    Connection conn = null;
    PreparedStatement pstm = null;
    try {
      conn = getConnection(jndiResource);
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, database);
      return pstm.execute();
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      return false;
    }
  }

  /**
   * Método verifica se o schema informado já existe.
   * 
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @param schema Schema a ser verificado.
   * @return <code>true</code> caso o esquema já exista e <code>false</code> caso contrário.
   * @throws SQLException Erro de execução do SQL.
   * @throws NamingException Recurso JNDI não localizado.
   */
  public static boolean isSchemaExist(String jndiResource, String schema) throws ConexaoException {
    boolean result = false;
    String format = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s'";
    String sql = String.format(format, schema);
    PreparedStatement pstmt;
    try {
      pstmt = getConnection(jndiResource).prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        result = true;
      }
      close(null, pstmt, rs);
    } catch (Exception ex) {
      throw new ConexaoException(ex);
    }
    return result;
  }

  /**
   * Cria um schema.
   * 
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @param schema Schema a ser criado.
   * @throws ConexaoException Erro de execução do SQL.
   * @throws ConexaoException Recurso JNDI não localizado.
   */
  public static void createSchema(String jndiResource, String schema) throws ConexaoException {
    try {
      String sql = String.format("create database %s", schema);
      PreparedStatement pstmt = getConnection(jndiResource).prepareStatement(sql);
      pstmt.execute();
      pstmt.close();
    } catch (SQLException ex) {
      throw new ConexaoException(ex);
    }
  }

  /**
   * Executa um comando SQL.
   * 
   * @param jndiResource Nome do Recurso JNDI utilizado para obter a conexão.
   * @@param jndiResource Recurso JNDI a ser utilizado.
   * @param sql SQL a ser executada.
   * @throws ConexaoException Caso tenha erro de SQL.
   * @throws ConexaoException Caso não possa obter um JNDI.
   */
  public static void executeSQL(String jndiResource, String sql) throws ConexaoException {
    try {
      getConnection(jndiResource).prepareStatement(sql).execute();
    } catch (SQLException ex) {
      throw new ConexaoException(ex);
    }
  }
}
