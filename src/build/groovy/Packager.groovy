import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



def readDom(String path) {
    File f = new File(path)
    Reader r = null
    try {
        r = new InputStreamReader(new FileInputStream(f), "utf-8")
        StreamSource src = new StreamSource(r);
        DOMResult rslt = new DOMResult();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(src, rslt);
        return rslt.getNode();
    }
    finally {
        r.close()
    }
    
}

def writeDom(Document document, String path) {
    File f = new File(path)
    Writer w = null
    try {
        w = new OutputStreamWriter(new FileOutputStream(f), "utf-8")
        DOMSource src = new DOMSource(document);
        StreamResult rslt = new StreamResult(w);
        
        TransformerFactory tf = TransformerFactory.newInstance();
//      if (ident) {
//          tf.setAttribute(XslConst.INDENT_NUMBER, XslConst.INDENT_NUMBER_DEFAULT);
//      }
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.transform(src, rslt);
    
    }
    finally {
        w.close()
    }
    
}

def setTextContent(parent, nodeName, textContent) {
    Node myNode = null
    for (Node n = parent.getFirstChild(); n != null; n = n.getNextSibling()){
        if (nodeName.equals(n.getNodeName())) {
            myNode = n;
            break;
        }
    }
    if (myNode == null) {
        myNode = parent.getOwnerDocument().createElement(nodeName)
        parent.appendChild(myNode);
    }
    myNode.setTextContent(textContent)
}

def enhanceProjectDescriptor(xPath, descriptorDom, moduleProject, zipFile) {
    Node projectNode = xPath.evaluate("/projects/project[name/text() = '${moduleProject.artifactId}']", descriptorDom, XPathConstants.NODE)
    if (projectNode == null) {
        projectNode = descriptorDom.createElement("project")
        Node projectsNode = xPath.evaluate("/projects", descriptorDom, XPathConstants.NODE).appendChild(projectNode)
        projectsNode.appendChild(projectNode)
        setTextContent(projectNode, "name", moduleProject.artifactId)
    }
    
    setTextContent(projectNode, "category", "GateIn Portal Quickstarts")
    setTextContent(projectNode, "included-projects", moduleProject.artifactId)
    setTextContent(projectNode, "shortDescription", moduleProject.name)
    setTextContent(projectNode, "description", moduleProject.description)
    setTextContent(projectNode, "size", String.valueOf(zipFile.length()))
    setTextContent(projectNode, "url", "https://github.com/downloads/ppalaga/gatein-portal-quickstart/"+ zipFile.getName())

}



ant.delete(dir: "target/assembly")
ant.mkdir(dir: "target/assembly")

Document descriptorDom = readDom("src/main/project-examples-xml/project-examples-gatein.xml")
XPath xPath = XPathFactory.newInstance().newXPath();


MavenXpp3Reader pomReader = new MavenXpp3Reader()
for (module in project.modules){
    
    String pomPath = "${project.basedir}${File.separator}${module}${File.separator}pom.xml"
    Reader r = new InputStreamReader(new FileInputStream(new File(pomPath)),"utf-8")
    Model moduleProject = pomReader.read(r)
    r.close()

    String zipPath = "target/assembly/${module}.zip"
    ant.zip (
        destfile: zipPath,
        basedir: "${project.basedir}",
        includes: "${module}/**",
        excludes: "${module}/target/**, .*/**"
    )
    
    File zipFile = new File(zipPath)
    
    enhanceProjectDescriptor(xPath, descriptorDom, moduleProject, zipFile)

}


writeDom(descriptorDom, "target/assembly/project-examples-gatein-"+ project.properties.get("org.jboss.ide.target.version") +".xml")

