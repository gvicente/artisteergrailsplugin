<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd>"
<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:g="http://grails.codehaus.org/tags" xmlns:nav="http://www.grails.org/plugin/navigation" dir="ltr" xml:lang="en" lang="en-US">
  <head>
    <link rel="stylesheet" href="http://localhost:8080/testArtisteer/css/main.css"/>
    <link rel="stylesheet" href="http://localhost:8080/testArtisteer/artisteertemplates/testsidebars/style.css" type="text/css"/>
    <!--[if IE 6]><link rel='stylesheet' href='http://localhost:8080/testArtisteer/artisteertemplates/testsidebars/style.ie6.css' type='text/css'/><![endif]-->
    <!--[if IE 7]><link rel='stylesheet' href='http://localhost:8080/testArtisteer/artisteertemplates/testsidebars/style.ie7.css' type='text/css'/><![endif]-->
    <nav:resources override="true"/>
    <g:javascript library="application"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="http://localhost:8080/testArtisteer/artisteertemplates/testsidebars/script.js"></script>
    <g:layoutHead/>
  </head>
  <BODY>
    <DIV id="art-page-background-simple-gradient">
      <DIV id="art-page-background-gradient"></DIV>
    </DIV>
    <DIV id="art-main">
      <DIV class="art-sheet">
        <DIV class="art-sheet-tl"></DIV>
        <DIV class="art-sheet-tr"></DIV>
        <DIV class="art-sheet-bl"></DIV>
        <DIV class="art-sheet-br"></DIV>
        <DIV class="art-sheet-tc"></DIV>
        <DIV class="art-sheet-bc"></DIV>
        <DIV class="art-sheet-cl"></DIV>
        <DIV class="art-sheet-cr"></DIV>
        <DIV class="art-sheet-cc"></DIV>
        <DIV class="art-sheet-body">
          <DIV class="art-nav">
            <DIV class="l"></DIV>
            <DIV class="r"></DIV>
            <g:applyLayout name="artisteerNavMenu"/>
          </DIV>
          <DIV class="art-header">
            <DIV class="art-header-jpeg"></DIV>
            <DIV class="art-logo">
              <H1 id="name-text" class="art-logo-name">
                <g:applyLayout name="artisteerLogoName"/>
              </H1>
              <DIV id="slogan-text" class="art-logo-text">
                <g:applyLayout name="artisteerLogoText"/>
              </DIV>
            </DIV>
          </DIV>
          <DIV class="art-content-layout">
            <DIV class="art-content-layout-row">
              <DIV class="art-layout-cell art-content">
                <DIV class="art-post">
                  <DIV class="art-post-body">
                    <DIV class="art-post-inner art-article">
                      <H2 class="art-postheader">
                                                Welcome
                                            </H2>
                      <div class="art-postcontent">
                        <g:layoutBody/>
                      </div>
                      <DIV class="cleared"></DIV>
                    </DIV>
                    <DIV class="cleared"></DIV>
                  </DIV>
                </DIV>
                <DIV class="art-post">
                  <DIV class="art-post-body">
                    <DIV class="art-post-inner art-article">
                      <H2 class="art-postheader">
                                                Text, <A title="Permanent Link to this Post" rel="bookmark" href="#">Link</A>, <A title="Visited Hyperlink" class="visited" rel="bookmark" href="#">Visited</A>, <A title="Hovered Hyperlink" class="hovered" rel="bookmark" href="#">Hovered</A>
                      </H2>
                      <DIV class="art-postcontent">
                        <P>Lorem <SUP>superscript</SUP> dolor <SUB>subscript</SUB> amet, consectetuer adipiscing elit, <A title="test link" href="#">test link</A>.
                                                	Nullam dignissim convallis est. Quisque aliquam. <CITE>cite</CITE>.
                                                	Nunc iaculis suscipit dui. Nam sit amet sem. Aliquam libero nisi, imperdiet at, tincidunt nec, gravida vehicula, nisl.
                                                	Praesent mattis, massa quis luctus fermentum, turpis mi volutpat justo, eu volutpat enim diam eget metus.
                                                	Maecenas ornare tortor. Donec sed tellus eget sapien fringilla nonummy. <ACRONYM title="National Basketball Association">NBA</ACRONYM> Mauris a ante.
                                                	Suspendisse quam sem, consequat at, commodo vitae, feugiat in, nunc.
                                                	Morbi imperdiet augue quis tellus.  <ABBR title="Avenue">AVE</ABBR>
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
                            <A class="art-button" href="javascript:void(0)">Join Now!</A>
                          </SPAN>
                        </P>
                      </DIV>
                      <DIV class="cleared"></DIV>
                    </DIV>
                    <DIV class="cleared"></DIV>
                  </DIV>
                </DIV>
              </DIV>
              <DIV class="art-layout-cell art-sidebar1">
                <DIV class="art-vmenublock">
                  <DIV class="art-vmenublock-tl"></DIV>
                  <DIV class="art-vmenublock-tr"></DIV>
                  <DIV class="art-vmenublock-bl"></DIV>
                  <DIV class="art-vmenublock-br"></DIV>
                  <DIV class="art-vmenublock-tc"></DIV>
                  <DIV class="art-vmenublock-bc"></DIV>
                  <DIV class="art-vmenublock-cl"></DIV>
                  <DIV class="art-vmenublock-cr"></DIV>
                  <DIV class="art-vmenublock-cc"></DIV>
                  <DIV class="art-vmenublock-body">
                    <DIV class="art-vmenublockheader">
                      <DIV class="t">Navigation</DIV>
                    </DIV>
                    <DIV class="art-vmenublockcontent">
                      <g:applyLayout name="artisteerVerticalNavMenu"/>
                    </DIV>
                    <DIV class="cleared"></DIV>
                  </DIV>
                </DIV>
                <DIV class="art-block">
                  <DIV class="art-block-tl"></DIV>
                  <DIV class="art-block-tr"></DIV>
                  <DIV class="art-block-bl"></DIV>
                  <DIV class="art-block-br"></DIV>
                  <DIV class="art-block-tc"></DIV>
                  <DIV class="art-block-bc"></DIV>
                  <DIV class="art-block-cl"></DIV>
                  <DIV class="art-block-cr"></DIV>
                  <DIV class="art-block-cc"></DIV>
                  <DIV class="art-block-body">
                    <DIV class="art-blockheader">
                      <DIV class="t">Highlights</DIV>
                    </DIV>
                    <DIV class="art-blockcontent">
                      <DIV class="art-blockcontent-body">
                        <DIV>
                          <UL>
                            <LI>
                              <A href="#">Home</A>
                            </LI>
                            <LI>
                              <A href="#">Overview</A>
                            </LI>
                            <LI>
                              <A href="#">Demo</A>
                            </LI>
                            <LI>
                              <A href="#">Download</A>
                            </LI>
                            <LI>
                              <A href="#">FAQ</A>
                            </LI>
                            <LI>
                              <A href="#">Hyperlink</A>
                            </LI>
                            <LI>
                              <A class="visited" href="#">Visited link</A>
                            </LI>
                            <LI>
                              <A class="hover" href="#">Hovered link</A>
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
                            <A href="javascript:void(0)">Read more...</A>
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
                            <A href="javascript:void(0)">Read more...</A>
                          </P>
                        </DIV>
                        <DIV class="cleared"></DIV>
                      </DIV>
                    </DIV>
                    <DIV class="cleared"></DIV>
                  </DIV>
                </DIV>
              </DIV>
              <DIV class="art-layout-cell art-sidebar2">
                <DIV class="art-block">
                  <DIV class="art-block-tl"></DIV>
                  <DIV class="art-block-tr"></DIV>
                  <DIV class="art-block-bl"></DIV>
                  <DIV class="art-block-br"></DIV>
                  <DIV class="art-block-tc"></DIV>
                  <DIV class="art-block-bc"></DIV>
                  <DIV class="art-block-cl"></DIV>
                  <DIV class="art-block-cr"></DIV>
                  <DIV class="art-block-cc"></DIV>
                  <DIV class="art-block-body">
                    <DIV class="art-blockheader">
                      <DIV class="t">Newsletter</DIV>
                    </DIV>
                    <DIV class="art-blockcontent">
                      <DIV class="art-blockcontent-body">
                        <DIV>
                          <FORM id="newsletterform" action="javascript:void(0)" method="get">
                            <INPUT id="s" style="width: 95%;" name="email" value="" type="text"/>
                            <SPAN class="art-button-wrapper">
                              <SPAN class="l"/>
                              <SPAN class="r"/>
                              <INPUT name="search" value="Subscribe" class="art-button" type="submit"/>
                            </SPAN>
                          </FORM>
                        </DIV>
                        <DIV class="cleared"></DIV>
                      </DIV>
                    </DIV>
                    <DIV class="cleared"></DIV>
                  </DIV>
                </DIV>
                <DIV class="art-block">
                  <DIV class="art-block-tl"></DIV>
                  <DIV class="art-block-tr"></DIV>
                  <DIV class="art-block-bl"></DIV>
                  <DIV class="art-block-br"></DIV>
                  <DIV class="art-block-tc"></DIV>
                  <DIV class="art-block-bc"></DIV>
                  <DIV class="art-block-cl"></DIV>
                  <DIV class="art-block-cr"></DIV>
                  <DIV class="art-block-cc"></DIV>
                  <DIV class="art-block-body">
                    <DIV class="art-blockheader">
                      <DIV class="t">Contact Info</DIV>
                    </DIV>
                    <DIV class="art-blockcontent">
                      <DIV class="art-blockcontent-body">
                        <DIV>
                          <IMG style="margin: 0 auto;display:block;width:95%" alt="an image" src="images/contact.jpg"/>
                          <BR/>
                          <B>Company Co.</B>
                          <BR/>
                                                            Las Vegas, NV 12345<BR/>
                                                            Email: <A href="mailto:info@company.com">info@company.com</A>
                          <BR/>
                          <BR/>
                                                            Phone: (123) 456-7890 <BR/>
                                                            Fax: (123) 456-7890
                                                            </DIV>
                        <DIV class="cleared"></DIV>
                      </DIV>
                    </DIV>
                    <DIV class="cleared"></DIV>
                  </DIV>
                </DIV>
              </DIV>
            </DIV>
          </DIV>
          <DIV class="cleared"></DIV>
          <DIV class="art-footer">
            <DIV class="art-footer-t"></DIV>
            <DIV class="art-footer-l"></DIV>
            <DIV class="art-footer-b"></DIV>
            <DIV class="art-footer-r"></DIV>
            <DIV class="art-footer-body">
              <A title="RSS" class="art-rss-tag-icon" href="#"></a>
              <div class="art-footer-text">
                <g:applyLayout name="artisteerFooterText"/>
              </div>
              <DIV class="cleared"></DIV>
            </DIV>
          </DIV>
          <DIV class="cleared"></DIV>
        </DIV>
      </DIV>
      <DIV class="cleared"></DIV>
      <p class="art-page-footer">
        <g:applyLayout name="artisteerPageFooterLink"/>
      </p>
    </DIV>
  </BODY>
</HTML>
