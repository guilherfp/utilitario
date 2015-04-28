package br.com.devsource.utilitario;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe utilitária com métodos para reflexão de classes Java.
 * @author Guilherme Freitas
 */
public class ReflexaoUtils {

  private ReflexaoUtils() {
    super();
  }

  /**
   * Obtem todos os atributos de uma classe.
   * @param classe que deseja obter os atributos.
   * @return lista com todos os atributos.
   */
  public static List<Field> getFields(Class<?> classe) {
    List<Field> fields = new LinkedList<>();
    fields.addAll(Arrays.asList(classe.getDeclaredFields()));
    Class<?> superClasse = classe.getSuperclass();
    if (superClasse != null) {
      fields.addAll(getFields(superClasse));
    }
    return fields;
  }

  /**
   * Obtem todos os métodos de uma classe.
   * @param classe que dejesa obter os métodos.
   * @return Lista com todos os métodos.
   */
  public static List<Method> getMethods(Class<?> classe) {
    List<Method> methods = new LinkedList<>();
    methods.addAll(Arrays.asList(classe.getDeclaredMethods()));
    Class<?> superClasse = classe.getSuperclass();
    if (superClasse != null) {
      methods.addAll(getMethods(superClasse));
    }
    return methods;
  }

  /**
   * Método obtem classes passadas como argumentos para classes genéricas.
   * @param classe genérica que deseja obter os argumentos.
   * @param indice posição da declaração do argumento na classe.
   * @return classe desejada.
   */
  @SuppressWarnings("unchecked")
  public static <T> Class<?> getGenericArgumentClass(Class<T> classe, int indice) {
    return (Class<T>) ((ParameterizedType) classe.getGenericSuperclass()).getActualTypeArguments()[indice];
  }
}
