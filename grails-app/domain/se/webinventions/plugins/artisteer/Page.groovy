
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


