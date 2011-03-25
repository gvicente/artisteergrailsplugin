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
