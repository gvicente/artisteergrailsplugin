package se.webinventions.plugins.artisteer

import org.springframework.web.multipart.MultipartFile
import org.springframework.beans.factory.InitializingBean
import groovy.xml.XmlUtil
import groovy.xml.StreamingMarkupBuilder
import org.cyberneko.html.parsers.SAXParser
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import se.webinventions.plugins.artisteer.TemplateService

class TemplateController implements ApplicationContextAware, InitializingBean {
  ApplicationContext applicationContext


  def templateService;
   def grailsApplication;
  Boolean warDeployed = false;

  void afterPropertiesSet() throws java.lang.Exception {
        this.warDeployed = grailsApplication.isWarDeployed()

  }
  

  def private  boolean generateGspForTemplate(Template myTemplate, overwrite) {

    if (myTemplate) {

      String pathToAppWebAppDir = applicationContext.getResourceByPath("/").getFile().getAbsolutePath();


      String pathToRootAppDir = pathToAppWebAppDir + File.separatorChar + ".." + File.separatorChar;
      if(warDeployed) {
        pathToRootAppDir = pathToAppWebAppDir;
      }
      String pathToLayoutsDir = pathToRootAppDir + File.separatorChar + "grails-app" + File.separatorChar + "views" + File.separatorChar + "layouts"
      def gspFile = new File(pathToLayoutsDir + File.separatorChar + myTemplate.name + ".gsp");
      if (gspFile.exists() && !overwrite) {
        return true;
      }
      else {
        //generate the gsp file and then try again..
        println "trying to generate gsp file for $myTemplate.name"
        if (templateService.generateGspFromTemplateHtml(myTemplate, gspFile))
        {
          return true;
        }

      }
    }
    return false;

  }

  def retreiveZipAndDeflateToTemplateDir(Template myTemplate) {


    if (myTemplate) {
      File tempFile = File.createTempFile(myTemplate.name,"");
      tempFile.setBytes(myTemplate.getZip());
      AntBuilder ant = new AntBuilder();
      String pathToAppWebAppDir = applicationContext.getResourceByPath("/").getFile().getAbsolutePath();
      String pathToTemplateDir = pathToAppWebAppDir +File.separatorChar+"artisteertemplates"+File.separatorChar
      try {
        def myDir = new File(pathToTemplateDir);
        if(!myDir.exists()) {
          myDir.mkdirs();
        }
        println "tempfile is $tempFile and size is " +tempFile.size();
        ant.unzip(src: tempFile, dest: pathToTemplateDir)
      } catch (Throwable t) {
        println t.toString();
      }


    } else {
      println "couldnt find template with name $name"
    }
  }
    def private boolean deflateZipFromTemplate(Template myTemplate, boolean overwrite) {
    
    if (myTemplate) {
      String pathToAppWebAppDir = applicationContext.getResourceByPath("/").getFile().getAbsolutePath();
      String pathToTemplateDir = pathToAppWebAppDir + File.separatorChar+ "artisteertemplates" + File.separatorChar+ myTemplate.name + "/"
      def pageFile = new File(pathToTemplateDir + File.separatorChar+ "page.html");
      if (pageFile.exists() &&!overwrite) {
        return true
      } else {
        //unzip it and check again..
        retreiveZipAndDeflateToTemplateDir(myTemplate);
        if (pageFile.exists()) {
          return true
        }
      }


    }

    //if we reach this state we havent found anything..
    return false;

  }


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [templateInstanceList: Template.list(params), templateInstanceTotal: Template.count()]
    }

    def create = {
        def templateInstance = new Template()
        templateInstance.properties = params
        return [templateInstance: templateInstance]
    }

    def save = {
        def templateInstance = new Template(params)
        def MultipartFile f = request.getFile('zip');
        println "filename is " + f.getOriginalFilename()
        templateInstance.name = f.getOriginalFilename().replaceAll(".zip","");

        if (templateInstance.save(flush: true) && deflateZipFromTemplate(templateInstance,true) && generateGspForTemplate(templateInstance,true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'template.label', default: 'Template'), templateInstance.id])}"


            redirect(action: "show", id: templateInstance.id)
        }
        else {
            render(view: "create", model: [templateInstance: templateInstance])
        }
    }

    def show = {
        def templateInstance = Template.get(params.id)
        if (!templateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), params.id])}"
            redirect(action: "list")
        }
        else {
          
            [templateInstance: templateInstance]
        }
    }

    def edit = {
        def templateInstance = Template.get(params.id)
        if (!templateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [templateInstance: templateInstance]
        }
    }

    def update = {
        def templateInstance = Template.get(params.id)
        if (templateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (templateInstance.version > version) {
                    
                    templateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'template.label', default: 'Template')] as Object[], "Another user has updated this Template while you were editing")
                    render(view: "edit", model: [templateInstance: templateInstance])
                    return
                }
            }
            templateInstance.properties = params
            if (!templateInstance.hasErrors() && templateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'template.label', default: 'Template'), templateInstance.id])}"
                redirect(action: "show", id: templateInstance.id)
            }
            else {
                render(view: "edit", model: [templateInstance: templateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def templateInstance = Template.get(params.id)
        if (templateInstance) {
            try {
                templateInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'template.label', default: 'Template'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'template.label', default: 'Template'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), params.id])}"
            redirect(action: "list")
        }
    }
}
