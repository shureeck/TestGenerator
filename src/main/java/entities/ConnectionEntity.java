package entities;

import constants.NodeNames;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utils;

import static constants.NodeNames.CONNECTION_STRING_NODE;
import static constants.StringConstants.VALUE;

public class ConnectionEntity {
    private String vendor;
    private String alias;
    private String server;
    private String port;
    private String sid;
    private String userName;
    private String password;
    private boolean isGlue = false;
    private Node connection;

    public ConnectionEntity() {
    }

    public ConnectionEntity(Node connection) {
        setConnection(connection);
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnection(Node connection) {
        this.connection = connection;
        this.alias = ((Element) connection).getAttribute(VALUE);
        NodeList childList = connection.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            switch (childList.item(i).getNodeName()) {
                case NodeNames.CONNECTION_STRING_NODE:
                    this.server = ((Element) childList.item(i)).getAttribute(VALUE);
                    break;
                case NodeNames.PORT_NODE:
                    this.port = ((Element) childList.item(i)).getAttribute(VALUE);
                    break;
                case NodeNames.SID_NODE:
                    this.sid = ((Element) childList.item(i)).getAttribute(VALUE);
                    break;
                case NodeNames.LOGIN_NODE:
                    this.userName = ((Element) childList.item(i)).getAttribute(VALUE);
                    break;
                case NodeNames.PASSWORD_NODE:
                    this.password = ((Element) childList.item(i)).getAttribute(VALUE);
                    break;
                case NodeNames.SOURCE_VENDOR_NODE:
                case NodeNames.TARGET_VENDOR_NODE:
                    this.vendor = ((Element) childList.item(i)).getAttribute(VALUE);
                    break;
            }
        }

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGlue(boolean glue) {
        isGlue = glue;
    }

}
