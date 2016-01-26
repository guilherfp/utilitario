package br.com.devsource.utilitario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;

import br.com.devsource.utilitario.ratio.Ratio;

/**
 * Classe utilitária para tratamento de texto.
 * @author Guilherme Freitas
 */
public final class TextoUtils {

  private static final List<String> PREPOSICOES;
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0");

  static {
    PREPOSICOES = Arrays.asList(new String[] {"de", "da", "do", "e"});
  }

  private TextoUtils() {
    super();
  }

  /**
   * Remove dígitos da string.
   * @param string a ser formatada.
   * @return string formatada.
   */
  public static String removerDigitos(String string) {
    return string.replaceAll("\\d", "");
  }

  /**
   * Remove caracteres especiais.
   * @param string {@link String} a ser tratada.
   * @return {@link String} tratada.
   */
  public static String removerCaracteresEspeciais(String string) {
    Validate.notNull("String inválida");
    String temp = string;
    temp = temp.replaceAll("[ÂÀÁÄÃ]", "A");
    temp = temp.replaceAll("[âãàáä]", "a");
    temp = temp.replaceAll("[ÊÈÉË]", "E");
    temp = temp.replaceAll("[êèéë]", "e");
    temp = temp.replaceAll("[ÎÍÌÏ]", "I");
    temp = temp.replaceAll("[îíìï]", "i");
    temp = temp.replaceAll("[ÔÕÒÓÖ]", "O");
    temp = temp.replaceAll("[ôõòóö]", "o");
    temp = temp.replaceAll("[ÛÙÚÜ]", "U");
    temp = temp.replaceAll("[ûúùü]", "u");
    temp = temp.replaceAll("Ç", "C");
    temp = temp.replaceAll("ç", "c");
    temp = temp.replaceAll("[ýÿ]", "y");
    temp = temp.replaceAll("Ý", "Y");
    temp = temp.replaceAll("ñ", "n");
    temp = temp.replaceAll("Ñ", "N");
    temp = temp.replaceAll("[-+=*&;%$#@?!~´`ªº°¹²³£¢¬]", "");
    temp = temp.replaceAll("['\"]", "");
    temp = temp.replaceAll("[<>()\\{\\}]", "");
    temp = temp.replaceAll("['\\\\.,()|/]", "");
    temp = temp.replaceAll("[\\^]", "");
    return temp;
  }

  /**
   * Trata string para nome próprio com primeiro caracteres com letra maiúscula.
   * <p>
   * Exemplo:
   *
   * <pre>
   * FUlano peixoto DA Silva -> Fulano Peixoto da Silva
   * </pre>
   *
   * @param nome a ser tratada.
   * @return string formatada.
   */
  public static String formatarNomeProprio(final String nome) {
    if (nome == null) {
      return null;
    }
    String nomeSemEspacos = removerEspacos(nome.toLowerCase());
    char[] array = removerEspacos(nomeSemEspacos).toCharArray();
    for (int i = 0; i < array.length; i++) {
      if (i == 0) {
        array[i] = String.valueOf(array[i]).toUpperCase().charAt(0);
      } else if (array[i] == ' ') {
        boolean isPreposicao = false;
        if (array.length - i > 2) {
          isPreposicao = PREPOSICOES.contains(String.format("%s%s", array[i + 1], array[i + 2]));
        }
        if (!isPreposicao) {
          array[i + 1] = String.valueOf(array[i + 1]).toUpperCase().charAt(0);
        }
      }
    }
    return String.valueOf(array);
  }

  /**
   * Método remove excessos de espaços em branco.
   * @param string a ser tratada.
   * @return string formatada.
   */
  public static String removerEspacos(String string) {
    Validate.notNull(string, "String inválida");
    return string.trim().replaceAll("\\s+", " ");
  }

  /**
   * Retorna o texto sim ou não de acordo com um boolean.
   * <p>
   * true = Sim.
   * <p>
   * false = Não.
   * @param valor <code>true</code> = Sim, <code>false</code> = Não.
   * @return {@link String} correspondente ao valor.
   */
  public static String getSimOuNao(boolean valor) {
    return valor ? "Sim" : "Não";
  }

  /**
   * Obtem a saudação de acordo com o horário.
   * @param data na qual deseja obtem a saudação. Se a <b>data</b> for nula será utilizada a data
   *          atual do sistema.
   * @return String de saudação<br/>
   *         Ex.:<br/>
   *         Data com hora igual à 11:00hs = "Bom dia"<br/>
   *         Data com hora igual à 14:00hs = "Boa tarde"
   */
  public static String getSaudacao(Date data) {
    Validate.notNull(data, "Informe a data");
    int hora = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).getHour();
    if (hora < 5) {
      return "Boa noite";
    } else if (hora < 12) {
      return "Bom dia";
    } else if (hora < 18) {
      return "Boa tarde";
    } else {
      return "Boa noite";
    }
  }

  /**
   * Converte uma String para o Formato camelCase.
   * @param string valor a ser convertido.
   * @return valor convertido.
   */
  public static String toCamelCase(String string) {
    Validate.notNull(string, "string não pode ser nula.");
    if (string.length() < 2) {
      return string.toLowerCase();
    }
    return string.substring(0, 1).toLowerCase().concat(string.substring(1));
  }

  /**
   * Realizar a formatação do número.<br/>
   * Ex.: 10000 -> 10.000
   * @param number Número a ser formatado.
   * @return número formatado.
   */
  public static String formatNumber(long number) {
    return DECIMAL_FORMAT.format(number);
  }

  /**
   * Realizar a formatação do número.<br/>
   * Ex.: 10000 -> 10.000
   * @param number Número a ser formatado.
   * @return número formatado.
   */
  public static String formatNumber(int number) {
    return DECIMAL_FORMAT.format(number);
  }

  /**
   * Obtem nome próprio. </br>
   * Ex.:<br>
   * João Pereira da Silva Batista -> João Batista
   * @param nome que deseja ser tratado.
   * @return nome tratado.
   */
  public static String nomeProprio(String nome) {
    Validate.notNull(nome, "Nome inválido");
    StringBuilder builder = new StringBuilder();
    if (!nome.isEmpty()) {
      String[] nomes = nome.split(" ");
      if (nomes.length > 0) {
        builder.append(nomes[0]);
        if (nomes.length > 1) {
          builder.append(" ".concat(nomes[nomes.length - 1]));
        }
      }
    }
    return builder.toString();
  }

  /**
   * Formata valor {@link Ratio} para melhor representação.
   * @param ratio Valor a ser formatado.
   * @param maxDecimalDigits Quantidade máxima de casas decimais desejada.
   * @return Valor formatado.
   */
  public static String formataQuantidade(Ratio ratio, int maxDecimalDigits) {
    int digits = 0;
    if (maxDecimalDigits > 0) {
      digits = maxDecimalDigits;
    }
    BigDecimal value = ratio.asNumber();
    int decimal = (int) value.doubleValue();
    double fractional = value.doubleValue() - decimal;
    if (Double.compare(fractional, 0.0) == 0) {
      return String.valueOf(value.intValue());
    } else {
      String mask = "%.".concat(String.valueOf(digits));
      return String.format(mask.concat("f"), value.doubleValue());
    }
  }

  /**
   * Formata valor {@link Ratio} para melhor representação.
   * @param ratio Valor a ser formatado.
   * @return Valor formatado.
   */
  public static String formataQuantidade(Ratio ratio) {
    return formataQuantidade(ratio, ratio.asNumber().scale());
  }
}
