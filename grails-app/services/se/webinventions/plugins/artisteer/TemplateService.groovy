package se.webinventions.plugins.artisteer

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.cyberneko.html.parsers.SAXParser
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil
import groovy.xml.MarkupBuilder
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.spring.GrailsRuntimeConfigurator
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.beans.factory.InitializingBean

class TemplateService implements ApplicationContextAware,  InitializingBean {
  ApplicationContext  applicationContext = (ApplicationContext)ApplicationHolder.getApplication().getMainContext();

   def grailsRuntimeConfigurator = new GrailsRuntimeConfigurator(applicationContext.getBean("grailsApplication"));
   def pluginManager = grailsRuntimeConfigurator.getPluginManager();
   def serverUrl = "";
  def grailsApplication;
  
  static transactional = false

  void afterPropertiesSet() throws java.lang.Exception {

    this.serverUrl = grailsApplication?.config?.grails?.serverURL

   }


  def private boolean generateGspFromTemplateHtml(Template myTemplate, File gspFileOut) {
    File dirFileWebApp = applicationContext.getResourceByPath("/").getFile()

    String pathToAppWebAppDir = dirFileWebApp.getAbsolutePath();
    String pathToTemplateDir = pathToAppWebAppDir + "/artisteertemplates/" + myTemplate.name + "/"
    String pathToCssFromTemplates = "../../../web-app/artisteertemplates" + File.separatorChar + myTemplate.name + File.separatorChar
    def pageFile = new File(pathToTemplateDir + "/page.html");
    if (pageFile.exists()) {

      //generate the gsp for this page file..
      def saxParser = new SAXParser();
      saxParser.setFeature('http://xml.org/sax/features/namespaces', false)

      def html = new XmlSlurper(saxParser).parseText(pageFile.getText());
      def title = "my own title"

      //remove certain elements..



      html.HEAD[0].replaceNode {
        head() {

          link(rel: "stylesheet", href: "${serverUrl}/css/main.css")

          link(rel: "stylesheet", href: "${serverUrl}/artisteertemplates/${myTemplate.name}/style.css", type: "text/css");
          mkp.yieldUnescaped("<!--[if IE 6]>")

          link(rel: "stylesheet", href: "${serverUrl}/artisteertemplates/${myTemplate.name}/style.ie6.css", type: "text/css");
          mkp.yieldUnescaped("<![endif]-->")

          mkp.yieldUnescaped("<!--[if IE 7]>")
          link(rel: "stylesheet", href: "${serverUrl}/artisteertemplates/${myTemplate.name}/style.ie7.css", type: "text/css");
          mkp.yieldUnescaped("<![endif]-->")

          nav.resources(override: "true")

          g.javascript(library: "application");

          mkp.yieldUnescaped("<title>")
            g.layoutTitle(default: "some title");
          mkp.yieldUnescaped("</title>")


          meta("http-equiv": "Content-Type", content: "text/html; charset=UTF-8")
          script(type: "text/javascript", src: "${serverUrl}/artisteertemplates/${myTemplate.name}/script.js")
          g.layoutHead()

        }

        //find the first article and layout the body in that..
        def contentNode = html.'**'.find {
          it?.@class?.text()?.indexOf("art-postcontent") != -1
        }


        contentNode.replaceNode {
          div(class: "art-postcontent") {

            g.layoutBody()

          }

        }


        def logoName = html.'**'.find {
          it?.@class?.text()?.indexOf("art-logo-name") != -1
        }

        logoName.replaceNode {
          H1(id: "name-text", class: "art-logo-name") {
            g.applyLayout(name: "artisteerLogoName")
          }

        }


        def sloganText = html.'**'.find {
          it?.@class?.text()?.indexOf("art-logo-text") != -1
        }

        sloganText.replaceNode {
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



      def outHtml = new StreamingMarkupBuilder()


      def String output = XmlUtil.serialize(outHtml.bind {

        mkp.declareNamespace('g': 'http://grails.codehaus.org/tags')
        mkp.declareNamespace('nav': 'http://www.grails.org/plugin/navigation')

        mkp.yield html
      })

      output = output.replace('<?xml version="1.0" encoding="UTF-8"?>', '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd>"')

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
      if (!gspFileOut.exists()) {
        gspFileOut.createNewFile()


      }

      replacement = replacement.replaceAll("<BODY>", "<body>").replaceAll("</BODY>", "</body>")
              .replaceAll("<HTML>", "<html>").replaceAll("</HTML>", "</html>")
              .replaceAll("<A", "<a").replaceAll("</A>", "</a>")
              .replaceAll("<DIV", "<div").replaceAll("</DIV>", "</div>")
      .replaceAll('src="images/','src="'+serverUrl+'/artisteertemplates/'+myTemplate.name+'/images/')




      gspFileOut.setText(replacement);
      return true;

    } else {
      println "couldnt find html file for " + pageFile.toString()
      return false;
    }
  }

  def void checkAndCreateInitialDirs() {
    String pathToAppWebAppDir = applicationContext.getResourceByPath("/").getFile().getAbsolutePath();
    String pathToTemplateDir = pathToAppWebAppDir + File.separatorChar + "artisteertemplates"
    def myDir = new File(pathToTemplateDir);
    if (!myDir.exists()) {
      myDir.mkdirs();
    }
  }
  /**
   * Generate initial templates for artisteer
   */
  def void generateInitialTemplates() {

    String pathToAppWebAppDir = applicationContext.getResourceByPath("/").getFile().getAbsolutePath();
    String pathToLayoutsDir = pathToAppWebAppDir + "/../grails-app/views/layouts/"
    println pathToLayoutsDir;


    def fileList = [new File(pathToLayoutsDir + "artisteerLogoName.gsp"), new File(pathToLayoutsDir + "artisteerLogoText.gsp"),
            new File(pathToLayoutsDir + "artisteerNavMenu.gsp"), new File(pathToLayoutsDir + "artisteerFooterText.gsp"),
            new File(pathToLayoutsDir + "artisteerPageFooterLink.gsp"),
            new File(pathToLayoutsDir + "artisteerVerticalNavMenu.gsp"), new File(pathToLayoutsDir + "artisteerTitle.gsp")]

    fileList.each {file ->

      println "Checking file exists: " + file.getName();
      if (!file.exists()) {
        println "Creating new file: " + file.getName();
        file.createNewFile();
        switch (file.getName()) {
          case "artisteerLogoName.gsp": file.setText("set text!"); break;
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
                   if(pluginManager.hasGrailsPlugin("navigation")) {
                     file.setText("""
                                      <artisteer:renderTopMenu >
                                        <nav:eachItem var="navMainItem">

                                          <artisteer:topNavListItem active=\"\${navMainItem.active}\" href=\"\${navMainItem.link}\" title=\"\${navMainItem.title}\">
                                          </artisteer:topNavListItem>

                                          <nav:ifHasItems>
                                            <artisteer:renderSubMenu >

                                              <nav:eachSubItem var="navSubItem">

                                                <artisteer:subNavListItem href=\"\${navMainItem.link}\" title=\"\${navMainItem.title}\">

                                                </artisteer:subNavListItem>

                                                <nav:ifHasItems>
                                                  <artisteer:renderSubMenu>
                                                    <nav:eachSubItem var="navSubSubItem">
                                                      <artisteer:subNavListItem href=\"\${navMainItem.link}\" title=\"\${navMainItem.title}\">
                                                      </artisteer:subNavListItem>
                                                    </nav:eachSubItem>
                                                  </artisteer:renderSubMenu>
                                                </nav:ifHasItems>
                                              </nav:eachSubItem>
                                            </artisteer:renderSubMenu>
                                          </nav:ifHasItems>
                                        </nav:eachItem>
                                      </artisteer:renderTopMenu>

                                                """);


                   } else {
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
                   }
          
           break;
          default: file.setText("<p>" + file.getName() + ": set configuration in layoutsdir</p>");

          break;
        }
      }

    }


  }


}
