package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._

class ExchangeRate extends LongKeyedMapper[ExchangeRate] with IdPK {
    def getSingleton = ExchangeRate
    
    object check_time extends MappedDateTime(this)
    object company extends MappedLongForeignKey(this, Company)
    object exchange extends MappedInt(this)
}

object ExchangeRate extends ExchangeRate with LongKeyedMetaMapper[ExchangeRate]
