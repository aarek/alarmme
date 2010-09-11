package pl.dwazero.alarmme.model

import java.util.{Date}
import _root_.net.liftweb.mapper._

class ExchangeRate extends LongKeyedMapper[ExchangeRate] with IdPK {
  def getSingleton = ExchangeRate
  
  object check_time extends MappedDateTime(this) {
    override def defaultValue = new Date
  }
  object company extends MappedLongForeignKey(this, Company) {
    override def dbNotNull_? = true
    override def dbIndexed_? = true
    override def displayName = "Spółka"
  }
  object exchange extends MappedInt(this) {
    override def defaultValue = 0
    override def displayName = "Kurs"
  }
}

object ExchangeRate extends ExchangeRate 
  with LongKeyedMetaMapper[ExchangeRate] 
  with LongCRUDify[ExchangeRate]
  with CreatedUpdated
