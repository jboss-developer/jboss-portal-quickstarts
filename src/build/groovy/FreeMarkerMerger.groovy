import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import org.sonatype.aether.util.artifact.DefaultArtifact;
import org.sonatype.aether.repository.LocalArtifactRequest;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.repository.LocalArtifactResult;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
/**
 * Makes saving hierarchical keys possible. Both org.example and org.example.key1 can be used as keys.
 * */
private static class ModelNode extends HashMap<String, Object> {
    private MavenXpp3Reader pomReader = null;

    private String value = null;

    public ModelNode() {
    }

    public ModelNode(String value) {
        this.value = value;
    }

    @Override
    public Object put(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    public void putModel(Object value) {
        super.put("project", value);
    }

    public void putNode(String key, ModelNode node) {
        super.put(key, node);
    }

    public MavenXpp3Reader getPomReader() {
        if (this.pomReader == null) {
            this.pomReader = new MavenXpp3Reader();
        }
        return this.pomReader;
    }

    public Model loadPom(File file) {
        //System.out.println("loading: "+ file);
        Reader r = null;
        try {
            r = new InputStreamReader(new FileInputStream(file), "utf-8");
            return getPomReader().read(r);
        }
        finally {
            if (r != null) {
                r.close();
            }
        }
    }

    /**
     * Put the properties from the top pom.xml into the FreeMarker model and do in the FreeMarker way - i.e. hierarchically
     */
    public void putProperties(Properties properties) {
        for (Map.Entry<Object, Object> prop : properties.entrySet()) {
            StringTokenizer st = new StringTokenizer((String) prop.getKey(), ".");
            ModelNode parent = this;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (st.hasMoreTokens()) {
                    ModelNode nextParent = (ModelNode) parent.get(token);
                    if (nextParent == null) {
                        nextParent = new ModelNode();
                        parent.putNode(token, nextParent);
                    }
                    parent = nextParent;
                } else {
                    parent.putString(token, (String) prop.getValue());
                }
                // log.info("   handling prop segment '${token}'.")
            }
        }
    }

    public void putProperties(session, String groupId, String artifactId, String version) {
        //    for (Repository repo : model.getRepositories()) {
        //        System.out.println("repo: "+ repo.getUrl());
        //    }
        //System.out.println("putProperties( session, "+ groupId +", "+ artifactId+", "+ version+")");
        def repoSession = session.getRepositorySession();
        def localRepoManager = repoSession.getLocalRepositoryManager();
        def artifact = new DefaultArtifact(groupId, artifactId, "pom", version);
        def request = new LocalArtifactRequest(artifact, new ArrayList<RemoteRepository>(), null);
        def LocalArtifactResult result = localRepoManager.find(repoSession, request);
        def pom = loadPom(result.getFile());
        putProperties(pom.getProperties());
    }

    public void putString(String key, String value) {
        Object node = get(key);
        if (node instanceof ModelNode) {
            ((ModelNode) node).value = value;
        } else {
            super.put(key, new ModelNode(value));
        }
    }

    public void putMap(String key, Map<String, String> map) {
        super.put(key, map);
    }

    public String toString() {
        return value;
    }
}

private static class ModelNodeRepresentation extends SimpleHash implements freemarker.template.TemplateScalarModel,
        TemplateHashModelEx {
    private ModelNode node;

    public ModelNodeRepresentation(ModelNode o) {
        super(o);
        this.node = o;
    }

    public TemplateModel get(String key) throws TemplateModelException {
        Object o = node.get(key);
        if (o instanceof ModelNode) {
            return new ModelNodeRepresentation((ModelNode) o);
        } else {
            return super.get(key);
        }
    }

    public String getAsString() {
        return node.toString();
    }
}

private static class ModelNodeWrapper extends DefaultObjectWrapper {
    public TemplateModel wrap(Object o) throws TemplateModelException {
        if (o instanceof ModelNode) {
            return new ModelNodeRepresentation((ModelNode) o);
        } else {
            return super.wrap(o);
        }
    }
}


/** Do the usual FreeMarker stuff */
public static void mergeFile(Configuration cfg, String templateRoot, String templatePath, ModelNode model,
        String outputDir) throws IOException, TemplateException {
    String outputPath = outputDir + "/" + templatePath.replaceAll("\\.ftl\$", "");
    // log.info("Merging '${templateRoot}/${templatePath}' -> '${outputPath}'.");
    Template temp = cfg.getTemplate(templatePath);
    Writer out = new OutputStreamWriter(new FileOutputStream(new File(outputPath)), "utf-8");
    temp.process(model, out);
    out.close();
}

public static void mergeDirRecursive(Configuration cfg, String templateRoot, String dir, ModelNode model,
        String outputDir) throws IOException, TemplateException {
    File absDir = new File(templateRoot + File.separatorChar + dir);
    for (File f : absDir.listFiles()) {
        if (f.getName().charAt(0) == '.') {
            /* ignore */
        }
        else if (f.isDirectory()) {
            mergeDirRecursive(cfg, templateRoot, f.getPath().substring(templateRoot.length() + 1), model, outputDir);
        }
        else {
            mergeFile(cfg, templateRoot, f.getPath().substring(templateRoot.length() + 1), model, outputDir);
        }
    }
}


String topDir = "${project.basedir}";
ant.mkdir(dir: "${topDir}/target");
ant.delete(file: "${topDir}/target/external-links.html");


/* The following is needed only if run standalone */
//Reader rr = new InputStreamReader(new FileInputStream(new File(topDir + "/pom.xml")), "utf-8");
//Model project = pomReader.read(rr);
//rr.close();

/* FreeMarker configuration */
Configuration cfg = new Configuration();
String templateRoot = topDir + "/src/main/freemarker";
cfg.setDirectoryForTemplateLoading(new File(templateRoot));
cfg.setObjectWrapper(new ModelNodeWrapper());

/* Create a data model to merge with FreeMarker templates */
ModelNode model = new ModelNode();
model.putModel(project);
model.putString("derivedFileNotice", "Do not edit this derived file! See ${project.artifactId}/src/main/freemarker/");

model.putProperties(session, "org.jboss.bom", "gatein-3.6-bom", project.getProperties().get("org.jboss.bom.gatein-bom.version"));
Properties topProperties = project.getProperties();
model.putProperties(topProperties);

/* links and versions */
Writer extLinksHtmlBuilder = new OutputStreamWriter(new FileOutputStream(new File("target/external-links.html")), "UTF-8");
extLinksHtmlBuilder.append("<html><body><ul>\n");
/* Sort alphabetically using TreeSet */
Map<String, String> linksAndVersions = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
for (String key : topProperties.keySet()) {
    if (key instanceof String && (key.endsWith(".url") || key.startsWith("compatibility."))) {
        linksAndVersions.put(key.replace('.', '_'), topProperties.get(key));
        if (key.endsWith(".url")) {
            extLinksHtmlBuilder.append("<li><a href=\"").append(topProperties.get(key)).append("\">").append(topProperties.get(key)).append("</a></li>\n");
        }
    }
}
extLinksHtmlBuilder.append("</ul></body></html>");
extLinksHtmlBuilder.close();
model.putMap("linksAndVersions", linksAndVersions);


/* Merge the top level README */
mergeFile(cfg, templateRoot, "README.md.ftl", model, topDir);

/* Handle the individual example projects */
for (String module : project.getModules()) {

    String pomPath = topDir + "/" + module + "/pom.xml";
    model.putModel(model.loadPom(new File(pomPath)));

    mergeDirRecursive(cfg, templateRoot, module, model, topDir);
}
