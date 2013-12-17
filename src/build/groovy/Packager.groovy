import java.text.SimpleDateFormat;

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
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.regex.Pattern;

final Pattern COMMENT_PATTERN = Pattern.compile("<!--~.*?~-->\r?\n?", Pattern.DOTALL | Pattern.MULTILINE);

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

def setTextContentByXPath(xPath, xPathExpression, parent, textContent) {
    Node n = xPath.evaluate(xPathExpression, parent, XPathConstants.NODE);
    n.setTextContent(textContent);
}

def enhanceProjectDescriptor(xPath, descriptorDom, moduleProject, zipFile, project) {
    String product = project.properties.get("compatibility.portal.projectName");
    String productNameShort = project.properties.get("compatibility.portal.projectNameShort");
    String majorVersion = project.properties.get("compatibility.portal.versionMajor");
    String downloadsRootUrl = project.properties.get("portal.quickstarts.downloads.url.prefix").toLowerCase();

    File genPropsFile = new File("src/main/freemarker/"+ moduleProject.getArtifactId(), "generator.properties");
    Properties genProps = new Properties();
    FileInputStream genPropsIn = null;
    try {
        genPropsIn = new FileInputStream(genPropsFile);
        genProps.load(genPropsIn);
    }
    finally {
        genPropsIn.close();
    }
    String genPropsPrefix = productNameShort.equals("GateIn") ? "compatibility.portal.community." : "compatibility.portal.product.";
    String compatibilityMin = genProps.get(genPropsPrefix + "min.versionMm");

    Node projectNode = xPath.evaluate("/projects/project[name/text() = '${moduleProject.artifactId}']", descriptorDom, XPathConstants.NODE)
    if (projectNode == null) {
        log.info("Trying to insert <project> node for '${moduleProject.artifactId}'.");
        projectNode = descriptorDom.createElement("project");
        Node projectsNode = xPath.evaluate("/projects", descriptorDom, XPathConstants.NODE).appendChild(projectNode);
        projectsNode.appendChild(projectNode);
    }

    setTextContent(projectNode, "name", productNameShort.toLowerCase() + majorVersion + "-" + moduleProject.artifactId)
    //setTextContent(projectNode, "category", "${product} "+ project.properties.get("compatibility.portal.versionMajor") +".x Quickstarts")
    setTextContent(projectNode, "category", "Portal Applications")
    setTextContent(projectNode, "included-projects", moduleProject.artifactId)
    setTextContent(projectNode, "shortDescription", moduleProject.name)
    setTextContent(projectNode, "description", productNameShort +" " + majorVersion + ": " + moduleProject.description)
    setTextContent(projectNode, "size", String.valueOf(zipFile.length()))
    setTextContent(projectNode, "url", "${downloadsRootUrl}/"+ zipFile.getName())

    setTextContentByXPath(xPath, "fixes/fix[@type='wtpruntime']/property[@name='description']",
            projectNode, "This project example requires ${product} ${compatibilityMin} or higher with the same major version.");

    String runtimes = genProps.get(genPropsPrefix + "wtpruntimes");

    setTextContentByXPath(
            xPath,
            "fixes/fix[@type='wtpruntime']/property[@name='allowed-types']",
            projectNode,
            runtimes
    );

    //System.out.println("genProps.get(\"" +genPropsPrefix + "isOnJBossCentral\") = "+ genProps.get(genPropsPrefix + "isOnJBossCentral"));
    if ("false".equals(genProps.get(genPropsPrefix + "isOnJBossCentral"))) {
        projectNode.getParentNode().removeChild(projectNode);
//        setTextContentByXPath(
//            xPath,
//            "tags",
//            projectNode,
//            ""
//        );
    }
}

def stripMdFile(path, pattern) {
    Reader r = null
    f = new File(path);
    StringBuilder sb = new StringBuilder(1024)
    try {
        r = new InputStreamReader(new FileInputStream(f), "utf-8")
        int ch = 0
        while ((ch = r.read()) >= 0) {
            sb.append((char) ch);
        }
    }
    finally {
        if (r != null) {
            r.close()
        }
    }
    Writer w = null;
    try {
        w = new OutputStreamWriter(new FileOutputStream(f), "utf-8")
        w.write(pattern.matcher(sb).replaceAll(""))
    }
    finally {
        if (w != null) {
            w.close()
        }
    }
}

