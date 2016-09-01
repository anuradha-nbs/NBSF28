/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mmh
 */
public class ReadXML {

    public Map<String,String> getXMLData() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(System.getProperty("user.home")+"/data.xml"));
        document.normalizeDocument();
        Element e = document.getDocumentElement();
        NodeList childNodes = e.getChildNodes();
        Map<String,String> data = new HashMap<>();
        Node item = childNodes.item(1);
        NodeList nl = item.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            data.put(nl.item(i).getNodeName(),nl.item(i).getTextContent().trim());
        }
        data.remove("#text");
        return data;
    }
}
