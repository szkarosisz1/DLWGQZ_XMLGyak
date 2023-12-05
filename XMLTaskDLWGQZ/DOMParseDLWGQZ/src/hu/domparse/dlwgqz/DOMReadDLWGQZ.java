package hu.domparse.dlwgqz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadDLWGQZ {
	// A fő metódus, ami meghívja a readXMLDocument metódust a megadott XML fájllal
     public static void main(String[] args) {
        readXMLDocument("XMLReadDlwgqz.xml");
    }

     private static void readXMLDocument(String filePath) {
        try {
            File newXMLFile = new File(filePath);
            StreamResult newXmlStream = new StreamResult(newXMLFile);
            
            // XML dokumentum beolvasása, amely a beolvasott adatokat
            // 'Document' objektumba menti     
            Document document = parseXML("./XMLDLWGQZ.xml");
            
            writeDocument(document, newXmlStream);

            // Kiírja a strukturált XML dokumentumot a konzolra
            System.out.println(formatXML(document));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Metódus az XML dokumentum beolvasására
    public static Document parseXML(String fileName) throws  ParserConfigurationException, SAXException, IOException{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // XML dokumentum beolvasása és DOM dokumentummá alakítása
            Document document = builder.parse(new File(fileName));
            // Üres szövegek eltávolítása
            cleanDocument(document.getDocumentElement()); 
            return document;    
    }

    // Metódus az üres szövegek eltávolítására
    private static void cleanDocument(Node root) {
        NodeList nodeList = root.getChildNodes();
        // Az üres szövegek eltávolítandó listája
        List<Node> toDeleteEmptyTextList = new ArrayList<>();
        // Az összes gyermekelem ellenőrzése
        for (int i = 0; i < nodeList.getLength(); i++) {
        	// Ellenőrzi, hogy a jelenlegi elem TEXT_NODE típusú és üres-e
            if (nodeList.item(i).getNodeType() == Node.TEXT_NODE && nodeList.item(i).getTextContent().strip().isEmpty()) {
            	//Ha üres listához adja
                toDeleteEmptyTextList.add(nodeList.item(i));
            } else {
                cleanDocument(nodeList.item(i));
            }
        }
        // Az üres szövegeket tartalmazó elemek eltávolítása a DOM dokumentumból
        for (Node node : toDeleteEmptyTextList) {
            root.removeChild(node);
        }
    }

    // Az új XML dokumentum kiírása fájlba 
    public static void writeDocument(Document document, StreamResult output){
        try {         
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // DOM forrás létrehozása a dokumentumból
            DOMSource source = new DOMSource(document);
            transformer.transform(source, output);
        } catch (Exception e) {
            e.printStackTrace();
        }  	
    }

    public static String formatXML(Document document) {
        return "<?xml version=\"" + document.getXmlVersion() + "\" encoding=\"" + document.getXmlEncoding() + "\" ?>\n" +
               formatElement(document.getDocumentElement(), 0);
    }

    public static String formatElement(Node node, int indent) {
    	// Ellenőrzés, hogy a Node objektum egy ELEMENT_NODE típusú-e
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return "";
        }
        // StringBuilder létrehozása a formázott XML szöveg gyűjtésére
        StringBuilder output = new StringBuilder();
        // Nyitó címke (tag) hozzáadása a StringBuilder-hez
        output.append(getIndent(indent)).append("<").append(((Element) node).getTagName());
        // Ha a Node objektumnak vannak attribútumai, azok hozzáadása a StringBuilder-hez
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node attribute = node.getAttributes().item(i);
                output.append(" ").append(attribute.getNodeName()).append("=\"").append(attribute.getNodeValue()).append("\"");
            }
        }
        // A Node objektum gyerekeinek lekérése
        NodeList children = node.getChildNodes();
        // Ha csak egy szöveges tartalom van, azt egy sorban megjelenítjük
        if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
            output.append(">").append(children.item(0).getTextContent().trim()).append("</").append(((Element) node).getTagName()).append(">\n");
        } else {
        	// Nyitó tag befejezése és újsor kezdete
            output.append(">\n");
            // Gyerekelemek formázása 
            for (int i = 0; i < children.getLength(); i++) {
                output.append(formatElement(children.item(i), indent + 1));
            }
            // Záró tag hozzáadása a StringBuilder-hez és újsor kezdete
            output.append(getIndent(indent)).append("</").append(((Element) node).getTagName()).append(">\n");
        }
        // A StringBuilder tartalmának visszaadása formázott XML szövegként
        return output.toString();
    }

    // Metódus az üres szóközök generálására
    private static String getIndent(int indent) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            indentation.append("    ");
        }
        return indentation.toString();
    }
}