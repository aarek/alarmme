package pl.dwazero.snippet

import scala.xml.{NodeSeq}
import pl.dwazero._
import code.model._

class Util {
 def loggedIn(html: NodeSeq) =
   if (User.loggedIn_?) html else NodeSeq.Empty

 def loggedOut(html: NodeSeq) =
   if (!User.loggedIn_?) html else NodeSeq.Empty
}