package pl.dwazero.alarmme.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.mapper.view._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.http.{PaginatorSnippet,S,SHtml}


import pl.dwazero.alarmme.model.{SharePortfolio, User}
import Helpers._

class UserSharePortfoliosPaginated extends  PaginatorSnippet[SharePortfolio] {
  // val currentUserQueryParam:QueryParam = By(SharePortfolio.user, User.currentUser.id) // TODO: Sfikac pozniej czy zadziala
  var currentUser: User = User.currentUser.open_!
  override def count  = SharePortfolio.count(By(SharePortfolio.user, currentUser.id))
  override def page   = SharePortfolio.findAll(
      By(SharePortfolio.user, currentUser.id), 
      StartAt(curPage*itemsPerPage), 
      MaxRows(itemsPerPage)
    )
  
  def renderPage(in: NodeSeq): NodeSeq = page.flatMap(
      item => bind("item", in, 
          "id"          -> item.id,
          "name"        -> item.name,
          "description" -> item.description,
          "show"        -> SHtml.link("/share-portfolio/show/" + item.id, () => Unit, Text("Pokaż"))
        )
    )
  
}