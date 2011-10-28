package se.webinventions.plugins.artisteer

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

import org.springframework.beans.factory.InitializingBean
import se.webinventions.util.BeanBagUser
import se.webinventions.util.SafePathAppender
import se.webinventions.util.Default
import org.devunited.groovy.util.regex.KushalsNestedPatternMatcher

class TemplateService implements ApplicationContextAware, InitializingBean, BeanBagUser {


    def applicationContext

    private File zipDir
    private File targetDir

    public static def handlerBean


    public void unzipAllTemplatesInZipDir() {
        if (!zipDir.exists()) {
            println "ERROR- ARTISTEER - Zip Dir Not Found"
            return
        }
        syncDatabaseToZipDir()
        zipDir.eachFile {file ->
            if (file.isDirectory()) {
                file.eachFile {subFile ->
                    createAndUnzipTemplate(subFile, file.name)
                }
            } else {
                createAndUnzipTemplate(file, "General")
            }
        }
    }

    private void syncDatabaseToZipDir() {
        List<String> fileNames = []
        zipDir.eachFileRecurse {file ->
            fileNames.add(file.getName() - ".zip")
        }
        Template.list().each {template ->
            if (!fileNames.find {it == template.name}) {
                File target = new File(new SafePathAppender(zipDir.getCanonicalPath()).append(template.category))
                target.mkdirs()
                target = new File(new SafePathAppender(zipDir.getCanonicalPath()).append("${template.category}/${template.name}.zip"))
                target.createNewFile()
                target.setBytes(template.zip)
            }
        }
    }

    public void initializeTemplate(Template template) {
        File file = new File(new SafePathAppender(zipDir.getCanonicalPath()).append("${template.category}/${template.name - '.zip'}.zip"))
        createAndUnzipTemplate(file, template.category)
    }

    public void createAndUnzipTemplate(File file, String category) {
        Template template = Template.findByName(file.getName() - ".zip")
        if (template) {
            template.zip = file.getBytes()
            template.category = category
        } else {
            template = new Template(
                    category: category,
                    name: file.getName() - ".zip",
                    zip: file.getBytes()
            )
        }
        if (template.validate()) {
            template.save(flush: true)
            unzipTemplate(file)
            createTemplateResourceDirsInWebApp(template)
            copyResourcesInWebAppFromTemplateStagingDir(template)
            digestIndexPageToGspLayout(template)
        }
    }

    public void unzipTemplate(File file) {
        File tempFile = File.createTempFile(file.getName(), "")
        tempFile.setBytes(file.getBytes());
        AntBuilder ant = new AntBuilder();
        String pathToTemplateDir = new SafePathAppender(targetDir.getCanonicalPath()).append(file.getName() - ".zip")
        try {
            File targetTemplateDir = new File(pathToTemplateDir)
            if (targetTemplateDir.exists()) {
                targetTemplateDir.delete()
                targetTemplateDir.mkdirs()
            }
            ant.unzip(src: tempFile, dest: targetTemplateDir)
        } catch (Throwable t) {
            println t.toString();
        }
    }

    public void createTemplateResourceDirsInWebApp(Template template) {
        String targetBase = readFromBeanBag("webAppDir")
        new File(new SafePathAppender(targetBase).append("css/templates/${template.name}")).mkdirs()
        new File(new SafePathAppender(targetBase).append("css/templates/${template.name}/images")).mkdirs()
        new File(new SafePathAppender(targetBase).append("js/templates/${template.name}")).mkdirs()
    }

    public void copyResourcesInWebAppFromTemplateStagingDir(Template template) {
        String templateBase = new SafePathAppender(targetDir.getCanonicalPath()).append(template.name)
        String targetBase = readFromBeanBag("webAppDir")
        AntBuilder ant = new AntBuilder()
        ant.copy(
                toDir: new SafePathAppender(targetBase).append("css/templates/${template.name}/images"),
                filtering: true,
                overwrite: true
        ) {
            fileset(dir: new SafePathAppender(templateBase).append("images"))
        }
        new File(templateBase).eachFile {file ->
            if (file.getName().endsWith(".css")) {
                new File(new SafePathAppender(targetBase).append("css/templates/${template.name}/${file.getName()}")).setBytes(file.getBytes())
            } else if (file.getName().endsWith(".js")) {
                new File(new SafePathAppender(targetBase).append("js/templates/${template.name}/${file.getName()}")).setBytes(file.getBytes())
            }
        }
    }

