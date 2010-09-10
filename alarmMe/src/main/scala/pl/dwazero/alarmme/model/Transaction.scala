package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._

class Transaction extends LongKeyedMapper[Transaction] with IdPK {
    def getSingleton = Transaction
    
    object user extends MappedLongForeignKey(this, User)
    object company extends MappedLongForeignKey(this, Company)
    object volume extends MappedInt(this) // volume aka quantity
    object unit_price extends MappedInt(this)
}

object Transaction extends Transaction with LongKeyedMetaMapper[Transaction]
