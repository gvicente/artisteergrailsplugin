package se.webinventions.plugins.artisteer

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

class PageController implements ApplicationContextAware, InitializingBean {

    ApplicationContext applicationContext
    def grailsApplication;
    def pageDomainClass
    def articleDomainClass


    def config

    void afterPropertiesSet() throws java.lang.Exception {

        if (!grailsApplication) {
            grailsApplication = applicationContext.getBean("grailsApplication")
        }

        config = grailsApplication.config

        this.warDeployed = grailsApplication.isWarDeployed()



        def pageClassName = config?.grails?.artisteer.pageClassName ?: "se.webinventions.plugins.artisteer.Page"
        pageDomainClass = grailsApplication.getClassForName(pageClassName)


        def postClassName = config?.grails?.artisteer.postClassName ?: "se.webinventions.plugins.artisteer.Post"
        articleDomainClass = grailsApplication.getClassForName(postClassName)


    }

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [pageInstanceList: pageDomainClass.list(params), pageInstanceTotal: pageDomainClass.count()]
  }

  def create = {
    def pageInstance =  pageDomainClass.newInstance()
    pageInstance.properties = params
    return [pageInstance: pageInstance]
  }

  def save = {
    def pageInstance =  pageDomainClass.newInstance(params)
 

    println "filename is " + f.getOriginalFilename()
    pageInstance.name = f.getOriginalFilename().replaceAll(".zip", "");

    if (pageInstance.validate()) {
      pageInstance.save(flush: true);
      if (pageService.deflateZipFrompage(pageInstance, true)) {

        if(pageService.generateGspForpage(pageInstance, true)) {
         flash.message = "${message(code: 'default.created.message', args: [message(code: 'page.label', default: 'page'), pageInstance.id])}"


        redirect(action: "show", id: pageInstance.id)
        } else {
        pageInstance.errors.rejectValue("zip","Could not generate page files from zip")
        render(view: "create", model: [pageInstance: pageInstance])
      }

      }
      else {
        pageInstance.errors.rejectValue("zip","Could not unzip file to temp directory ")
        render(view: "create", model: [pageInstance: pageInstance])
      }
    }

    else {
      render(view: "create", model: [pageInstance: pageInstance])
    }
  }

  def show = {
    def pageInstance = pageDomainClass.get(params.id)
    if (!pageInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'page'), params.id])}"
      redirect(action: "list")
    }
    else {

      [pageInstance: pageInstance]
    }
  }
      //view is different from show in the sense that it actually 'views' the page, wheres show only displays
     def view = {
    def pageInstance = pageDomainClass.get(params.id)
    if (!pageInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'page'), params.id])}"

    }
    else {

      [pageInstance: pageInstance]
    }
  }

  

  def delete = {
    def pageInstance = pageDomainClass.get(params.id)
    if (pageInstance) {
      try {
        pageInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'page.label', default: 'page'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'page.label', default: 'page'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'page'), params.id])}"
      redirect(action: "list")
    }
  }


}
