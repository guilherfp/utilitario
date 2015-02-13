package br.com.devsource.utilitario.texto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import br.com.devsource.utilitario.ratio.Ratio;

@SuppressWarnings("javadoc")
public class TextoUtilTest {

  @Test
  public void testeRemoverDigitos() {
    String string = "ASF10asf";
    string = TextoUtil.removerDigitos(string);
    Assert.assertEquals("Remoção de digitos falhou!", "ASFasf", string);
  }

  @Test
  public void testeRemoverCaracteresEspeciais() {
    String string = "Tes!#@$!@te9387498";
    string = TextoUtil.removerCaracteresEspeciais(string);
    Assert.assertEquals("Remoção de caracteres especiais falhou!", "Teste9387498", string);
  }

  @Test
  public void testeFormatarNomeProprio() {
    String nome = "JoAO DA SiLVA PeREIra";
    Assert.assertEquals("Joao da Silva Pereira", TextoUtil.formatarNomeProprio(nome));
  }

  @Test
  public void testeRemoverEspacos() {
    String string = "tes   te";
    string = TextoUtil.removerEspacos(string);
    Assert.assertEquals("Remoção de exceço de espaços falhou!", "tes te", string);
  }

  @Test
  public void testeToCamelCase() {
    String string = "GetNome";
    string = TextoUtil.toCamelCase(string);
    Assert.assertEquals("ToCamelCase falhou!", "getNome", string);
  }

  @Test
  public void testeGetSimOuNao() {
    Assert.assertEquals("getSimOuNao falou", "Sim", TextoUtil.getSimOuNao(true));
    Assert.assertEquals("getSimOuNao falou", "Não", TextoUtil.getSimOuNao(false));
  }

  @Test
  public void testeObterNomeFormatado() {
    String nome = "Ricardo Pereira de Arruda dos Santos Pinto Araújo Batista";
    String result = TextoUtil.nomeProprio(nome);
    Assert.assertEquals("Ricardo Batista", result);
  }

  @Test
  public void testeMensagemSaudacao() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
    Assert.assertEquals("Bom dia", TextoUtil.getSaudacao(format.parse("10/10/10 08:10")));
    Assert.assertEquals("Boa tarde", TextoUtil.getSaudacao(format.parse("10/10/10 12:10")));
    Assert.assertEquals("Boa noite", TextoUtil.getSaudacao(format.parse("10/10/10 18:10")));
    Assert.assertEquals("Boa noite", TextoUtil.getSaudacao(format.parse("10/10/10 04:10")));
  }

  @Test
  public void testeFormatNumber() {
    int numero1 = 10_000;
    long numero2 = 10_000L;
    Assert.assertEquals("Erro formatação de numero inteiro", "10.000", TextoUtil.formatNumber(numero1));
    Assert.assertEquals("Erro formatação de numero long", "10.000", TextoUtil.formatNumber(numero2));
  }

  @Test
  public void testFormatQuantidade() throws Exception {
    Assert.assertEquals("0", TextoUtil.formataQuantidade(Ratio.valueOf(0)));
    Assert.assertEquals("1", TextoUtil.formataQuantidade(Ratio.valueOf(1)));
    Assert.assertEquals("2,2", TextoUtil.formataQuantidade(Ratio.valueOf(2.2)));
    Assert.assertEquals("2,202", TextoUtil.formataQuantidade(Ratio.valueOf(2.202)));
    Assert.assertEquals("1,002", TextoUtil.formataQuantidade(Ratio.valueOf(1.002)));
    Assert.assertEquals("0,002", TextoUtil.formataQuantidade(Ratio.valueOf(0.002)));
    Assert.assertEquals("0,1", TextoUtil.formataQuantidade(Ratio.valueOf(0.100)));
  }

  @Test
  public void testFormatQuantidade_comLimitacaoDeDigitosDecimais() throws Exception {
    Assert.assertEquals("121", TextoUtil.formataQuantidade(Ratio.valueOf(121.034), 0));
    Assert.assertEquals("41", TextoUtil.formataQuantidade(Ratio.valueOf(41.002234), -1));
    Assert.assertEquals("1,00223", TextoUtil.formataQuantidade(Ratio.valueOf(1.002234), 5));
    Assert.assertEquals("10,12", TextoUtil.formataQuantidade(Ratio.valueOf(10.12002), 2));
    Assert.assertEquals("1,1", TextoUtil.formataQuantidade(Ratio.valueOf(01.100), 1));
    Assert.assertEquals("11,100", TextoUtil.formataQuantidade(Ratio.valueOf(11.1), 3));
  }

  @Test
  public void testToCamelCase() throws Exception {
    Assert.assertEquals("getNome", TextoUtil.toCamelCase("GetNome"));
    Assert.assertEquals("a", TextoUtil.toCamelCase("a"));
  }

}
