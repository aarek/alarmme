package pl.dwazero.alarmme.model

import _root_.net.liftweb.mapper._

class Company extends LongKeyedMapper[Company] with IdPK {
    def getSingleton = Company
    
    object name extends MappedPoliteString(this, 128)
    object code extends MappedPoliteString(this, 16)
}

object Company extends Company 
  with LongKeyedMetaMapper[Company] 
  with LongCRUDify[Company]
