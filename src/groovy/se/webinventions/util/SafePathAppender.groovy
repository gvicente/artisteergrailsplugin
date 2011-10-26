package se.webinventions.util

/**
 * Created by IntelliJ IDEA.
 * User: kushal
 * Date: 10/25/11
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
class SafePathAppender {

    String path

    public SafePathAppender(String path) {
        this.path = path
        if (!this.path.endsWith("/")) {
            this.path = this.path + "/"
        }
    }

    public String append(String append) {
        if (append.startsWith("/")) {
            append = append.replaceFirst("/", "")
        }
        "${this.path}${append}"
    }


}
