
ant.mkdir(dir: "${basedir}/grails-app/artisteer")
ant.mkdir(dir: "${basedir}/grails-app/artisteer/zip")
ant.mkdir(dir: "${basedir}/grails-app/artisteer/.target")

println "[ARTISTEER PLUGIN - INSTALL PROCESS] Created Directories"

String to = "${basedir}/web-app/images/footer"

String from = "${pluginBasedir}/web-app/images/footer"

println "[ARTISTEER PLUGIN - INSTALL PROCESS] Copying Footer Files"

ant.copy(toDir: to, filtering: true, overwrite: true) {
    fileset(dir: from)
}

to = "${basedir}/web-app/css/footer"

from = "${pluginBasedir}/web-app/css/footer"


ant.copy(toDir: to, filtering: true, overwrite: true) {
    fileset(dir: from)
}

println "[ARTISTEER PLUGIN - INSTALL PROCESS] Copied Files"
