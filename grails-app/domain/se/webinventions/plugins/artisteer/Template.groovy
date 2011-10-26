package se.webinventions.plugins.artisteer

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import se.webinventions.util.SafePathAppender
import se.webinventions.util.BeanBag

class Template {


    String name
    String category = "General"
    byte[] zip

    static transients = ['previewImage', 'previewUrl']

    static constraints = {
        name(nullable: false, blank: false, editable: false, unique: true)
        zip(nullable: false, maxSize: 1024 * 1000 * 5)
    }

    static mapping = {
        name index: true
    }

    public String getPreviewUrl() {
        return (ConfigurationHolder.config.grails.serverURL + "/template/preview/" + this.id)
    }

    public Expando getPreviewImage() {
        File previewImageFile = new File(new SafePathAppender(BeanBag.fetch("targetDir").toString()).append("${this.name}/images/preview.jpg"))
        return new Expando(
                image: previewImageFile.getBytes(),
                type: "image/jpeg"
        )
    }
}

