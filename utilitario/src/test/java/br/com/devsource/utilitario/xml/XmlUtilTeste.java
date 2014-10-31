package br.com.devsource.utilitario.xml;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
 */
public class XmlUtilTeste {

  @Test
  public void testToXml() throws Exception {
    Pessoa pessoa = new Pessoa("Pessoa10", 10);
    String xml = "<pessoa><idade>10</idade><nome>Pessoa10</nome></pessoa>";
    assertEquals(xml, XmlUtil.toXml(pessoa));
  }

  @Test
  public void testFromXml() throws Exception {
    Pessoa pessoa1 = new Pessoa("Pessoa10", 10);
    String xml = XmlUtil.toXml(pessoa1);
    Pessoa pessoa2 = XmlUtil.fromXml(xml, Pessoa.class);
    assertEquals(pessoa1.getNome(), pessoa2.getNome());
    assertEquals(pessoa1.getIdade(), pessoa2.getIdade());
  }

}

@XmlRootElement
class Pessoa {
  private String nome;
  private int idade;

  public Pessoa() {}

  public Pessoa(String nome, int idade) {
    this.nome = nome;
    this.idade = idade;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }
}
