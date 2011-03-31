
import org.apache.tools.ant.taskdefs.Ant


includeTargets << grailsScript("_GrailsClasspath")
includeTargets << grailsScript("_GrailsBootstrap")
includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << grailsScript("_GrailsSettings")


  //create a template but dont save it and use it to generate the gsp file .
target('default': "This will create a gps template based on an exported html style zip file from artisteer") {
   depends( parseArguments , configureProxy, packageApp, classpath )



  grails.util.BuildSettings settngs = grailsSettings;





  Class clazzTi = classLoader.loadClass('se.webinventions.plugins.artisteer.TemplateService')
  Class clazzT = classLoader.loadClass('se.webinventions.plugins.artisteer.Template')


  def templateService = clazzTi.newInstance()

  templateService?.afterPropertiesSet();

  List inArgs = argsMap?.params

  if(inArgs.size()!=1) {
    println "ERROR: you must provide one argument to the zip file exported from Artisteer"
    return;
  }

  File zipFile = new File(inArgs.get(0));

  if(!zipFile.isFile()) {
    println "ERROR: not a valid file: " +zipFile;
  }

  def filename = zipFile.getName()

  def templateName = filename.substring(0,filename.indexOf(".zip"))



 def  templateInstance = clazzT.newInstance(name:templateName, zip:zipFile.getBytes());



  def webAppdir = "$settngs.baseDir"+File.separator+"web-app"+File.separator

  if (templateService.deflateZipFromTemplate(templateInstance, true,webAppdir)) {

        if(templateService.generateGspForTemplate(templateInstance, true,webAppdir)) {

          println "template successfully created! Refer to your layouts directory"
          return

        } else {
        println " Error: Could not generate template files from zip"
        return
      }

      }
      else {
        println "Error could not unzip file to temp directory"
      }


}