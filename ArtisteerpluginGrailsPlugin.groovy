import se.webinventions.plugins.artisteer.Template
import se.webinventions.plugins.artisteer.TemplateService


class ArtisteerpluginGrailsPlugin {
  // the plugin version
  def version = "0.1"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.3.4 > *"
  // the other plugins this plugin depends on
  def dependsOn = [:]
  // resources that are excluded from plugin packaging
  def pluginExcludes = [
          "grails-app/views/error.gsp"
  ]

  // TODO Fill in these fields
  def author = "Per Sundberg"
  def authorEmail = "contact@webinventions.se"
  def title = "Artisteer plugin"
  def description = '''\\
Plugin that provides a simple controller for uploading artisteer zip file templates to grails. The plugin automatically
unzips the template, creates a corresponding gsp layout file which links to an unpacked version of the artisteer template.
The gsp contains links to other layouts that the user can customize (footer, title, menurendering). Footer and title etc is best to
take from db, but I left it up to the user to configure that.
'''


  // URL to the plugin's documentation
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
    // TODO Implement post initialization spring config (optional)

  
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
