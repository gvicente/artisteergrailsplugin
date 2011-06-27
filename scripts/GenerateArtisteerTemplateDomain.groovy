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


     new File("grails-app/domain/se/webinventions/plugins/artisteer/Page.groovy").write('''

package se.webinventions.plugins.artisteer

class Page {


    static hasMany = [articles: Article, subPages: Page] //posts only for blog type of pages,subpages will become menuitems

  //page has  only 'articles' as content

    Boolean published = true
    String visibleToRoles = "*"
    Boolean visibleInVerticalMenu = true
    Boolean visibleInHorizontalMenu = true


    static constraints = {

    }


}





''')





     new File("grails-app/domain/se/webinventions/plugins/artisteer/Article.groovy").write('''
package se.webinventions.plugins.artisteer

class Article {

    static belongsTo = [page: Page]

    String content
    Boolean published = true
    String visibleToRoles = "*"


    static constraints = {

        content(nullable: false, blank: true)

    }

}


''')


 new File("grails-app/domain/se/webinventions/plugins/artisteer/VerticalBlock.groovy").write('''

package se.webinventions.plugins.artisteer

/**
* Keeps track of vertical blocks, their order and such, visible on every page
**/
class VerticalBlock {

     String content = ""
     BlockType type = BlockType.NORMAL //if its a vertical menue then the menu items are outputed after the content
     Boolean published = true
     String visibleToRoles = "*"
     Integer column = 2 //does this block belong to column 1 2 or 3?? if the template only has one column the blocks wont be shown

     String content = ""
     Boolean published = true
     String visibleToRoles = "*"


    static constraints = {

      content(nullable:false, blank:true)

    }

}

public enum BlockType {
  NORMAL (0),
   MENU (1);


 final Integer id
  BlockType(Integer id) {
    this.id=id
  }

}

''')






}

setDefaultTarget(main)

