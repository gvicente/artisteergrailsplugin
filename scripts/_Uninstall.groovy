//
// This script is executed by Grails when the plugin is uninstalled from project.
// Use this script if you intend to do any additional clean-up on uninstall, but
// beware of messing up SVN directories!
//


ant.delete(dir: "${basedir}/grails-app/artisteer/.target")

println "[ARTISTEER PLUGIN - UNINSTALL PROCESS] Removed Target Directory"
