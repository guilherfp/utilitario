package br.com.devsource.utilitario.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

/**
 * Classe utilitária para realizar conversões XML.
 * @author Guilherme Freitas
 * @param <T> Tipo do objeto a ser trabalhado.
 */
public final class XmlUtil<T> {
  private final Class<T> type;
  private final JAXBContext context;
  private final Marshaller marshaller;
  private final Unmarshaller unmarshaller;

  /**
   * Construtor carrega o tipo do objeto a ser convertido em XML.
   * @param type Classe do objeto a ser convertido.
   * @throws JAXBException {@link JAXBContext}
   */
  private XmlUtil(Class<T> type) throws JAXBException {
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

  public String marshalToFileName(T object, String fileName) throws JAXBException, IOException {
    return marshalToFile(object, new File(fileName));
  }

  public String marshalToFile(T object, File file) throws JAXBException, IOException {
    String xml = marshal(object);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
    return xml;
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(String xmlString, Class<T> type) throws JAXBException {
    return (T) JAXBContext.newInstance(type).createUnmarshaller().unmarshal(new StringReader(xmlString));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static String toXml(Object object) throws JAXBException {
    return new XmlUtil(object.getClass()).marshal(object);
  }

  public static <T> XmlUtil<T> create(Class<T> type) throws JAXBException {
    return new XmlUtil<>(type);
  }
}
