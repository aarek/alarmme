package pl.dwazero.alarmme.snippet

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.widgets.menu.{MenuWidget, MenuStyle}

class Menubar {
  def render (xhtml : NodeSeq) = MenuWidget(MenuStyle.VERTICAL)
}
