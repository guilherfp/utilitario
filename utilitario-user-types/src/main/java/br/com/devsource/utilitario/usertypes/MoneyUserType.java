package br.com.devsource.utilitario.usertypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Currency;
import java.util.Locale;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.usertype.UserType;

import br.com.devsource.utilitario.money.Money;

/**
 * @author Guilherme Pacheco
 */
public class MoneyUserType extends AbstractUserType implements UserType {
  private static final long serialVersionUID = 1L;

  private static final int[] SQL_TYPES = { Types.NUMERIC };

  @Override
  public int[] sqlTypes() {
    return SQL_TYPES;
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
      throws SQLException {
    BigDecimal amount = rs.getBigDecimal(names[0]);
    return (amount == null) ? null : Money.valueOf(amount, Currency
      .getInstance(Locale.getDefault()));
  }

  @Override
  public void
      nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
          throws SQLException {
    if (value == null) {
      BigDecimalType.INSTANCE.set(st, null, index, session);
    } else {
      BigDecimalType.INSTANCE.set(st, ((Money) value).getAmount(), index, session);
    }
  }

  @Override
  public Class<?> returnedClass() {
    return Money.class;
  }

}
