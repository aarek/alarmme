package pl.dwazero.alarmme.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.mapper.view._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.http.PaginatorSnippet


import pl.dwazero.alarmme.model.{Company}
import Helpers._

class CompaniesPaginated extends PaginatorSnippet[Company] {
  override def count  = Company.count()
  override def page   = Company.findAll(
      BySql("1=1", new IHaveValidatedThisSQL("matee", "Sep 17 2010 01:30 CET")),
      StartAt(curPage*itemsPerPage), // ekhm where is curPage and itemsPerPage definition? --mp
      MaxRows(itemsPerPage)          // as above...
    )
  
  def renderPage(in: NodeSeq): NodeSeq = page.flatMap(
      item => bind("item", in, 
          "id"          -> item.id,
          "name"        -> item.name,
          "code"        -> item.code
        )
    )
  
}