    public Template saveAndUploadANewArtisteerTemplateFromBytes(String name, String category, byte[] bytes) {
        Template template = Template.findByName(name)
        if (!template) {
            File target = new File(new SafePathAppender(targetDir.getCanonicalPath()).append(category))
            if (!target.exists()) {
                target.mkdirs()
            }
            target = new File(new SafePathAppender(target.getCanonicalPath()).append(name + ".zip"))
            target.setBytes(bytes)
            createAndUnzipTemplate(target, category)
        } else return null
        template
    }

    private void digestIndexPageToGspLayout(Template template) {

        String pathToTemplateDirectory = new SafePathAppender(targetDir.getCanonicalPath()).append(template.name)
        File fileObject = new File(new SafePathAppender(pathToTemplateDirectory).append("index.html"))
        if (!fileObject.exists()) {
            fileObject = new File(new SafePathAppender(pathToTemplateDirectory).append("page.html"))
        }

        if (fileObject.exists()) {
            String file = fileObject.text
            String head = file.find(/<[hH][eE][aA][dD]>[.\w\s<>!-:"';={}\|\[\]\d\s\D\S]*<\/[hH][eE][aA][dD]>/)
            head = head.replaceAll(/<[hH][eE][aA][dD]>/, "").replaceAll(/<\/[hH][eE][aA][dD]>/, "")
            head = head.replaceAll(/<[tT][iI][tT][lL][eE]>.*<\/[tT][iI][tT][lL][eE]>/, "<title><g:layoutTitle default=\"Grails\"/></title>")
            head = head + "\n" + "<g:layoutHead/>"

            String path = "templates/${template.name}"

            head.findAll(/[hH][rR][eE][fF][=\s"']*.*?["']/).each {String match ->
                String s = match.replaceAll(/[hH][rR][eE][fF]/, "").replaceAll(/["'=\s]/, "")
                String s1 = "hreeft=\"\${resource(dir: 'css', file: '${path}/${s}')}\""
                head = head.replaceFirst(/[hH][rR][eE][fF][=\s"']*.*?["']/, "#########temp#########").replace("#########temp#########", s1)
            }
            head = head.replaceAll("hreeft", "href")

            head.findAll(/[sS][rR][cC][=\s"']*.*?["']/).each {String match ->
                String s = match.replaceAll(/[sS][rR][cC]/, "").replaceAll(/["'=\s]/, "")
                String s1 = "sstrrtcc=\"\${resource(dir: 'js', file: '${path}/${s}')}\""
                head = head.replaceFirst(/[sS][rR][cC][=\s"']*.*?["']/, "#########temp#########").replace("#########temp#########", s1)
            }
            head = head.replaceAll("sstrrtcc", "src")

            file = file.replaceAll(/<[hH][eE][aA][dD]>[.\w\s<>!-:"';={}\|\[\]\d\s\D\S]*<\/[hH][eE][aA][dD]>/, "<head>\n######headHere######\n</head>").replace("######headHere######", head)

            String menu = file.find(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-nav-inner["'][.\w\s<>!-:"';={}\|\[\]]*?<\/[\s]*\1[\s]*>/)
            file = file.replaceFirst(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-nav-inner["'][.\w\s<>!-:"';={}\|\[\]]*?<\/[\s]*\1[\s]*>/, "<g:render template=\"/layouts/navigationMenu\" />")

            String logo = file.find(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-logo["'][.\w\s<>!-:"';={}\|\[\]]*?<\/[\s]*\1[\s]*>/)
            file = file.replaceFirst(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-logo["'][.\w\s<>!-:"';={}\|\[\]]*?<\/[\s]*\1[\s]*>/, "<g:render template=\"/layouts/logo\" />")


            String pathToLayoutsDir = new SafePathAppender(readFromBeanBag("rootAppDir").toString()).append("grails-app/views/layouts")
            File gspFile = new File(new SafePathAppender(pathToLayoutsDir).append("${template.name}.gsp"))

            file = file.replaceAll(/>[\s]*<\/[hH][rR]>/, " />")
            String body = file.find(/<[bB][oO][dD][yY]>[.\w\s<>!-:"';={}\|\[\]\r\n\t\.\d\s\D\S]*<\/[bB][oO][dD][yY]>/)
            String bodyContent = body.find(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-sheet-body["'][.\w\s<>!-:"';={}\|\[\]\d\s\D\S]*<\/[\s]*\1[\s]*>/)

            KushalsNestedPatternMatcher nestedPatternMatcher = new KushalsNestedPatternMatcher(
                    /(<[\s]*[dV][iV][vV][\s\d\w\D\S]*?>)/,
                    /(<\/[\s]*[dV][iV][vV][\s\d\w\D\S]*?>)/,
                    bodyContent
            )
            body = body.replaceAll(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-sheet-body["'][.\w\s<>!-:"';={}\|\[\]\d\s\D\S]*<\/[\s]*\1[\s]*>/, "######bodyHere######").replace("######bodyHere######", nestedPatternMatcher.replaceMatchedText("<div class=\"art-sheet-body\">\n<g:layoutBody/>\n</div>"))

            String footerContent = body.find(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-footer["'][.\w\s<>!-:"';={}\|\[\]\d\s\D\S]*<\/[\s]*\1[\s]*>/)
            nestedPatternMatcher = new KushalsNestedPatternMatcher(
                    /(<[\s]*[dV][iV][vV][\s\d\w\D\S]*?>)/,
                    /(<\/[\s]*[dV][iV][vV][\s\d\w\D\S]*?>)/,
                    footerContent
            )
            body = body.replaceAll(/<([\w]*)[\s]*[cC][lL][aA][sS]{2}[\s]*=[\s]*["']art-footer["'][.\w\s<>!-:"';={}\|\[\]\d\s\D\S]*<\/[\s]*\1[\s]*>/, "######footHere######").replace("######footHere######", nestedPatternMatcher.replaceMatchedText("<div class=\"art-footer\">\n<g:render template=\"/layouts/footer\"/>\n</div>"))

            file = file.replaceAll(/<[bB][oO][dD][yY]>[.\w\s<>!-:"';={}\|\[\]\r\n\t\.\d\s\D\S]*<\/[bB][oO][dD][yY]>/, "######bodyHere######").replace("######bodyHere######", body)

            gspFile.setText(file)
            generateSupportingLayouts(pathToLayoutsDir, logo, menu, Default.defaultFooter)
        } else {
            log.error "FATAL: Unable To Extract Gsp Layout as Index.html was not present, Template unuseable now."
        }
    }



    void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext
    }

    void afterPropertiesSet() {
        zipDir = new File(readFromBeanBag("zipDir").toString())
        targetDir = new File(readFromBeanBag("targetDir").toString())
    }

    private void generateSupportingLayouts(String layoutDir, String logoContent, String navMenuContent, String footerContent) {
        File logoFile = new File(new SafePathAppender(layoutDir).append("_logo.gsp"))
        File navigationMenuFile = new File(new SafePathAppender(layoutDir).append("_navigationMenu.gsp"))
        File footerFile = new File(new SafePathAppender(layoutDir).append("_footer.gsp"))
        File mainFile = new File(new SafePathAppender(layoutDir).append("main.gsp"))
        if (!logoFile.exists()) {
            logoFile.createNewFile()
            logoFile.setText(logoContent)
        }
        if (!navigationMenuFile.exists()) {
            navigationMenuFile.createNewFile()
            navigationMenuFile.setText(navMenuContent)
        }
        if (!footerFile.exists()) {
            footerFile.createNewFile()
            footerFile.setText(footerContent)
        }
        if (!mainFile.exists()) {
            mainFile.createNewFile()
            mainFile.setText(Default.defaultMainLayout)
        } else {
            if (!mainFile.getText().contains("applyArtisteerLayout")) {
                mainFile.setText(Default.defaultMainLayout)
            }
        }
    }

    public Template getToBeAppliedTemplate() {
        if (handlerBean) {
            return handlerBean.getArtisteerTemplate()
        } else return null
    }
}