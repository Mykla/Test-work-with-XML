import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализовать обработку тэгов типа <tag />
 * т.н. пустого тега, который не содержит контента и не содержит параметров,
 * т.е. будет true для узла getLength() == 0 или для контента getNodeValue().trim().isEmpty()
 * Created by user on 19.06.2015.
 */
public class RemoveEmptyTag {
    public static void main(String[] args) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            // организация чтения xml файла
            File file = new File("f:\\Николай\\Java\\MyWorkXML\\src\\My.xml");
            Document doc = builder.parse(file);

            removeEmptyNodes(doc);

            // сохранить изменный xml файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            // организация вывода в файл
            StreamResult result = new StreamResult(new File("f:\\Николай\\Java\\MyWorkXML\\src\\MyNew.xml"));

            // организация вывода результата в консоль
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void removeEmptyNodes(Node node) {

        NodeList list = node.getChildNodes();
        List<Node> nodesToRecursivelyCall = new LinkedList();

        for (int i = 0; i < list.getLength(); i++) {
            nodesToRecursivelyCall.add(list.item(i));
        }

        for (Node tempNode : nodesToRecursivelyCall) {
            removeEmptyNodes(tempNode);
        }

        boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE
                && node.getChildNodes().getLength() == 0;
        boolean emptyText = node.getNodeType() == Node.TEXT_NODE
                && node.getNodeValue().trim().isEmpty();

        if (emptyElement || emptyText) {
            if (!node.hasAttributes()) {
                node.getParentNode().removeChild(node);
            }
        }
    }
}
