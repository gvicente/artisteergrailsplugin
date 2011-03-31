import se.webinventions.plugins.artisteer.TemplateService
import grails.util.GrailsUtil

/**
 * Created by IntelliJ IDEA.
 * User: per
 * Date: 2010-okt-07
 * Time: 11:29:49
 * To change this template use File | Settings | File Templates.
 */
class ArtisteerBootStrap {

	def templateService
	def init = { servletContext ->
		switch (GrailsUtil.environment) {
			case "development":


			//              println "init bootstrap for artisteer template plugin"
				templateService.checkAndCreateInitialDirs();

				templateService.generateInitialTemplates();
				break;
		}
	}
	def destroy = {
	}
}
