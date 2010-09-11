package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.{Full}

class SharePortfolio extends LongKeyedMapper[SharePortfolio] with IdPK {
  def getSingleton = SharePortfolio
  
  object user extends MappedLongForeignKey(this, User) {
    override def dbNotNull_? = true
    override def dbIndexed_? = true // for: show all portfolios of user
    override def validSelectValues = Full(User.findAll.map{u:User => (u.id.is, u.email.is)})
  }
  object name extends MappedPoliteString(this, 128) {
    override def displayName = "Nazwa"
  }
  object description extends MappedTextarea(this, 8192)
  object is_public extends MappedBoolean(this) {
    override def defaultValue = false
    override def dbIndexed_? = true // for: show all public portfolios
  }
  
  def allTransactions : List[Transaction] = Transaction.findAll(By(Transaction.portfolio, this.id))
  
}

object SharePortfolio extends SharePortfolio 
  with LongKeyedMetaMapper[SharePortfolio]
  with LongCRUDify[SharePortfolio] 
  with CreatedUpdated {
    override def displayName = "Portfel"
  }
