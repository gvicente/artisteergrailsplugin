package se.webinventions.plugins.artisteer

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.cyberneko.html.parsers.SAXParser
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil

import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.beans.factory.InitializingBean
import org.apache.log4j.Logger

class TemplateService implements ApplicationContextAware, InitializingBean {
    ApplicationContext applicationContext = (ApplicationContext) ApplicationHolder.getApplication().getMainContext();





    Logger log = Logger.getLogger(getClass())

    def grailsApplication;
    Class templateDomainClass
    Class pageDomainClass
    Class articleDomainClass
    Class vBlockDomainClass

    def config



    static transactional = false

    void afterPropertiesSet() throws java.lang.Exception {


        if (!grailsApplication) {
            grailsApplication = ApplicationHolder.getApplication()
        }

        config = grailsApplication.config

        def templateClassName = config?.grails?.artisteer.templateClassName ?: "se.webinventions.plugins.artisteer.Template"
        templateDomainClass = grailsApplication.getClassForName(templateClassName)

        def pageClassName = config?.grails?.artisteer.pageClassName ?: "se.webinventions.plugins.artisteer.Page"
        pageDomainClass = grailsApplication.getClassForName(pageClassName)


        def articleClassName = config?.grails?.artisteer.postClassName ?: "se.webinventions.plugins.artisteer.Post"
        articleDomainClass = grailsApplication.getClassForName(articleClassName)

        def vMenuBlockClassName = config?.grails?.artisteer.vMenuBlockClassName ?: "se.webinventions.plugins.artisteer.VerticalBlock"
              vBlockDomainClass = grailsApplication.getClassForName(vMenuBlockClassName)



        this.serverUrl = grailsApplication?.config?.grails?.serverURL

    }

    private String getAppWebAppDir() {
        String pathToAppWebAppDir = applicationContext.getResourceByPath("/").getFile().getAbsolutePath()
        return pathToAppWebAppDir
    }

    def public boolean generateGspForTemplate(Object myTemplate, overwrite) {
        String pathToAppWebAppDir = getAppWebAppDir()
        return generateGspForTemplate(myTemplate, overwrite, pathToAppWebAppDir)
    }

    def public boolean generateGspForTemplate(Object myTemplate, overwrite, pathToAppWebAppDir) {

        if (myTemplate) {


            String pathToRootAppDir = pathToAppWebAppDir + File.separatorChar + ".." + File.separatorChar;

            String pathToLayoutsDir = pathToRootAppDir + File.separatorChar + "grails-app" + File.separatorChar + "views" + File.separatorChar + "layouts"
            def tpid = '';
            if (myTemplate?.id) {
                tpid = "-" + myTemplate?.id
            }


            def gspFile = new File(pathToLayoutsDir + File.separatorChar + myTemplate.name + tpid + ".gsp");
            if (gspFile.exists() && !overwrite) {
                return true;
            }
            else {
                //generate the gsp file and then try again..
                if (log) {
                    log?.info("trying to generate gsp file for $myTemplate.name version $myTemplate?.id for file: " + gspFile);
                }

                if (generateGspFromTemplateHtml(myTemplate, gspFile, pathToAppWebAppDir)) {
                    return true;
                }

            }
        }
        return false;

    }

    def retreiveZipAndDeflateToTemplateDir(Object myTemplate, String pathToAppWebAppDir) {


        if (myTemplate) {

            def tpid = '';
            if (myTemplate?.id) {
                tpid = "-" + myTemplate?.id
            }

            File tempFile = File.createTempFile((myTemplate.name + tpid), "");
            tempFile.setBytes(myTemplate.getZip());
            AntBuilder ant = new AntBuilder();

            String pathToTemplateDir = pathToAppWebAppDir + File.separatorChar + "artisteertemplates" + File.separatorChar
            try {
                def myDir = new File(pathToTemplateDir);
                if (!myDir.exists()) {
                    myDir.mkdirs();
                }
                println "tempfile is $tempFile and size is " + tempFile.size();
                ant.unzip(src: tempFile, dest: pathToTemplateDir + myTemplate.name + tpid + File.separatorChar)
            } catch (Throwable t) {
                println t.toString();
            }


        } else {
            println "couldnt find template with name $name"
        }
    }

