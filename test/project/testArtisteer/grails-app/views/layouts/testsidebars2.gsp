<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd>"
<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:g="http://grails.codehaus.org/tags" xmlns:nav="http://www.grails.org/plugin/navigation" dir="ltr" xml:lang="en" lang="en-US">
  <head>
    <link rel="stylesheet" href="http://localhost:8080/testArtisteer/css/main.css"/>
    <link rel="stylesheet" href="http://localhost:8080/testArtisteer/artisteertemplates/testsidebars2/style.css" type="text/css"/>
    <!--[if IE 6]><link rel='stylesheet' href='http://localhost:8080/testArtisteer/artisteertemplates/testsidebars2/style.ie6.css' type='text/css'/><![endif]-->
    <!--[if IE 7]><link rel='stylesheet' href='http://localhost:8080/testArtisteer/artisteertemplates/testsidebars2/style.ie7.css' type='text/css'/><![endif]-->
    <nav:resources override="true"/>
    <g:javascript library="application"/>
    <title>
      <g:layoutTitle default="some title"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="http://localhost:8080/testArtisteer/artisteertemplates/testsidebars2/script.js"></script>
    <g:layoutHead/>
  </head>
  <body>
    <div id="art-main">
      <div class="art-sheet">
        <div class="art-sheet-tl"></div>
        <div class="art-sheet-tr"></div>
        <div class="art-sheet-bl"></div>
        <div class="art-sheet-br"></div>
        <div class="art-sheet-tc"></div>
        <div class="art-sheet-bc"></div>
        <div class="art-sheet-cl"></div>
        <div class="art-sheet-cr"></div>
        <div class="art-sheet-cc"></div>
        <div class="art-sheet-body">
          <div class="art-header">
            <div class="art-header-jpeg"></div>
            <div class="art-logo">
              <H1 id="name-text" class="art-logo-name">
                <g:applyLayout name="artisteerLogoName"/>
              </H1>
              <div id="slogan-text" class="art-logo-text">
                <g:applyLayout name="artisteerLogoText"/>
              </div>
            </div>
          </div>
          <div class="art-nav">
            <div class="l"></div>
            <div class="r"></div>
            <g:applyLayout name="artisteerNavMenu"/>
          </div>
          <div class="art-content-layout">
            <div class="art-content-layout-row">
              <div class="art-layout-cell art-content">
                <div class="art-post">
                  <div class="art-post-tl"></div>
                  <div class="art-post-tr"></div>
                  <div class="art-post-bl"></div>
                  <div class="art-post-br"></div>
                  <div class="art-post-tc"></div>
                  <div class="art-post-bc"></div>
                  <div class="art-post-cl"></div>
                  <div class="art-post-cr"></div>
                  <div class="art-post-cc"></div>
                  <div class="art-post-body">
                    <div class="art-post-inner art-article">
                      <H2 class="art-postheader">
                                                Welcome
                                            </H2>
                      <div class="art-postcontent">
                        <g:layoutBody/>
                      </div>
                      <div class="cleared"></div>
                    </div>
                    <div class="cleared"></div>
                  </div>
                </div>
                <div class="art-post">
                  <div class="art-post-tl"></div>
                  <div class="art-post-tr"></div>
                  <div class="art-post-bl"></div>
                  <div class="art-post-br"></div>
                  <div class="art-post-tc"></div>
                  <div class="art-post-bc"></div>
                  <div class="art-post-cl"></div>
                  <div class="art-post-cr"></div>
                  <div class="art-post-cc"></div>
                  <div class="art-post-body">
                    <div class="art-post-inner art-article">
                      <H2 class="art-postheader">
                                                Text, <a title="Permanent Link to this Post" rel="bookmark" href="#">Link</a>, <a title="Visited Hyperlink" class="visited" rel="bookmark" href="#">Visited</a>, <a title="Hovered Hyperlink" class="hovered" rel="bookmark" href="#">Hovered</a>
                      </H2>
                      <div class="art-postcontent">
                        <P>Lorem <SUP>superscript</SUP> dolor <SUB>subscript</SUB> amet, consectetuer adipiscing elit, <a title="test link" href="#">test link</a>.
                                                	Nullam dignissim convallis est. Quisque aliquam. <CITE>cite</CITE>.
                                                	Nunc iaculis suscipit dui. Nam sit amet sem. Aliquam libero nisi, imperdiet at, tincidunt nec, gravida vehicula, nisl.
                                                	Praesent mattis, massa quis luctus fermentum, turpis mi volutpat justo, eu volutpat enim diam eget metus.
                                                	Maecenas ornare tortor. Donec sed tellus eget sapien fringilla nonummy. <aCRONYM title="National Basketball Association">NBA</ACRONYM> Mauris a ante.
                                                	Suspendisse quam sem, consequat at, commodo vitae, feugiat in, nunc.
                                                	Morbi imperdiet augue quis tellus.  <aBBR title="Avenue">AVE</ABBR>
                        </P>
                        <H1>Heading 1</H1>
                        <H2>Heading 2</H2>
                        <H3>Heading 3</H3>
                        <H4>Heading 4</H4>
                        <H5>Heading 5</H5>
                        <H6>Heading 6</H6>
                        <BLOCKQUOTE>
                          <P>
                                                            “This stylesheet is going to help so freaking much.”
                                                            <BR/>
                                                            -Blockquote
                                                        </P>
                        </BLOCKQUOTE>
                        <BR/>
                        <TABLE cellpadding="0" class="art-article" border="0" cellspacing="0">
                          <TBODY>
                            <TR>
                              <TH>Header</TH>
                              <TH>Header</TH>
                              <TH>Header</TH>
                            </TR>
                            <TR>
                              <TD>Data</TD>
                              <TD>Data</TD>
                              <TD>Data</TD>
                            </TR>
                            <TR class="even">
                              <TD>Data</TD>
                              <TD>Data</TD>
                              <TD>Data</TD>
                            </TR>
                            <TR>
                              <TD>Data</TD>
                              <TD>Data</TD>
                              <TD>Data</TD>
                            </TR>
                          </TBODY>
                        </TABLE>
                        <P>
                          <SPAN class="art-button-wrapper">
                            <SPAN class="l"/>
                            <SPAN class="r"/>
                            <a class="art-button" href="javascript:void(0)">Join Now!</a>
                          </SPAN>
                        </P>
                      </div>
                      <div class="cleared"></div>
                    </div>
                    <div class="cleared"></div>
                  </div>
                </div>
              </div>
              <div class="art-layout-cell art-sidebar1">
                <div class="art-vmenublock">
                  <div class="art-vmenublock-body">
                    <div class="art-vmenublockheader">
                      <div class="t">Navigation</div>
                    </div>
                    <div class="art-vmenublockcontent">
                      <g:applyLayout name="artisteerVerticalNavMenu"/>
                    </div>
                    <div class="cleared"></div>
                  </div>
                </div>
                <div class="art-block">
                  <div class="art-block-body">
                    <div class="art-blockheader">
                      <div class="t">Newsletter</div>
                    </div>
                    <div class="art-blockcontent">
                      <div class="art-blockcontent-body">
                        <div>
                          <FORM id="newsletterform" action="javascript:void(0)" method="get">
                            <INPUT id="s" style="width: 95%;" name="email" value="" type="text"/>
                            <SPAN class="art-button-wrapper">
                              <SPAN class="l"/>
                              <SPAN class="r"/>
                              <INPUT name="search" value="Subscribe" class="art-button" type="submit"/>
                            </SPAN>
                          </FORM>
                        </div>
                        <div class="cleared"></div>
                      </div>
                    </div>
                    <div class="cleared"></div>
                  </div>
                </div>
                <div class="art-block">
                  <div class="art-block-body">
                    <div class="art-blockheader">
                      <div class="t">Highlights</div>
                    </div>
                    <div class="art-blockcontent">
                      <div class="art-blockcontent-body">
                        <div>
                          <UL>
                            <LI>
                              <a href="#">Home</a>
                            </LI>
                            <LI>
                              <a href="#">Overview</a>
                            </LI>
                            <LI>
                              <a href="#">Demo</a>
                            </LI>
                            <LI>
                              <a href="#">Download</a>
                            </LI>
                            <LI>
                              <a href="#">FAQ</a>
                            </LI>
                            <LI>
                              <a href="#">Hyperlink</a>
                            </LI>
                            <LI>
                              <a class="visited" href="#">Visited link</a>
                            </LI>
                            <LI>
                              <a class="hover" href="#">Hovered link</a>
                            </LI>
                          </UL>
                          <P>
                            <B>Jun 14, 2008</B>
                            <BR/>
                                                                              Aliquam sit amet felis. Mauris semper,
                                                                              velit semper laoreet dictum, quam
                                                                              diam dictum urna, nec placerat elit
                                                                              nisl in quam. Etiam augue pede,
                                                                              molestie eget, rhoncus at, convallis
                                                                              ut, eros. Aliquam pharetra.<BR/>
                            <a href="javascript:void(0)">Read more...</a>
                          </P>
                          <P>
                            <B>Aug 24, 2008</B>
                            <BR/>
                                                                              Aliquam sit amet felis. Mauris semper,
                                                                              velit semper laoreet dictum, quam
                                                                              diam dictum urna, nec placerat elit
                                                                              nisl in quam. Etiam augue pede,
                                                                              molestie eget, rhoncus at, convallis
                                                                              ut, eros. Aliquam pharetra.<BR/>
                            <a href="javascript:void(0)">Read more...</a>
                          </P>
                        </div>
                        <div class="cleared"></div>
                      </div>
                    </div>
                    <div class="cleared"></div>
                  </div>
                </div>
                <div class="art-block">
                  <div class="art-block-body">
                    <div class="art-blockheader">
                      <div class="t">Contact Info</div>
                    </div>
                    <div class="art-blockcontent">
                      <div class="art-blockcontent-body">
                        <div>
                          <IMG style="margin: 0 auto;display:block;width:95%" alt="an image" src="http://localhost:8080/testArtisteer/artisteertemplates/testsidebars2/images/contact.jpg"/>
                          <BR/>
                          <B>Company Co.</B>
                          <BR/>
                                                            Las Vegas, NV 12345<BR/>
                                                            Email: <a href="mailto:info@company.com">info@company.com</a>
                          <BR/>
                          <BR/>
                                                            Phone: (123) 456-7890 <BR/>
                                                            Fax: (123) 456-7890
                                                            </div>
                        <div class="cleared"></div>
                      </div>
                    </div>
                    <div class="cleared"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="cleared"></div>
          <div class="art-footer">
            <div class="art-footer-t"></div>
            <div class="art-footer-b"></div>
            <div class="art-footer-body">
              <a title="RSS" class="art-rss-tag-icon" href="#"></a>
              <div class="art-footer-text">
                <g:applyLayout name="artisteerFooterText"/>
              </div>
              <div class="cleared"></div>
            </div>
          </div>
          <div class="cleared"></div>
        </div>
      </div>
      <div class="cleared"></div>
      <p class="art-page-footer">
        <g:applyLayout name="artisteerPageFooterLink"/>
      </p>
    </div>
  </body>
</html>
