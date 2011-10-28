eventCreateWarStart = {name, stagingDir ->

    String from = "${basedir}/grails-app/artisteer/"
    String to = "${stagingDir}/WEB-INF/grails-app/artisteer"

    println "[ARTISTEER PLUGIN] Copying Plugin Files From: ${from}"

    ant.copy(toDir: to, filtering: true, overwrite: true) {
        fileset(dir: from)
    }

    println "[ARTISTEER PLUGIN] Copied Files To: ${to}"

    println "[ARTISTEER PLUGIN] Deleting template files from web app."

    ant.delete(dir: "${stagingDir}/WEB-INF/web-app/css/templates")
    ant.delete(dir: "${stagingDir}/WEB-INF/web-app/js/templates")

    println "[ARTISTEER PLUGIN] Deleted Files."

}