    def public boolean deflateZipFromTemplate(Object myTemplate, boolean overwrite) {
        String pathToAppWebAppDir = getAppWebAppDir()

        return deflateZipFromTemplate(myTemplate, overwrite, pathToAppWebAppDir)
    }

    def public boolean deflateZipFromTemplate(Object myTemplate, boolean overwrite, String pathToAppWebAppDir) {

        if (myTemplate) {

            def tpid = '';
            if (myTemplate?.id) {
                tpid = "-" + myTemplate?.id
            }
            String pathToTemplateDir = pathToAppWebAppDir + File.separatorChar + "artisteertemplates" + File.separatorChar + myTemplate.name + tpid + "/" + myTemplate.name + "/"
            def pageFile = new File(pathToTemplateDir + File.separatorChar + "page.html");
            if (pageFile.exists() && !overwrite) {
                return true
            } else {
                //unzip it and check again..
                retreiveZipAndDeflateToTemplateDir(myTemplate, pathToAppWebAppDir);
                if (pageFile.exists()) {
                    return true
                }
            }


        }

        //if we reach this state we havent found anything..
        return false;

    }

    /**
     * save sidebars blocks for a html file, not it doesnt take the template into account
     * @param f
     */
    def private void saveSidebarBlocks(File f) {

    }
    /**
     * Save page and its posts for an html file, note it doesnt take template into account
     * which in turns means that all pages/posts will show up on all templates..
     * @param f
     */
    def private void savePageAndPosts(File f) {


        //create the page
        def page = pageDomainClass.newInstance()
        page.save()

        def saxParser = new SAXParser();
        saxParser.setFeature('http://xml.org/sax/features/namespaces', false)

        def html = new XmlSlurper(saxParser).parseText(f.getText());

        //find the first article and layout
        def contentNode = html.'**'.findAll {
            it?.@class?.text()?.indexOf("art-post") != -1
        }

                    //each article is a post
        contentNode?.each {cn ->
            //create the article domain class and save it
            def newArticle = articleDomainClass.newInstance(content:getXmlHtmlStringFromHtmlXmlNode(cn))
            newArticle.save()
            page.addToArticles(newArticle)
            page.save()

        }


    }


    def public Boolean generateVerticalBlocksFromTemplate(Object myTemplate) {

    }

    /**
     * Generate pages and post domains from template file
     * @param myTemplate
     * @return
     */
    def public Boolean generatePagesAndArticlesFromTemplate(Object myTemplate) {

        def tpid = '';
        if (myTemplate?.id) {
            tpid = "-" + myTemplate?.id
        }

        String pathToWebAppDir = getAppWebAppDir()
        String pathToTemplateDir = pathToWebAppDir + "/artisteertemplates/" + myTemplate.name + tpid + "/" + myTemplate.name + "/"

        //find all 'vertical sidebar blocks and put them into the db' this only needs to be done once.
        //find all 'pages' and put them into the db

        def p = ~/.*\.html/
        new File(pathToTemplateDir).eachFileMatch(p) { file ->
            //the index file will always exist so we can use this to get the sidebars blocks and also to skip it
            //since there will be duplicate of index (e.g. 'home') artisteer version 3.0 specific.
            if (!file.getName().contains("index")) {
              //save the page and articles/posts belonging to this page
                savePageAndPosts(file)
            }

        }


    }

