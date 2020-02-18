package app;

import constants.NodeNames;
import entities.ConnectionEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionConfig {
    private List<ConnectionEntity> sourceConnectionEntitiesList = new ArrayList<>();
    private List<ConnectionEntity> targetConnectionEntitiesList = new ArrayList<>();

    public ConnectionConfig(String connectionConfigPath) {
        Document configDoc = Utils.getDocument(connectionConfigPath);
        Node sourceRootNode = configDoc.getDocumentElement().getElementsByTagName(NodeNames.SOURCE_NODE).item(0);
        sourceConnectionEntitiesList.addAll(initConnectionEntities(sourceRootNode));
        for (Node tmp = sourceRootNode.getNextSibling(); tmp != null; tmp = tmp.getNextSibling()) {
            if (tmp.getNodeType() == 1) {
                System.out.println(tmp.getNodeName());
                targetConnectionEntitiesList.addAll(initConnectionEntities(tmp));

            }
        }
    }

    public List<ConnectionEntity> initConnectionEntities(Node root) {
        List<ConnectionEntity> result = new ArrayList<>();
        NodeList connectionList = ((Element) root).getElementsByTagName(NodeNames.CONNECTION_NODE);
        for (int i = 0; i < connectionList.getLength(); i++) {
            result.add(new ConnectionEntity(connectionList.item(i)));
        }
        return result;
    }

    public List<ConnectionEntity> getSourceConnectionEntitiesList() {
        return sourceConnectionEntitiesList;
    }

    public List<ConnectionEntity> getTargetConnectionEntitiesList() {
        return targetConnectionEntitiesList;
    }
}
