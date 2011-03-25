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
    templateInstance.name = f.getOriginalFilename().replaceAll(".zip", "");

    if (templateInstance.validate()) {
      templateInstance.save(flush: true);
      if (templateService.deflateZipFromTemplate(templateInstance, true)) {

        if(templateService.generateGspForTemplate(templateInstance, true)) {
         flash.message = "${message(code: 'default.created.message', args: [message(code: 'template.label', default: 'Template'), templateInstance.id])}"


        redirect(action: "show", id: templateInstance.id)
        } else {
        templateInstance.errors.rejectValue("zip","Could not generate template files from zip")
        render(view: "create", model: [templateInstance: templateInstance])
      }

      }
      else {
        templateInstance.errors.rejectValue("zip","Could not unzip file to temp directory ")
        render(view: "create", model: [templateInstance: templateInstance])
      }
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