    def public boolean generateGspFromTemplateHtml(Object myTemplate, File gspFileOut, String pathToWebAppDir) {


        def tpid = '';
        if (myTemplate?.id) {
            tpid = "-" + myTemplate?.id
        }
        String pathToTemplateDir = pathToWebAppDir + "/artisteertemplates/" + myTemplate.name + tpid + "/" + myTemplate.name + "/"
        String pathToCssFromTemplates = "../../../web-app/artisteertemplates" + File.separatorChar + myTemplate.name + tpid + "/" + myTemplate.name + "/"

        //keep to 2 known pages.. (page in artisteer 2.5 and below, index.html in artisteer 3.0 and above)
        def pageFile = new File(pathToTemplateDir + "/page.html");
        if (!pageFile.exists()) {
            pageFile = new File(pathToTemplateDir + "/index.html");
        }

        if (pageFile.exists()) {

            //generate the gsp for this page file..
            def saxParser = new SAXParser();
            saxParser.setFeature('http://xml.org/sax/features/namespaces', false)

            def html = new XmlSlurper(saxParser).parseText(pageFile.getText());
            def title = "my own title"

            //remove certain elements..

            //check if there are any pages, if not generate them
            if(pageDomainClass.list().size() == 0) {
                generatePagesAndArticlesFromTemplate(myTemplate)
            }

            //check verticla blocks if there arent any generate them
            if(vBlockDomainClass.list().size()==0) {

            }



            html.HEAD[0].replaceNode {
                head() {

                    link(rel: "stylesheet", href: "${serverUrl}/css/main.css")

                    link(rel: "stylesheet", href: "${serverUrl}/artisteertemplates/${myTemplate.name + tpid + '/' + myTemplate.name}/style.css", type: "text/css");
                    mkp.yieldUnescaped("<!--[if IE 6]>")

                    link(rel: "stylesheet", href: "${serverUrl}/artisteertemplates/${myTemplate.name + tpid + '/' + myTemplate.name}/style.ie6.css", type: "text/css");
                    mkp.yieldUnescaped("<![endif]-->")

                    mkp.yieldUnescaped("<!--[if IE 7]>")
                    link(rel: "stylesheet", href: "${serverUrl}/artisteertemplates/${myTemplate.name + tpid + '/' + myTemplate.name}/style.ie7.css", type: "text/css");
                    mkp.yieldUnescaped("<![endif]-->")


                    g.javascript(library: "application");

                    mkp.yieldUnescaped("<title>")
                    g.layoutTitle(default: "some title");
                    mkp.yieldUnescaped("</title>")


                    meta("http-equiv": "Content-Type", content: "text/html; charset=UTF-8")
                    script(type: "text/javascript", src: "${serverUrl}/artisteertemplates/${myTemplate.name + tpid + '/' + myTemplate.name}/script.js")
                    g.layoutHead()
                    g.applyLayout(name: "artisteerheaderadditions")

                }

                //find the content article posts and layout body in the first and remove the rest.
                //(in each saved post there will be 'art-post')
                def contentNode = html.'**'.findAll {
                    it?.@class?.text()?.indexOf("art-post") != -1
                }

                //we layout the body for the page, the pagecontroller will be responsible for laying out articles and
                //such things instead.
                contentNode?.eachWithIndex {cn, i ->

                    switch (i) {
                        case 0:
                            cn.replaceNode {

                                g.layoutBody()

                            }
                            break;
                        default:
                            cn.replaceNode {}
                            break;
                    }

                }


                def logoName = html.'**'.find {
                    it?.@class?.text()?.indexOf("art-logo-name") != -1
                }

                logoName?.replaceNode {
                    H1(id: "name-text", class: "art-logo-name") {
                        g.applyLayout(name: "artisteerLogoName")
                    }

                }


                def sloganText = html.'**'.find {
                    it?.@class?.text()?.indexOf("art-logo-text") != -1
                }

                sloganText?.replaceNode {
                    DIV(id: "slogan-text", class: "art-logo-text") {
                        g.applyLayout(name: "artisteerLogoText")
                    }

                }




                def navMenu = html.'**'.find {
                    it?.@class?.text()?.indexOf("art-menu") != -1
                }

                navMenu?.replaceNode {
                    //it is important that the artisteerNavigation makes a UL with class art-nav to work..
                    //g.applyLayout(name:"artisteerNavigation")
                    g.applyLayout(name: "artisteerNavMenu")


                }

                def navVertMenu = html.'**'.find {
                    it?.UL?.@class?.text()?.indexOf("art-vmenu") != -1
                }

                navVertMenu?.replaceNode {
                    //it is important that the artisteerNavigation makes a UL with class art-nav to work..
                    //g.applyLayout(name:"artisteerNavigation")
                    g.applyLayout(name: "artisteerVerticalNavMenu")


                }


                def footerNodeText = html.'**'.find {
                    it?.@class?.text()?.indexOf("art-footer-text") != -1
                }

                footerNodeText?.replaceNode {
                    div(class: "art-footer-text") {
                        g.applyLayout(name: "artisteerFooterText")
                    }

                }

                def footerNodeLink = html.'**'.find {
                    it?.@class?.text()?.indexOf("art-page-footer") != -1
                }

                footerNodeLink?.replaceNode {
                    p(class: "art-page-footer") {
                        g.applyLayout(name: "artisteerPageFooterLink")
                    }

                }

            }



            String replacement = getXmlHtmlStringFromHtmlXmlNode(html).replaceAll('src="images/', 'src="' + serverUrl + '/artisteertemplates/' + myTemplate.name + tpid + '/' + myTemplate.name + '/images/')

              if (!gspFileOut.exists()) {
                gspFileOut.createNewFile()


            }


            gspFileOut.setText(replacement);
            return true;

        } else {
            println "couldnt find html file for " + pageFile.toString()
            return false;
        }
    }

