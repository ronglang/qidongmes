package com.css.common.util.workflow;

import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.input.SAXBuilder;

public class SimpleXMLWorkShop {
    public static Document file2Doc(String filePath) throws IOException,
            JDOMException {
        File file = new File(filePath);
        return file2Doc(file);
    }

    public static Document file2Doc(File file) throws IOException,
            JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document doc = saxBuilder.build(file);
        return doc;

    }

    public static Document str2Doc(String xmlStr) throws IOException,
            JDOMException {
        InputStream inputStream = new ByteArrayInputStream(xmlStr
                .getBytes(encoding));
        SAXBuilder saxBuilder = new SAXBuilder();
        Document doc = saxBuilder.build(inputStream);
        return doc;
    }

    public static String doc2Str(Document doc) {
        if (doc == null) {
            return null;
        }

        Format format = Format.getPrettyFormat();
        format.setExpandEmptyElements(true);
        format.setEncoding(encoding);
        return doc2Str(doc, format);
    }

    public static String doc2Str(Document doc, Format format) {
        if (doc == null) {
            log.error("xml�ĵ�Ϊ��");
            return null;
        }

        XMLOutputter outputter = new XMLOutputter(format);
        String str = outputter.outputString(doc);

        return str;
    }

    public static void outputXML(Document doc, String filePath) throws FileNotFoundException,
            IOException {
        outputXML(doc, new File(filePath));
    }

    public static void outputXML(Document doc, String filePath, Format format) throws FileNotFoundException,
            IOException {
        outputXML(doc, new File(filePath), format);
    }

    /**
     * ��Documentд��ָ��·����xml�ļ�
     *
     * @param doc Document��Ҫ�����Document����
     * @param file File��������ļ�·��
     */
    public static void outputXML(Document doc, File file) throws FileNotFoundException,
            IOException {
        outputXML(doc, new FileOutputStream(file));
    }

    public static void outputXML(Document doc, File file, Format format) throws FileNotFoundException,
            IOException {
        outputXML(doc, new FileOutputStream(file), format);
    }

    /**
     * ��Document �ĵ����� д�뵽Ӳ��xml�ļ�
     * @param doc
     * @param outputStream
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void outputXML(Document doc, OutputStream outputStream) throws FileNotFoundException,
            IOException {
        if (doc == null) {
            log.error("���ʱ,xml�ĵ�Ϊ��,δ�����������");
            return;
        }

        Format format = Format.getPrettyFormat();
        format.setEncoding(encoding);
        format.setExpandEmptyElements(true);

        XMLOutputter outputter = new XMLOutputter(format);
        outputter.output(doc, outputStream);
        outputStream.close();
    }
    
    /**
     * ��Document �ĵ����� д�뵽Ӳ��xml�ļ�,�����ؽ��
     * @param doc
     * @param outputStream
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean outputXMLWithResult(Document doc, OutputStream outputStream){
        boolean saveOkFlag=false;
    	try {
			if (doc == null) {
			    log.error("���ʱ,xml�ĵ�Ϊ��,δ�����������");
			}else{
				Format format = Format.getPrettyFormat();
				format.setEncoding(encoding);
				format.setExpandEmptyElements(true);

				XMLOutputter outputter = new XMLOutputter(format);
				outputter.output(doc, outputStream);
				outputStream.close();
				saveOkFlag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveOkFlag;
    }

    public static void outputXML(Document doc,
            OutputStream outputStream,
            Format format) throws FileNotFoundException, IOException {
        if (doc == null) {
            log.error("���ʱ,xml�ĵ�Ϊ��,δ�����������");
            return;
        }

        XMLOutputter outputter = new XMLOutputter(format);
        outputter.output(doc, outputStream);
    }

    /**
     * xml�ļ����xsl��ʽ���ļ����html�ļ�
     * @param xmlFile File
     * @param htmlFile File
     * @param xslFile File
     */
    public static void xmlToHtml(File xmlFile, File htmlFile, File xslFile) throws TransformerConfigurationException,
            FileNotFoundException,
            TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        Transformer transformer = tFactory.newTransformer(new StreamSource(
                new FileInputStream(xslFile)));

        transformer.transform(new StreamSource(new FileInputStream(xmlFile)),
                new StreamResult(new FileOutputStream(htmlFile)));
    }

    /**
     * Document���xsl��ʽ���ļ����html�ļ�
     * @param doc Document
     * @param htmlFile File
     * @param xslFile File
     */
    public static void xmlToHtml(Document doc, File htmlFile, File xslFile) throws TransformerConfigurationException,
            FileNotFoundException,
            TransformerException {
        if (doc == null) {
            log.error("���ʱ,xml�ĵ�Ϊ��,δ�����������");
            return;
        }

        String xmlStr = doc2Str(doc);
        InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes());

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(
                new FileInputStream(xslFile)));

        transformer.transform(new StreamSource(inputStream), new StreamResult(
                new FileOutputStream(htmlFile)));
    }

    /**
     * �ı�����ʽ
     * @param charactorSet
     */
    public static void setEndocing(String charactorSet) {
        encoding = charactorSet;
    }

    /**
     * @return Returns the encoding.
     */
    public static String getEncoding() {
        return encoding;
    }

    //��־��¼��
    private static Log log = LogFactory.getLog(SimpleXMLWorkShop.class);

    //Ĭ�ϱ��뷽ʽ
    private static String encoding = "UTF-8";

}