
<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:g="http://grails.codehaus.org/tags" xmlns:nav="http://www.grails.org/plugin/navigation" dir="ltr" xml:lang="en" lang="en-US">
  <head>
    <link rel="stylesheet" href="../../../web-app/artisteertemplates\testArtisteer1\style.css" type="text/css"/>
    <!--[if IE 6]><link rel='stylesheet' href='../../../web-app/artisteertemplates\testArtisteer1\style.ie6.css' type='text/css'/><![endif]-->
    <!--[if IE 7]><link rel='stylesheet' href='../../../web-app/artisteertemplates\testArtisteer1\style.ie7.css' type='text/css'/><![endif]-->
    <nav:resources override="true"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="http://main.js"/>
    <g:layoutHead/>
  </head>
  <test id="12" class="testclass"/>
  <BODY>
    <DIV id="art-page-background-simple-gradient">
      <DIV id="art-page-background-gradient"/>
    </DIV>
    <DIV id="art-main">
      <DIV class="art-sheet">
        <DIV class="art-sheet-tl"/>
        <DIV class="art-sheet-tr"/>
        <DIV class="art-sheet-bl"/>
        <DIV class="art-sheet-br"/>
        <DIV class="art-sheet-tc"/>
        <DIV class="art-sheet-bc"/>
        <DIV class="art-sheet-cl"/>
        <DIV class="art-sheet-cr"/>
        <DIV class="art-sheet-cc"/>
        <DIV class="art-sheet-body">
          <DIV class="art-nav">
            <DIV class="l"/>
            <DIV class="r"/>
            <g:applyLayout name="artisteerNavMenu"/>
          </DIV>
          <DIV class="art-header">
            <DIV class="art-header-jpeg"/>
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
          <DIV class="cleared"/>
          <DIV class="art-footer">
            <DIV class="art-footer-t"/>
            <DIV class="art-footer-b"/>
            <DIV class="art-footer-body">
              <A title="RSS" class="art-rss-tag-icon" href="#"/>
              <div class="art-footer-text">
                <g:applyLayout name="artisteerFooterText"/>
              </div>
              <DIV class="cleared"/>
            </DIV>
          </DIV>
          <DIV class="cleared"/>
        </DIV>
      </DIV>
      <DIV class="cleared"/>
      <p class="art-page-footer">
        <g:applyLayout name="artisteerPageFooterLink"/>
      </p>
    </DIV>
  </BODY>
</HTML>
