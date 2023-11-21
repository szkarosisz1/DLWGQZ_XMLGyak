package hu.domparse.dlwgqz;

import java.io.File;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMModifyDLWGQZ {
    public static void main(String[] args) {
    	// Metódus meghívása
        modifyElements("XMLDLWGQZ.xml");
    }

    private static void modifyElements(String filePath) {
        try {
        	// Fájl beolvasása
            File xmlFile = new File(filePath);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // Ez a dokumentumépítő példányok létrehozására szolgál
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Ez a dokumentum építésére szolgál
            Document document = dBuilder.parse(xmlFile);
            // A dokumentum normalizálása

            // Csapat attribútumának módosítása
			NodeList csapatList = document.getElementsByTagName("Csapat");

            // Lekérjük azt az elemet a listából amelyiket módosítani szeretnénk, 
            // index alapján történik a módosítás
			Element csapatElement = (Element) csapatList.item(1);
            
            // 1. módosítás
            // A német válogatott vezetőedző nevének módosítása
            csapatElement.getElementsByTagName("Vezetőedző").item(0).setTextContent("Julian Nagelsmann");

            // 2. módosítás
            // Ronaldo és Messi góljai számának módosítása
            NodeList labdarugoList = document.getElementsByTagName("Labdarúgó");
            Element labdarugoElement_CR7 = (Element) labdarugoList.item(0);
            labdarugoElement_CR7.getElementsByTagName("Gól").item(0).setTextContent("14");
            Element labdarugoElement_LM10 = (Element) labdarugoList.item(8);
            labdarugoElement_LM10.getElementsByTagName("Gól").item(0).setTextContent("13");

            // 3. módosítás
            // Education City Stadion, Al Bayt Stadion és a al-Thumama Stadion férőhelyeinek számainak módosítása
            NodeList helyszinList = document.getElementsByTagName("Helyszín");
            Element helyszinElement_Education_City_Stadion = (Element) helyszinList.item(0);
            helyszinElement_Education_City_Stadion.getElementsByTagName("Férőhely").item(0).setTextContent("41500");
            Element helyszinElement_Al_Bayt_Stadion = (Element) helyszinList.item(1);
            helyszinElement_Al_Bayt_Stadion.getElementsByTagName("Férőhely").item(0).setTextContent("55000");
            Element helyszinElement_al_Thumama_Stadion = (Element) helyszinList.item(3);
            helyszinElement_al_Thumama_Stadion.getElementsByTagName("Férőhely").item(0).setTextContent("30000");

            // 4. módosítás
            // 2022.11.26-i mérkőzést dátumának módosítása
            NodeList merkozikList = document.getElementsByTagName("Mérkőzik");
            Element merkozikElement = (Element) merkozikList.item(1);
            merkozikElement.getElementsByTagName("Dátum").item(0).setTextContent("2022.11.28");
            
            // 5. módosítás
            // Csoport jelölésének módosítása
            NodeList csoportList = document.getElementsByTagName("Csoport");
            Element csoportElement = (Element) csoportList.item(0);
            csoportElement.getElementsByTagName("Jelölés").item(0).setTextContent("G");

           
            // Kiírjuk a konzolra a módosított XML fájlt 
            // A konzolra íratáshoz transformerFactoryt alkalmazom
            // Definiálok a factoryból egy új példányt
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // Beállítom a transformert
            Transformer transformer = transformerFactory.newTransformer();

            // Kimeneti kódolás beállítása UTF-8-ra
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // Megadom a forrás fájlt
            DOMSource source = new DOMSource(document);

            // Megnyitom a streamet és konzolra kiíratom sys.out-al a fájlt
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
			
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

