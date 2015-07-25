package br.com.devsource.utilitario.usertypes;

import java.io.Serializable;

import org.hibernate.usertype.UserType;

/**
 * @author Guilherme Pacheco
 */
public abstract class AbstractUserType implements UserType, Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  public Serializable disassemble(Object value) {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) {
    return original;
  }

  @Override
  public int hashCode(Object x) {
    return x.hashCode();
  }

  @Override
  public Object deepCopy(Object value) {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public boolean equals(Object x, Object y) {
    if (x == y) {
      return true;
    } else if ((x == null) || (y == null)) {
      return false;
    } else {
      return x.equals(y);
    }
  }

}
