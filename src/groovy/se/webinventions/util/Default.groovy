package se.webinventions.util

/**
 * Created by IntelliJ IDEA.
 * User: kushal
 * Date: 10/26/11
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
class Default {

    public static final String defaultFooter = """
    <div class="art-footer">
        <div class="art-footer-t"></div>

        <div class="art-footer-body">
            <div class="art-footer-center">
                <div class="art-footer-wrapper">
                    <div class="art-footer-text">

                        <center>
                            <div style="width: 870px; text-align: left;">
                                <ul>
                                    <li style="display: block;float: left;margin-right: 30px;width: 200px;">
                                        <h5 style="text-align: left;"><span style="font-size: 18px;">Information</span>
                                        </h5>
                                        <ul>
                                            <li><a href="#">Welcome</a></li>
                                            <li><a href="#">Organization</a></li>
                                            <li><a href="#">Schedule</a></li>
                                            <li><a href="#">People</a></li>
                                            <li><a href="#">Management</a></li>
                                        </ul>
                                    </li>
                                    <li style="display: block;float: left;margin-right: 30px;width: 200px;">
                                        <h5><span style="font-size: 18px;">Location</span></h5>
                                        <ul>
                                            <li><a href="#">Area Map</a></li>
                                            <li><a href="#">Address</a></li>
                                            <li><a href="#">Contact Us</a></li>
                                        </ul>
                                    </li>
                                    <li style="display: block;float: left;margin-right: 30px;width: 150px;">
                                        <h5><span style="font-size: 18px;">Company</span></h5>
                                        <ul>
                                            <li><a href="#">About Us</a></li>
                                            <li><a href="#">Terms</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>

                            <p style="text-align: left;"><span
                                    style="font-family: 'Trebuchet MS', Arial, Helvetica, sans-serif; font-size: 18px; font-weight: bold;"><br/>
                            </span></p>

                            <p><a href="#"><img width="32" height="32" alt="" src="./images/rss_32.png"
                                                style="margin-right: 10px;"/></a><a href="#"><img width="32" height="32"
                                                                                                  alt=""
                                                                                                  src="./images/facebook_32.png"
                                                                                                  style="margin-right: 10px;"/>
                            </a><a href="#"><img width="32" height="32" alt="" src="./images/twitter_32.png"
                                                 style="margin-right: 10px;"/></a></p>

                            <p><br/><br/><br/><a href="#"></a>Copyright © 2011 All Rights Reserved.</p>

                            <p>vector graphics by <a href="http://www.bazaardesigns.com">www.bazaardesigns.com</a></p>

                            <p><br/></p>

                            <p>                </p>

                            <p style="text-align: right;"><br/></p>
                        </center>


                        <div class="cleared"></div>

                        <p class="art-page-footer"><a
                                href="http://www.artisteer.com/?p=website_templates">Website Template</a> created with Artisteer.
                        </p>
                    </div>
                </div>
            </div>

            <div class="cleared"></div>
        </div>
    </div>
    """

    public static final String defaultMainLayout = """
<artisteer:applyArtisteerLayout>
    <html>
    <head>
        <g:layoutHead/>
    </head>

    <body>
        <g:layoutBody/>
    </body>
    </html>
</artisteer:applyArtisteerLayout>
"""
}