ant.delete(dir: "target/assembly")
ant.mkdir(dir: "target/assembly")

ant.delete(dir: "target/assembly-prepare")
ant.mkdir(dir: "target/assembly-prepare")

Document descriptorDom = readDom("src/main/project-examples-xml/project-examples-gatein.xml")
XPath xPath = XPathFactory.newInstance().newXPath();

String productNameShort = project.properties.get("compatibility.portal.projectNameShort");
String rootIncludes = productNameShort.equals("GateIn") ?
    "README.md, LICENSE.txt" :
    "README.md, LICENSE.txt, settings-hosted-repo.xml, settings-zipped-repos.xml";

ant.copy(
    todir: "target/assembly-prepare",
) {
    ant.fileset(
        dir: "${project.basedir}",
        includes: rootIncludes
    )
}
stripMdFile("${project.basedir}/target/assembly-prepare/README.md", COMMENT_PATTERN)


/* Pack them all together for GateIn Downloads */
String product = project.properties.get("compatibility.portal.projectName");
String productVersion = project.properties.get("compatibility.portal.versionMm");

String gateinQuickstartsZipPath = "target/assembly/"+
    project.properties.get("compatibility.portal.projectNameShort").toLowerCase().replace(' ', '-') +
    "-"+
    productVersion +"-quickstarts.zip";

ant.zip (
    destfile: gateinQuickstartsZipPath,
    basedir: "target/assembly-prepare",
    includes: rootIncludes
)

MavenXpp3Reader pomReader = new MavenXpp3Reader()
for (module in project.modules) {

    ant.property(
        name: "ant.regexp.regexpimpl",
        value: "org.apache.tools.ant.util.regexp.JakartaRegexpRegexp"
    )

    ant.copy(
        todir: "target/assembly-prepare",
    ) {
        ant.fileset(
            dir: "${project.basedir}",
            includes: "${module}/**",
            excludes: "${module}/target/**, **/.*, **/.*/**"
        )
    }

    stripMdFile("${project.basedir}/target/assembly-prepare/${module}/README.md", COMMENT_PATTERN)

    String pomPath = "${project.basedir}${File.separator}${module}${File.separator}pom.xml"
    Reader r = new InputStreamReader(new FileInputStream(new File(pomPath)),"utf-8")
    Model moduleProject = pomReader.read(r)
    r.close()

    String zipPath = "target/assembly/${module}.zip"
    ant.zip (
        destfile: zipPath,
        basedir: "target/assembly-prepare",
        includes: "${module}/**",
        excludes: "${module}/target/**, ${module}/*/target/**, **/.*, **/.*/**"
    )

    /* And the same thing once again for GateIn Downloads */
    ant.zip (
        update: true,
        destfile: gateinQuickstartsZipPath,
        basedir: "target/assembly-prepare",
        includes: "${module}/**",
        excludes: "${module}/target/**, ${module}/*/target/**, **/.*, **/.*/**"
    )

    File zipFile = new File(zipPath)

    enhanceProjectDescriptor(xPath, descriptorDom, moduleProject, zipFile, project)

}

descriptorDom.setStrictErrorChecking(false);
java.text.SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZ");
Comment comment = descriptorDom.createComment(" Generated on "+ df.format(new Date()) +" ");
descriptorDom.insertBefore(comment, descriptorDom.getDocumentElement());
descriptorDom.insertBefore(descriptorDom.createTextNode("\n"), descriptorDom.getDocumentElement());

String descriptorPath = "target/assembly/project-examples-" +
        project.properties.get("compatibility.portal.projectNameShort").toLowerCase() +
        project.properties.get("compatibility.portal.versionMm") +
        "-" +
        project.properties.get("org.jboss.ide.target.nameAndVersion") +
        project.properties.get("org.jboss.quickstarts.portal.descriptor.suffix") +
        ".xml"
writeDom(descriptorDom, descriptorPath)

