package br.com.devsource.utilitario.usertypes.ratio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import br.com.devsource.utilitario.ratio.Ratio;

public class RatioUserType implements UserType {

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
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == y) {
      return true;
    } else if ((x == null) || (y == null)) {
      return false;
    } else {
      return x.equals(y);
    }
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return x.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
          throws HibernateException, SQLException {
    BigDecimal numerador = rs.getBigDecimal(names[0]);
    return (numerador == null) ? null : Ratio.valueOf(numerador);
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
          throws HibernateException, SQLException {
    if (value == null) {
      st.setNull(index, Types.DECIMAL);
    } else {
      st.setBigDecimal(index, ((Ratio) value).asNumber());
    }
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }
}
