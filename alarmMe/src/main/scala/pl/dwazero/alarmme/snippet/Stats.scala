package pl.dwazero.alarmme.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util.{Helpers}
import pl.dwazero.alarmme.model._
import Helpers._

class Stats {
  
  def summary(in: NodeSeq) = {
    val users_count: Long = User.count()
    Helpers.bind("stat", in, 
      "users" -> users_count.toString,
      "companies" -> Company.count().toString)
  }
}

