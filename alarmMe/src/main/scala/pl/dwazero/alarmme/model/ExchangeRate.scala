package pl.dwazero.alarmme.model

import java.util.{Date}
import _root_.net.liftweb.mapper._

class ExchangeRate extends LongKeyedMapper[ExchangeRate] with IdPK {
    def getSingleton = ExchangeRate
    
    object check_time extends MappedDateTime(this) {
      override def defaultValue = new Date
    }
    object company extends MappedLongForeignKey(this, Company)
    object exchange extends MappedInt(this)
}

object ExchangeRate extends ExchangeRate 
  with LongKeyedMetaMapper[ExchangeRate] 
  with LongCRUDify[ExchangeRate]
