package br.com.devsource.utilitario.usertypes;

import java.util.Properties;

import org.hibernate.type.EnumType;

/**
 * @author Guilherme Pacheco
 */
public class NamedEnumType extends EnumType {
  private static final long serialVersionUID = 1L;

  @Override
  public void setParameterValues(Properties parameters) {
    if (parameters != null) {
      parameters.setProperty(EnumType.NAMED, "true");
      parameters.setProperty(EnumType.TYPE, "12");
    }
    super.setParameterValues(parameters);
  }

}
