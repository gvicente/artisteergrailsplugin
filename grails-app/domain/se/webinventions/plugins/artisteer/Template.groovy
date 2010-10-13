package se.webinventions.plugins.artisteer

class Template {

     String name
     byte[] zip

    static constraints = {

      name(nullable:false, blank:false, editable:false, unique:true)
      zip(nullable:false)
    }
}
