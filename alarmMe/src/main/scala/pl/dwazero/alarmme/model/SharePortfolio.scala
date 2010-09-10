package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._

class SharePortfolio extends LongKeyedMapper[SharePortfolio] with IdPK {
    def getSingleton = SharePortfolio
    
    object user extends MappedLongForeignKey(this, User)
    object name extends MappedPoliteString(this, 128)
    object description extends MappedTextarea(this, 8192)
}

object SharePortfolio extends SharePortfolio with LongKeyedMetaMapper[SharePortfolio]
