package View;

import Model.ItemSAXHandler;
import Model.Present;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        if(!validateXML()){
            System.exit(-1);
        }



        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        ItemSAXHandler saxHandler = new ItemSAXHandler();

        saxParser.parse(new File("present_data.xml"), saxHandler);

        Present present = new Present(saxHandler.getParsedItems());

//        present.addItem("Alenka", 100, "Chocolate", "milk, cocao, sugar");
//        present.addItem("Sorvanec", 20,"Candy", "milk, cocao, sugar, flour");
//        present.addItem("Truffles", 300, "Candy", "milk, cocao, sugar, flour");

        String info = present.getItemsInfo();
        System.out.println(info);
//
//        System.out.println("Weight of present: " + present.getWeight());
//
//        System.out.println("Sorting present by weight.");
//        present.sortBy(null); //default by weight
//        info = present.getItemsInfo();
//        System.out.println(info);
//
//        System.out.println("Find in present items with [flour].");
//        info = present.findItem("flour");
//        System.out.println(info);
//
//        System.out.println("Delete in present item [Sorvanec].");
//        present.removeItem("Sorvanec");
//        info = present.getItemsInfo();
//        System.out.println(info);
    }

    static boolean validateXML() throws IOException, SAXException, ParserConfigurationException {
        // parse an XML document into a DOM tree
        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = parser.parse(new File("present_data.xml"));

        // create a SchemaFactory capable of understanding WXS schemas
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // load a WXS schema, represented by a Schema instance
        Source schemaFile = new StreamSource(new File("present_data.xsd"));
        Schema schema = factory.newSchema(schemaFile);

        // create a Validator instance, which can be used to validate an instance document
        Validator validator = schema.newValidator();

        // validate the DOM tree
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException e) {
            System.err.println("Instance document is invalid: " + e.getMessage());
            return false;
        }
        return true;
    }
}
