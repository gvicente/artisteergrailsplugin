

package se.webinventions.plugins.artisteer

/**
* Keeps track of vertical blocks, their order and such, visible on every page
**/
class VerticalBlock {

     String content = ""
     BlockType type = BlockType.NORMAL //if its a vertical menue then the menu items are outputed after the content
     Boolean published = true
     String visibleToRoles = "*"
     Integer column = 2 //does this block belong to column 1 2 or 3?? if the template only has one column the blocks wont be shown

     String content = ""
     Boolean published = true
     String visibleToRoles = "*"


    static constraints = {

      content(nullable:false, blank:true)

    }

}

public enum BlockType {
  NORMAL (0),
   MENU (1);


 final Integer id
  BlockType(Integer id) {
    this.id=id
  }

}

