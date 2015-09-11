package br.com.devsource.utilitario;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.management.modelmbean.XMLParseException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

/**
 * Classe utilitária para realizar conversões XML.
 * 
 * @author Guilherme Freitas
 * @param <T> Tipo do objeto a ser trabalhado.
 */
public final class XmlUtils<T> {

  private final Class<T> type;
  private final JAXBContext context;
  private final Marshaller marshaller;
  private final Unmarshaller unmarshaller;

  /**
   * Construtor carrega o tipo do objeto a ser convertido em XML.
   * 
   * @param type Classe do objeto a ser convertido.
   * @throws JAXBException {@link JAXBContext}
   */
  private XmlUtils(Class<T> type) throws JAXBException {
    this.type = type;
    this.context = JAXBContext.newInstance(this.type);
    this.marshaller = context.createMarshaller();
    this.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
    this.unmarshaller = context.createUnmarshaller();
  }

  @SuppressWarnings("unchecked")
  public T unmarshal(String xmlString) throws JAXBException {
    return (T) unmarshaller.unmarshal(new StringReader(xmlString));
  }

  public String marshal(T object) throws JAXBException {
    final StringWriter writer = new StringWriter();
    marshaller.marshal(object, new StreamResult(writer));
    return writer.toString();
  }

  public String marshalToFileName(T object, String fileName) throws XMLParseException {
    try {
      return marshalToFile(object, new File(fileName));
    } catch (Exception ex) {
      throw new XMLParseException(ex, "Não foi possível converter o arquivo");
    }
  }

  public String marshalToFile(T object, File file) throws XMLParseException {
    try {
      String xml = marshal(object);
      Writer writer = new FileWriter(file);
      writer.write(xml);
      writer.close();
      return xml;
    } catch (Exception ex) {
      throw new XMLParseException(ex, "Não foi possível converter o arquivo");
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(String xmlString, Class<T> type) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(type);
    return (T) jaxbContext.createUnmarshaller().unmarshal(new StringReader(xmlString));
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static String toXml(Object object) throws JAXBException {
    return new XmlUtils(object.getClass()).marshal(object);
  }

  public static <T> XmlUtils<T> create(Class<T> type) throws JAXBException {
    return new XmlUtils<>(type);
  }
}