    private String getXmlHtmlStringFromHtmlXmlNode(html) {
        def outHtml = new StreamingMarkupBuilder()


        def String output = XmlUtil.serialize(outHtml.bind {

            mkp.declareNamespace('g': 'http://grails.codehaus.org/tags')
            mkp.declareNamespace('nav': 'http://www.grails.org/plugin/navigation')

            mkp.yield html
        })

        output = output.replace('<?xml version="1.0" encoding="UTF-8"?>', '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">')

        // xmlutil doesn't generate correct xhtml .. so fix this with regexp replacement  for now..
        def replacement = output.replaceAll(/<.*\/>/, {

            // it.replaceAll(/(<)(.* )(.* )(.*")(\/>)/,'$1$2$3$4></$2>')
            if (it.indexOf("<DIV") != -1) {

                it.replaceAll("/>", "></DIV>") //.replaceAll("DIV", "div");
            } else if (it.indexOf("<A") != -1) {
                it.replaceAll("/>", "></A>") //.replaceAll("<A", "<a")
            } else if (it.indexOf("<script") != -1) {
                it.replaceAll("/>", "></script>");
            } else {
                it
            }

        })


        replacement = replacement.replaceAll("<BODY>", "<body>").replaceAll("</BODY>", "</body>").replaceAll("<HTML>", "<html>").replaceAll("</HTML>", "</html>").replaceAll("<A", "<a").replaceAll("</A>", "</a>").replaceAll("<DIV", "<div").replaceAll("</DIV>", "</div>")
        return replacement
    }

