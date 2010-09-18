package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.sitemap.{Menu, Loc}
import _root_.net.liftweb.http.{RedirectResponse}
import Loc.{If}

class Company extends LongKeyedMapper[Company] with IdPK {
  def getSingleton = Company
  
  object name extends MappedPoliteString(this, 128)
  object code extends MappedPoliteString(this, 16)
}

object Company extends Company
  with LongKeyedMetaMapper[Company]
  with LongCRUDify[Company]
  with CreatedUpdated {
    def menu(prefix: List[String], isLoggedIn: If): Menu = {
      val submenus: List[Menu] = List(
        Menu(Loc("addCompany", prefix ::: "add" :: Nil, "Dodaj"))
        )
      return Menu(Loc("indexCompany", prefix ::: "index" :: Nil, "Firmy", isLoggedIn), submenus :_*)
    }
  }
