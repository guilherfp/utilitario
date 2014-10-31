package br.com.devsource.utilitario.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classes fabrica conexões JDBC.
 * @author Guilherme Freitas
 */
public final class JDBCUtil {

  private JDBCUtil() {}

  /**
   * Obtem uma conexão JDBC com a base de dados da aplicação.
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @return Conexão com o banco de dados.
   * @throws NamingException caso data source JNDI não seja encontrado.
   * @throws SQLException caso ocorra algum problema de conexão com a base de dados.
   */
  public static Connection getConnection(String jndiResource) throws NamingException, SQLException {
    Context ctx = new InitialContext();
    return ((DataSource) ctx.lookup(jndiResource)).getConnection();
  }

  /**
   * Fecha conexões JDBC.
   * @param connection conexão com banco.
   * @param pstm {@link PreparedStatement} utilizado na conexão.
   * @param rs {@link ResultSet} utilizado na conexão.
   */
  public static void close(Connection connection, PreparedStatement pstm, ResultSet rs) {
    try {
      if ((rs != null) && !rs.isClosed()) {
        rs.close();
      }
      if ((pstm != null) && !pstm.isClosed()) {
        pstm.close();
      }
      if ((connection != null) && !connection.isClosed()) {
        connection.close();
      }
    } catch (Exception ex) {
    }
  }

  /**
   * Cria a base de dados da aplicação.
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @param database nome do banco de dados.
   * @return <code>true</code> caso a base seja criada com sucesso. <code>false</code> caso ocorra algum problema.
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
      return false;
    }
  }

  /**
   * Método verifica se o schema informado já existe.
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @param schema Schema a ser verificado.
   * @return <code>true</code> caso o esquema já exista e <code>false</code> caso contrário.
   * @throws SQLException Erro de execução do SQL.
   * @throws NamingException Recurso JNDI não localizado.
   */
  public static boolean isSchemaExist(String jndiResource, String schema) throws SQLException, NamingException {
    boolean result = false;
    String sql = String.format("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s'", schema);
    PreparedStatement pstmt = getConnection(jndiResource).prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
      result = true;
    }
    close(null, pstmt, rs);
    return result;
  }

  /**
   * Cria um schema.
   * @param jndiResource Recurso JNDI a ser utilizado.
   * @param schema Schema a ser criado.
   * @throws SQLException Erro de execução do SQL.
   * @throws NamingException Recurso JNDI não localizado.
   */
  public static void createSchema(String jndiResource, String schema) throws SQLException, NamingException {
    String sql = String.format("create database %s", schema);
    PreparedStatement pstmt = getConnection(jndiResource).prepareStatement(sql);
    pstmt.execute();
    pstmt.close();
  }

  /**
   * Executa um comando SQL.
   * @param jndiResource Nome do Recurso JNDI utilizado para obter a conexão.
   * @@param jndiResource Recurso JNDI a ser utilizado.
   * @param sql SQL a ser executada.
   * @throws SQLException Caso tenha erro de SQL.
   * @throws NamingException Caso não possa obter um JNDI.
   */
  public static void executeSQL(String jndiResource, String sql) throws SQLException, NamingException {
    getConnection(jndiResource).prepareStatement(sql).execute();
  }
}
