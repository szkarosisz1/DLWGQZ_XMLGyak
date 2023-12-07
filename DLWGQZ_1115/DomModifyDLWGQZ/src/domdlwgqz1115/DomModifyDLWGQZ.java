package domdlwgqz1115;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomModifyDLWGQZ {
    public static void main(String[] args) {
         try {
            File inputFile = new File("./orarendDLWGQZ.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            addInstructor(document);
            outputDocument(document, transformer, "orarendModifyDLWGQZ.xml");

            modifyCourseType(document);
            outputDocument(document, transformer, "orarendModifyDLWGQZ.xml");

            insertNewClass(document);
            outputDocument(document, transformer, "orarendModifyDLWGQZ.xml");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void addInstructor(Document document) {
        NodeList oraNodeList = document.getElementsByTagName("ora");

        for (int i = 0; i < oraNodeList.getLength(); i++) {
            Node oraNode = oraNodeList.item(i);

            if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                Element oraElement = (Element) oraNode;
                NodeList oraadoNodeList = oraElement.getElementsByTagName("oraado");

                if (oraadoNodeList.getLength() == 0) {
                    Element newInstructor = document.createElement("oraado");
                    newInstructor.appendChild(document.createTextNode("Nagy Norbert"));
                    oraElement.appendChild(newInstructor);
                }
            }
        }
    }

    private static void modifyCourseType(Document document) {
        NodeList oraNodeList = document.getElementsByTagName("ora");

        for (int i = 0; i < oraNodeList.getLength(); i++) {
            Node oraNode = oraNodeList.item(i);

            if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                Element oraElement = (Element) oraNode;
                String courseType = oraElement.getAttribute("tipus");

                if ("gyakorlat".equals(courseType)) {
                    oraElement.setAttribute("tipus", "előadás");
                }
            }
        }
    }

    private static void insertNewClass(Document document) {
        Element root = document.getDocumentElement();

        Element newClass = document.createElement("ora");
        newClass.setAttribute("id", "13");
        newClass.setAttribute("tipus", "előadás");

        Element targy = document.createElement("targy");
        targy.appendChild(document.createTextNode("Adatbázisrendszerek I."));
        newClass.appendChild(targy);

        Element idopont = document.createElement("idopont");

        Element nap = document.createElement("nap");
        nap.appendChild(document.createTextNode("Péntek"));
        idopont.appendChild(nap);

        Element tol = document.createElement("tol");
        tol.appendChild(document.createTextNode("14:00"));
        idopont.appendChild(tol);

        Element ig = document.createElement("ig");
        ig.appendChild(document.createTextNode("16:00"));
        idopont.appendChild(ig);

        newClass.appendChild(idopont);

        Element helyszin = document.createElement("helyszin");
        helyszin.appendChild(document.createTextNode("XXXII. A1 magasfsz"));
        newClass.appendChild(helyszin);

        Element oktato = document.createElement("oktato");
        oktato.appendChild(document.createTextNode("Dr. Kovács László"));
        newClass.appendChild(oktato);

        Element szak = document.createElement("szak");
        szak.appendChild(document.createTextNode("Mérnökinformatikus"));
        newClass.appendChild(szak);

        root.appendChild(newClass);
    }
    
    private static void outputDocument(Document doc, Transformer transformer, String outputPath) throws TransformerException, IOException {
        DOMSource source = new DOMSource(doc);
        StreamResult consoleResult = new StreamResult(System.out);
        StreamResult fileResult = new StreamResult(new File(outputPath));

        transformer.transform(source, consoleResult);
        transformer.transform(source, fileResult);
    }
}
