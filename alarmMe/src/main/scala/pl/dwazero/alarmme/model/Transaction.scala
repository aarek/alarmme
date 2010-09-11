package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._

class Transaction extends LongKeyedMapper[Transaction] with IdPK {
  def getSingleton = Transaction
  
  object user extends MappedLongForeignKey(this, User) {
    override def dbNotNull_? = true
  }
  object company extends MappedLongForeignKey(this, Company) {
    override def dbNotNull_? = true
  }
  object volume extends MappedInt(this) {
    override def defaultValue = 0
    override def displayName = "Wolumen" // aka quantity
  }
  object unit_price extends MappedInt(this) {
    override def defaultValue = 0
  }
}

object Transaction extends Transaction 
  with LongKeyedMetaMapper[Transaction] 
  with LongCRUDify[Transaction] {
    override def displayName = "Transakcje"
  }
  with CreatedUpdated