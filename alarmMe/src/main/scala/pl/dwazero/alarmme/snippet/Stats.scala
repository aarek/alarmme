package pl.dwazero.alarmme.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util.{Helpers}
import pl.dwazero.alarmme.model._
import Helpers._

class Stats {
  
  def summary(in: NodeSeq) = {
    var users_count: Long = User.count()
    Helpers.bind("stat", in, "users" -> users_count.toString)
  }
}
// 
// class HelloWorld {
//   lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date
// 
//   def howdy(in: NodeSeq): NodeSeq =
//   Helpers.bind("b", in, "time" -> date.map(d => Text(d.toString)))
// 
//   /*
//    lazy val date: Date = DependencyFactory.time.vend // create the date via factory
// 
//    def howdy(in: NodeSeq): NodeSeq = Helpers.bind("b", in, "time" -> date.toString)
//    */
// }
