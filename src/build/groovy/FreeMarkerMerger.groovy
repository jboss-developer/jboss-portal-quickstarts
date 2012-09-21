import freemarker.template.*
import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

/* Do the usual FreeMarker stuff */
def mergeReadme(cfg, templateRoot, templatePath, model) {
    String outputPath = templatePath.replaceAll("\\.ftl\$", "")
    log.info("Merging '${templateRoot}${File.separator}${templatePath}' -> '${outputPath}'.")
    Template temp = cfg.getTemplate(templatePath)
    Writer out = new OutputStreamWriter(new FileOutputStream(new File(outputPath)), "utf-8")
    temp.process(model, out)
    out.close()
}

/* Put the properties from the top pom.xml into the FreeMarker model
 * and do in the FreeMarker way - i.e. hierarchically */
def putProperties(properties, model) {
    for (prop in properties){
        StringTokenizer st = new StringTokenizer(prop.getKey(), ".")
        Map parent = model
        while (st.hasMoreTokens()) {
            String token = st.nextToken()
            if (st.hasMoreTokens()) {
                Object nextParent = parent.get(token)
                if (nextParent == null) {
                    nextParent = new HashMap()
                    parent.put(token, nextParent)
                }
                parent = nextParent
            }
            else {
                parent.put(token, prop.getValue())
            }
            // log.info("   handling prop segment '${token}'.")
        }
    }
}

/* FreeMarker configuration */
Configuration cfg = new Configuration()
String templateRoot = "${project.basedir}${File.separator}src/main/freemarker"
cfg.setDirectoryForTemplateLoading(new File(templateRoot))
cfg.setObjectWrapper(new DefaultObjectWrapper())

/* Create a data model to merge with FreeMarker templates */
Map model = new HashMap()
model.put("project", project)
model.put("derivedFileNotice", "Do not edit this derived file! Rather edit the master file ${project.artifactId}/src/main/freemarker/")

putProperties(project.properties, model)

/* Merge the top level README */
mergeReadme(cfg, templateRoot, "README.md.ftl", model)

/* Handle the individual example projects */
MavenXpp3Reader pomReader = new MavenXpp3Reader()
for (module in project.modules){
    
    String pomPath = "${project.basedir}${File.separator}${module}${File.separator}pom.xml"
    Reader r = new InputStreamReader(new FileInputStream(new File(pomPath)),"utf-8")
    Model moduleProject = pomReader.read(r)
    r.close()
    /* overwrite "project" with this module's project model */
    model.put("project", moduleProject)
    
    mergeReadme(cfg, templateRoot, "${module}${File.separator}README.md.ftl", model)
}
