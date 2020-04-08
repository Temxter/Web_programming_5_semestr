package Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ItemSAXHandler extends DefaultHandler {

    private String name, weight, type, consist;
    private String lastElement;
    private ArrayList<Item> present;

    public ItemSAXHandler() {
        present = new ArrayList<>();
    }

    public ArrayList<Item> getParsedItems() {
        return present;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElement = qName;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String dataElement = new String(ch, start, length);
        dataElement = dataElement.replace("\n", "").trim();

        if (!dataElement.isEmpty()) {
            if (lastElement.equals("name"))
                name = dataElement;
            else if (lastElement.equals("weight"))
                weight = dataElement;
            else if (lastElement.equals("type"))
                type = dataElement;
            else if (lastElement.equals("consist"))
                consist = dataElement;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (checkItem()) {
            present.add(new Item(name, Double.parseDouble(weight), type, consist));
            name = null;
            weight = null;
            type = null;
            consist = null;
        }
    }

    private boolean checkItem() {
        if (name != null && !name.isEmpty() && weight != null && !weight.isEmpty() &&
                type != null && !type.isEmpty() && consist != null && !consist.isEmpty()){
            try{
                Double.parseDouble(weight);
            } catch (NumberFormatException ex){
                return false;
            }
            return true;
        }
        return false;
    }
}