    def public void checkAndCreateInitialDirs() {
        String pathToAppWebAppDir = getAppWebAppDir()
        String pathToTemplateDir = pathToAppWebAppDir + File.separatorChar + "artisteertemplates"
        def myDir = new File(pathToTemplateDir);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
    }
    /**
     * Generate initial templates for artisteer
     */
    def public void generateInitialTemplates() {

        String pathToAppWebAppDir = getAppWebAppDir()
        String pathToLayoutsDir = pathToAppWebAppDir + "/../grails-app/views/layouts/"
        println pathToLayoutsDir;


        def fileList = [new File(pathToLayoutsDir + "artisteerLogoName.gsp"),
                new File(pathToLayoutsDir + "artisteerLogoText.gsp"),
                new File(pathToLayoutsDir + "artisteerNavMenu.gsp"),
                new File(pathToLayoutsDir + "artisteerFooterText.gsp"),
                new File(pathToLayoutsDir + "artisteerPageFooterLink.gsp"),
                new File(pathToLayoutsDir + "artisteerVerticalNavMenu.gsp"),
                new File(pathToLayoutsDir + "artisteerTitle.gsp"),
                new File(pathToLayoutsDir + "artisteerafterbody.gsp"),
                new File(pathToLayoutsDir + "artisteerbeforebody.gsp"),
                new File(pathToLayoutsDir + "artisteerheaderadditions.gsp")]

        fileList.each {file ->

            println "Checking file exists: " + file.getName();
            if (!file.exists()) {
                println "Creating new file: " + file.getName();
                file.createNewFile();
                switch (file.getName()) {
                    case "artisteerLogoName.gsp": file.setText("set text!"); break;
                    case "artisteerafterbody.gsp": file.setText(""); break;
                    case "artisteerbeforebody.gsp": file.setText(""); break;
                    case "artisteerheaderadditions.gsp": file.setText(""); break;
                    case "artisteerLogoText.gsp": file.setText("<p> configure your logtext in the layoutdir</p>"); break;
                    case "artisteerFooterText.gsp": file.setText("<p> Configure your footer text in the layoutdir</p>"); break;
                    case "artisteerPageFooterLink.gsp": file.setText('<a href="#">configurePageFooterLinkInLayoutDir</a>'); break;
                    case "artisteerTitle.gsp": file.setText("A default title, change in artisteerTitle.gsp in layouts folder!"); break;
                    case "artisteerVerticalNavMenu.gsp": file.setText("""

<artisteer:renderVerticalTopMenu >

  <artisteer:topVerticalNavListItem active="true" href="#" title="A default title">

  </artisteer:topVerticalNavListItem>

  <artisteer:topVerticalNavListItem href="#" title="A default title">
  <artisteer:renderVerticalSubMenu >
    <artisteer:subVerticalNavListItem href="#" title="A default submenu title">
             <artisteer:renderVerticalSubMenu >
               <artisteer:subVerticalNavListItem href="#" title="A default subsubmenu title">

               </artisteer:subVerticalNavListItem>
               <artisteer:subVerticalNavListItem href="#" title="A default subsubmenu title">

               </artisteer:subVerticalNavListItem>
               <artisteer:subVerticalNavListItem href="#" title="A default subsubmenu title">

               </artisteer:subVerticalNavListItem>
             </artisteer:renderVerticalSubMenu>
    </artisteer:subVerticalNavListItem>
  </artisteer:renderVerticalSubMenu>
  </artisteer:topVerticalNavListItem>


</artisteer:renderVerticalTopMenu>



          """); break;
                    case "artisteerNavMenu.gsp":
                        file.setText("""
                                        <artisteer:renderTopMenu >

                                          <artisteer:topNavListItem active="true" href="#" title="A default title">

                                          </artisteer:topNavListItem>

                                          <artisteer:topNavListItem href="#" title="A default title">
                                          <artisteer:renderSubMenu >
                                            <artisteer:subNavListItem href="#" title="A default submenu title">
                                                     <artisteer:renderSubMenu >
                                                       <artisteer:subNavListItem href="#" title="A default subsubmenu title">

                                                       </artisteer:subNavListItem>
                                                       <artisteer:subNavListItem href="#" title="A default subsubmenu title">

                                                       </artisteer:subNavListItem>
                                                       <artisteer:subNavListItem href="#" title="A default subsubmenu title">

                                                       </artisteer:subNavListItem>
                                                     </artisteer:renderSubMenu>
                                            </artisteer:subNavListItem>
                                          </artisteer:renderSubMenu>
                                          </artisteer:topNavListItem>


                                        </artisteer:renderTopMenu>


                                                  """);


                        break;
                    default: file.setText("<p>" + file.getName() + ": set configuration in layoutsdir</p>");

                        break;
                }
            }

        }


    }


}
