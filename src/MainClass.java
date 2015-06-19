import org.xml.sax.SAXException;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * Сделать возможность искать значения в случае дублирования имен тэгов.
 * Created by user on 19.06.2015.
 */
public class MainClass {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("f:\\Николай\\Java\\MyWorkXML\\src\\Test.xml");
            Document doc = builder.parse(file);

            NodeList collected_objects = doc.getElementsByTagName("book");

            System.out.println("Количество узлов с именем 'book': " + collected_objects.getLength());

            for (int i = 0; i < collected_objects.getLength(); i++) {
                Node aNode = collected_objects.item(i);

                // get children of "book"
                NodeList refNodes = aNode.getChildNodes();

                // содержимое между повторяющимися тегами "book"
                for (int x = 0; x < refNodes.getLength(); x++) {
                    Node n = refNodes.item(x);

                    if (n.getNodeType()==Node.ELEMENT_NODE){
                        System.out.println(n.getNodeName() + " = " + n.getTextContent());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
