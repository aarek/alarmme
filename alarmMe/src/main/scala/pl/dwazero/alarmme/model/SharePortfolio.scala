package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.{Full}

class SharePortfolio extends LongKeyedMapper[SharePortfolio] with IdPK {
  def getSingleton = SharePortfolio
  
  object user extends MappedLongForeignKey(this, User) {
    override def dbNotNull_? = true
    override def validSelectValues = Full(User.findAll.map{u:User => (u.id.is, u.email.is)})
  }
  object name extends MappedPoliteString(this, 128) {
    override def displayName = "Nazwa portfela"
  }
  object description extends MappedTextarea(this, 8192)
}

object SharePortfolio extends SharePortfolio 
  with LongKeyedMetaMapper[SharePortfolio] 
  with LongCRUDify[SharePortfolio]
  with CreatedUpdated
