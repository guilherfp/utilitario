package br.com.devsource.utilitario.usertypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import br.com.devsource.utilitario.ratio.Ratio;

/**
 * @author Guilherme Pacheco
 */
public class RatioUserType extends AbstractUserType implements UserType {
  private static final long serialVersionUID = 1L;

  private static final int[] SQL_TYPES = { Types.NUMERIC };

  @Override
  public int[] sqlTypes() {
    return SQL_TYPES;
  }

  @Override
  public Class<?> returnedClass() {
    return Ratio.class;
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
      throws SQLException {
    BigDecimal numerador = rs.getBigDecimal(names[0]);
    return (numerador == null) ? null : Ratio.valueOf(numerador);
  }

  @Override
  public void
      nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
          throws SQLException {
    if (value == null) {
      st.setNull(index, Types.DECIMAL);
    } else {
      st.setBigDecimal(index, ((Ratio) value).asNumber());
    }
  }

}
