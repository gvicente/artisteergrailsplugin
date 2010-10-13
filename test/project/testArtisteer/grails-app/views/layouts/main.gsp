<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd>"
<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:g="http://grails.codehaus.org/tags" xmlns:nav="http://www.grails.org/plugin/navigation" dir="ltr" xml:lang="en" lang="en-US">
  <head>
    <link rel="stylesheet" href="http://localhost:8080/testArtisteer/css/main.css"/>
    <link rel="stylesheet" href="http://localhost:8080/testArtisteer/artisteertemplates/main/style.css" type="text/css"/>
    <!--[if IE 6]><link rel='stylesheet' href='http://localhost:8080/testArtisteer/artisteertemplates/main/style.ie6.css' type='text/css'/><![endif]-->
    <!--[if IE 7]><link rel='stylesheet' href='http://localhost:8080/testArtisteer/artisteertemplates/main/style.ie7.css' type='text/css'/><![endif]-->
    <nav:resources override="true"/>
    <g:javascript library="application"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="http://localhost:8080/testArtisteer/artisteertemplates/main/script.js"></script>
    <g:layoutHead/>
  </head>
  <BODY>
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
          <div class="art-content-layout">
            <g:layoutBody/>
          </div>
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
