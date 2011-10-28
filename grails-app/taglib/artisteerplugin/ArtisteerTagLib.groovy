package artisteerplugin

import se.webinventions.plugins.artisteer.Template

class ArtisteerTagLib {

    static namespace = "artisteer"

    def templateService

    def applyArtisteerLayout = { attrs, body ->
        Template template = templateService.getToBeAppliedTemplate()
        if (template) {
            attrs.name = "$template.name"
            out << g.applyLayout(attrs, body)
        } else {
            out << body()
        }
    }
}


