import org.w3c.dom.*;

import javax.script.AbstractScriptEngine;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XmlManager {
    public static String CreateId(String _name, String _lastName) {
        String id = _name.substring(0, 1) + _lastName.substring(0, 1);
        int nameLength = _name.length();
        int lastNameLength = _lastName.length();
        return id+nameLength+lastNameLength;
    }

    public static boolean CheckAsignatura(String _id) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            NodeList nList = doc.getElementsByTagName("asignatura");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("id").equals(_id)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean CheckAlumno(String _name, String _lastName, String _idAsignatura) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            NodeList nList = doc.getElementsByTagName("asignatura");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("id").equals(_idAsignatura)) {
                        NodeList list = eElement.getElementsByTagName("alumno");
                        for (int j = 0; j < list.getLength(); j++) {
                            Node node = list.item(j);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element = (Element) node;
                                if (element.getElementsByTagName("nombre").item(0).getTextContent().equals(_name) && element.getElementsByTagName("apellido").item(0).getTextContent().equals(_lastName)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void CreateAsignatura(String _id) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            Element rootElement = doc.getDocumentElement();
            Element asignatura = doc.createElement("asignatura");
            asignatura.setAttribute("id", _id.toLowerCase());
            rootElement.appendChild(asignatura);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(Main.settings.PATH_DB_FILE));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createXml(String _fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Campusnet");
            doc.appendChild(rootElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(_fileName));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ShowAsignaturas() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            NodeList nList = doc.getElementsByTagName("asignatura");
            System.out.println("----------------------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("==================================");
                    System.out.println("Asignatura: " + eElement.getAttribute("id").toUpperCase());
                    System.out.println("==================================");
                }
            }
            System.out.println("----------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShowStudents() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            NodeList nList = doc.getElementsByTagName("alumno");
            System.out.println("----------------------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("==================================");
                    System.out.println("ID: " + eElement.getAttribute("id"));
                    System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent().toUpperCase());
                    System.out.println("Apellido: " + eElement.getElementsByTagName("apellido").item(0).getTextContent().toUpperCase());
                    System.out.println("==================================");
                }
            }
            System.out.println("----------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddStudent(String _idAsignatura, String _name, String _lastName, int _nota) {
        String id = CreateId(_name, _lastName);
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            NodeList nList = doc.getElementsByTagName("asignatura");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("id").equals(_idAsignatura)) {
                        Element alumno = doc.createElement("alumno");
                        alumno.setAttribute("id", id.toLowerCase());

                        Element nombre = doc.createElement("nombre");
                        nombre.appendChild(doc.createTextNode(_name));
                        alumno.appendChild(nombre);

                        Element apellido = doc.createElement("apellido");
                        apellido.appendChild(doc.createTextNode(_lastName));
                        alumno.appendChild(apellido);

                        Element nota = doc.createElement("nota");
                        nota.appendChild(doc.createTextNode(String.valueOf(_nota)));
                        alumno.appendChild(nota);
                        eElement.appendChild(alumno);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(Main.settings.PATH_DB_FILE));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SearchStudent(String _name) {
        String id = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.settings.PATH_DB_FILE);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//alumno[nombre/text()='" + _name + "']");
            NodeList nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            System.out.println("----------------------------------------");

            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                id = eElement.getAttribute("id");
                System.out.println("==================================");
                System.out.println("ID: " + eElement.getAttribute("id"));
                System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent().toUpperCase());
                System.out.println("Apellido: " + eElement.getElementsByTagName("apellido").item(0).getTextContent().toUpperCase());
                System.out.println("==================================");
            }

            XPathExpression expr2 = xpath.compile("//asignatura/alumno[@id='" + id + "']/parent::asignatura");
            NodeList nList2 = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nList2.getLength(); i++) {
                Node nNode2 = nList2.item(i);
                if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement2 = (Element) nNode2;
                    if (i < 1) {
                        System.out.println("<<-------------------->>");
                    }
                    System.out.println("Asignatura: " + eElement2.getAttribute("id").toUpperCase());
                    System.out.println("Nota: " + eElement2.getElementsByTagName("nota").item(0).getTextContent().toUpperCase());
                    System.out.println("<<-------------------->>");
                }
            }
            System.out.println("----------------------------------------");
        } catch (Exception e) {
            System.out.printf("No se ha encontrado el alumno %s", _name + "\n");
        }
    }
}
