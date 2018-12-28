package com.robobank.reader;

import com.robobank.model.CustomerStatement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class created to read the XML file and map them into Customer statement POJO
 */

public class XMLStatementReader {
    private Logger logger = Logger.getLogger(XMLStatementReader.class.getName());

    public List<CustomerStatement> getCustomerStatements() {
        List<CustomerStatement> customerStatements = new ArrayList<>();
        try {

            File fXmlFile = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("com/robobank/resources/records.xml")).toURI()).toFile();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("record");


            for (int index = 0; index < nodeList.getLength(); index++) {
                Node node = nodeList.item(index);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    customerStatements.add(new CustomerStatement(Integer.parseInt(element.getAttribute("reference")),
                            element.getElementsByTagName("accountNumber").item(0).getTextContent(),
                            element.getElementsByTagName("description").item(0).getTextContent(),
                            new BigDecimal(element.getElementsByTagName("startBalance").item(0).getTextContent()),
                            new BigDecimal(element.getElementsByTagName("mutation").item(0).getTextContent()),
                            new BigDecimal(element.getElementsByTagName("endBalance").item(0).getTextContent())));
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, " Exception occurred while processing XML file " + e.getMessage());
        }
        return customerStatements;
    }
}
