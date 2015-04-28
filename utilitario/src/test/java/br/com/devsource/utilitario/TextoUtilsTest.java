package br.com.devsource.utilitario;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import br.com.devsource.utilitario.ratio.Ratio;

/**
 * @author Guilherme Pacheco
 */
public class TextoUtilsTest {

  @Test
  public void testeRemoverDigitos() {
    String string = "ASF10asf";
    string = TextoUtils.removerDigitos(string);
    assertEquals("Remoção de digitos falhou!", "ASFasf", string);
  }

  @Test
  public void testeRemoverCaracteresEspeciais() {
    String string = "Tes!#@$!@te9387498";
    string = TextoUtils.removerCaracteresEspeciais(string);
    assertEquals("Remoção de caracteres especiais falhou!", "Teste9387498", string);
  }

  @Test
  public void testeFormatarNomeProprio() {
    String nome = "JoAO DA SiLVA PeREIra";
    assertEquals(null, TextoUtils.formatarNomeProprio(null));
    assertEquals("Joao da Silva Pereira", TextoUtils.formatarNomeProprio(nome));
    assertEquals("", TextoUtils.formatarNomeProprio("   "));
    assertEquals("", TextoUtils.formatarNomeProprio(""));
  }

  @Test
  public void testeRemoverEspacos() {
    String string = "tes   te";
    string = TextoUtils.removerEspacos(string);
    assertEquals("Remoção de exceço de espaços falhou!", "tes te", string);
  }

  @Test
  public void testeToCamelCase() {
    String string = "GetNome";
    string = TextoUtils.toCamelCase(string);
    assertEquals("ToCamelCase falhou!", "getNome", string);
  }

  @Test
  public void testeGetSimOuNao() {
    assertEquals("getSimOuNao falou", "Sim", TextoUtils.getSimOuNao(true));
    assertEquals("getSimOuNao falou", "Não", TextoUtils.getSimOuNao(false));
  }

  @Test
  public void testeObterNomeFormatado() {
    String nome = "Ricardo Pereira de Arruda dos Santos Pinto Araújo Batista";
    String result = TextoUtils.nomeProprio(nome);
    assertEquals("Ricardo Batista", result);
  }

  @Test
  public void testeMensagemSaudacao() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
    assertEquals("Bom dia", TextoUtils.getSaudacao(format.parse("10/10/10 08:10")));
    assertEquals("Boa tarde", TextoUtils.getSaudacao(format.parse("10/10/10 12:10")));
    assertEquals("Boa noite", TextoUtils.getSaudacao(format.parse("10/10/10 18:10")));
    assertEquals("Boa noite", TextoUtils.getSaudacao(format.parse("10/10/10 04:10")));
  }

  @Test
  public void testeFormatNumber() {
    int numero1 = 10_000;
    long numero2 = 10_000L;
    assertEquals("Erro formatação de numero inteiro", "10.000", TextoUtils.formatNumber(numero1));
    assertEquals("Erro formatação de numero long", "10.000", TextoUtils.formatNumber(numero2));
  }

  @Test
  public void testFormatQuantidade() throws Exception {
    assertEquals("0", TextoUtils.formataQuantidade(Ratio.valueOf(0)));
    assertEquals("1", TextoUtils.formataQuantidade(Ratio.valueOf(1)));
    assertEquals("2,2", TextoUtils.formataQuantidade(Ratio.valueOf(2.2)));
    assertEquals("2,202", TextoUtils.formataQuantidade(Ratio.valueOf(2.202)));
    assertEquals("1,002", TextoUtils.formataQuantidade(Ratio.valueOf(1.002)));
    assertEquals("0,002", TextoUtils.formataQuantidade(Ratio.valueOf(0.002)));
    assertEquals("0,1", TextoUtils.formataQuantidade(Ratio.valueOf(0.100)));
  }

  @Test
  public void testFormatQuantidade_comLimitacaoDeDigitosDecimais() throws Exception {
    assertEquals("121", TextoUtils.formataQuantidade(Ratio.valueOf(121.034), 0));
    assertEquals("41", TextoUtils.formataQuantidade(Ratio.valueOf(41.002234), -1));
    assertEquals("1,00223", TextoUtils.formataQuantidade(Ratio.valueOf(1.002234), 5));
    assertEquals("10,12", TextoUtils.formataQuantidade(Ratio.valueOf(10.12002), 2));
    assertEquals("1,1", TextoUtils.formataQuantidade(Ratio.valueOf(01.100), 1));
    assertEquals("11,100", TextoUtils.formataQuantidade(Ratio.valueOf(11.1), 3));
  }

  @Test
  public void testToCamelCase() throws Exception {
    assertEquals("getNome", TextoUtils.toCamelCase("GetNome"));
    assertEquals("a", TextoUtils.toCamelCase("a"));
  }

}
