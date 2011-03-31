includeTargets << grailsScript("Init")

target(main: "This will create the default domain classes that maps tenants towards servernames") {
  def ant = new AntBuilder();


  ant.mkdir(dir: 'grails-app/domain/se/webinventions/plugins/artisteer')
  new File("grails-app/domain/se/webinventions/plugins/artisteer/Template.groovy").write('''

package se.webinventions.plugins.artisteer

class Template {

     String name
     byte[] zip


    static constraints = {

      name(nullable:false, blank:false, editable:false, unique:true)
      zip(nullable:false,maxSize:1024*1000*5)
    }

  //just for nosql dbs...
   static mapping = {
     name index:true

   }
}

''')



}

setDefaultTarget(main)

