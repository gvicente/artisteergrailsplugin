import org.codehaus.groovy.grails.commons.ApplicationHolder
import se.webinventions.util.BeanBagUser
import se.webinventions.util.SafePathAppender
import se.webinventions.plugins.artisteer.TemplateService

class ArtisteerGrailsPlugin implements BeanBagUser {

    def version = "1.4.1"
    def grailsVersion = "1.3.4 > *"
    def dependsOn = [:]
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Per Sundberg, Kushal Likhi"
    def authorEmail = "contact@webinventions.se"
    def title = "Artisteer plugin"
    def description = '''\\
Plugin that provides a simple script for unpacking artisteer zip file templates to grails. The plugin automatically
unzips the template, creates a corresponding gsp layout file which links to an unpacked version of the artisteer template.
The gsp contains applyLayout links to other layouts that the user can customize which includes footer, title, and menurendering.
Examples of tags that can be used can be found under each corresponding layout file.
The plugin also provides a controller which makes it possible to upload templates on the fly with a controller.
The plugin is however at this stage only meant to work offline to import templates from artisteer. It works with artisteer 2.5,
and haven't been tried with artisteer 3.0. Please find the sources on github: https://github.com/webinventions/artisteergrailsplugin
'''

    def documentation = "http://grails.org/plugin/artisteerplugin"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        application.serviceClasses.each {service ->
            if (service.getStaticPropertyValue("artisteerTemplateProvider", Boolean.class)) {
                TemplateService.handlerBean = applicationContext.getBean(service.getName().replaceFirst(/^\w/, service.getName().charAt(0).toLowerCase().toString()) + "Service")
            }
        }
        def grailsApplication = applicationContext.getBean("grailsApplication")
        TemplateService templateService = applicationContext.getBean("templateService")
        boolean warDeployed = grailsApplication.isWarDeployed()
        String basePath
        if (warDeployed) {
            basePath = ApplicationHolder.application.parentContext.servletContext.getRealPath('WEB-INF/grails-app/artisteer/')
        } else {
            basePath = 'grails-app/artisteer/'
        }
        SafePathAppender safePathAppender = new SafePathAppender(basePath)

        storeInBeanBag "baseDir", basePath
        storeInBeanBag "zipDir", safePathAppender.append("zip/")
        storeInBeanBag "targetDir", safePathAppender.append(".target/")
        storeInBeanBag "webAppDir", applicationContext.getResourceByPath("/").getFile().getAbsolutePath()
        storeInBeanBag "rootAppDir", new SafePathAppender(applicationContext.getResourceByPath("/").getFile().getAbsolutePath()).append("../")

        println "[ARTISTEER PLUGIN] - Preparing Templates..."
        templateService.afterPropertiesSet()
        templateService.unzipAllTemplatesInZipDir()
        println "[ARTISTEER PLUGIN] - Templates Processing Compete"

    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
