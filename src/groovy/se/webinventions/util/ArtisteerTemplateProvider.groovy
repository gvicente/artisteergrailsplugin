package se.webinventions.util

import se.webinventions.plugins.artisteer.Template

/**
 * Created by IntelliJ IDEA.
 * User: kushal
 * Date: 10/26/11
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ArtisteerTemplateProvider {

    public static artisteerTemplateProvider = true

    public Template getArtisteerTemplate()

}