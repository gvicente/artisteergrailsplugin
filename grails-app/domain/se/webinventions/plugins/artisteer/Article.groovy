
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


