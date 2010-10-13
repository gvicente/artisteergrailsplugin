package artisteerplugin

class ArtisteerTagLib {

  static namespace = "artisteer"


  /**
  *
   * BEGIN NAVIGATION TAGS..
   */
  def renderTopMenu = {attrs, body ->

    out <<   """ <ul class="art-menu">
            <div class="l"></div>
            <div class="r"></div>
            """

      out<<body()

    out<<"</ul>"
  }

  def topNavListItem = { attrs, body ->
    def href = attrs?.href ?: "#"
    def title = attrs?.title ?: "specify title in tag!"
    def activemenu = attrs?.active ?:false
    out<<"<li>"
    if(activemenu) {
     out<<'<a class="active"'
    }
    else {
      out<<'<a'
    }
    out<<' href="'+href+'">'

    out<< """ <span class="l"></span><span class="r"></span><span class="t">${title}</span></a> """

    out<< body();
    out<< "</li>"

  }
  def renderSubMenu = {attrs, body ->
    out << """ <ul>
    """
    out<< body()
    out<< """
    </ul>
    """
  }
  def subNavListItem = {  attrs, body ->
    def href = attrs?.href ?: "#"
    def title = attrs?.title ?:"specify title in tag!"
    out<<"""<li><a href="${href}">${title}</a> """
    out<<body()
    out<<"</li>"
    
         
  }

  //VERTICAL VARIANT..
  def renderVerticalTopMenu = {attrs, body ->

    out <<   """ <ul class="art-vmenu">
            
            """

      out<<body()

    out<<"</ul>"
  }

  def topVerticalNavListItem = { attrs, body ->
    def href = attrs?.href ?: "#"
    def title = attrs?.title ?: "specify title in tag!"
    def activemenu = attrs?.active ?:false

    if(activemenu) {
      out<<'<li class="active">'
     out<<'<a class="active"'
    }
    else {
      out<<"<li>"
      out<<'<a'
    }
    out<<' href="'+href+'">'

    out<< """ <span class="l"></span><span class="r"></span><span class="t">${title}</span></a> """

    out<< body();
    out<< "</li>"

  }
  def renderVerticalSubMenu = {attrs, body ->


       def active = attrs?.active ?:false

       if(active) {
         out<<'<ul class="active">'

       }
       else {
         out<<"<ul>"

       }

    out<< body()
    out<< """
    </ul>
    """
  }
  def subVerticalNavListItem = {  attrs, body ->
    def href = attrs?.href ?: "#"
    def title = attrs?.title ?:"specify title in tag!"
    out<<"""<li><a href="${href}">${title}</a> """
    out<<body()
    out<<"</li>"


  }

}


