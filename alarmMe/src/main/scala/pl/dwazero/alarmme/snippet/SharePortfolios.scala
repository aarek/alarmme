package pl.dwazero.alarmme.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util.{Helpers}
import _root_.net.liftweb.http.{RedirectResponse, RequestVar, S, SHtml}
import _root_.net.liftweb.common.{Box,Empty,Full}
import _root_.net.liftweb.mapper._
import pl.dwazero.alarmme.model.{SharePortfolio, User}
import Helpers._

object sp_name extends RequestVar[String]("") // maxlen -> 128
object description extends RequestVar[String]("") // maxlen -> 8192
object is_public extends RequestVar[Boolean](false)

class SharePortfolios {
  
  // TODO: return Map  valid if map is empty; (name -> "Pole jest wymagane")
  private def valid_?(sp_name: String, description: String, is_public: Boolean): Boolean = {
    if (sp_name.toString.length < 1)
      return false
    // description is optional
    return true
  }
  
  def add(in: NodeSeq): NodeSeq = {
    
    def processCompanyAdd() = 
      if (valid_?(sp_name, description, is_public)) {
        val currentUser: User = User.currentUser.open_!
        val sharePortfolio: SharePortfolio = SharePortfolio.create
        sharePortfolio.name(sp_name)
        sharePortfolio.description(description)
        sharePortfolio.user(currentUser)
        sharePortfolio.is_public(is_public)
        val saved: Boolean = sharePortfolio.save
        S.notice("Portfel dodany")
        S.redirectTo("/share-portfolio/index")
      } else {
        S.error("Popraw błędy")
      }
    
    Helpers.bind("entry", in, 
      "name" -> SHtml.text(sp_name.is, sp_name(_), "maxlenght" -> "128"),
      "description" -> SHtml.textarea(description.is, description(_), "maxlenght" -> "8192"),
      "is_public" -> SHtml.checkbox(is_public.is, is_public(_)),
      "submit" -> SHtml.submit("Dodaj", processCompanyAdd))
  }
  
  
  def detail(in: NodeSeq): NodeSeq = S.param("id") match {
    case Full(portfolioId) => {
      SharePortfolio.find(By(SharePortfolio.user, User.currentUser.open_!.id), By(SharePortfolio.id, portfolioId.toLong)) match {
        case portfolioContainer:Full[SharePortfolio] => Text("Jea, foundit")
        // {
        //   // znaczy sie ze znalazl, trzeba dorobic detail
        // }
        case _ => Text("Nie znalazłem twojego portfolio o id " + portfolioId)
      }
    }
    case _ => Text("Brak id portfolio")
  }
  
  
  
}

