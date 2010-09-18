package pl.dwazero.alarmme.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util.{Helpers}
import _root_.net.liftweb.http.{RedirectResponse, RequestVar, S, SHtml}
import pl.dwazero.alarmme.model.{Company => CompanyModel}
import Helpers._

object name extends RequestVar("")
object code extends RequestVar("")

class Company {
  
  def add(in: NodeSeq): NodeSeq = {
    
    def processCompanyAdd() = 
      if (name.toString.length < 1 || code.toString.length < 1) {
        S.error("Zbyt krótka nazwa spółki")
        // TODO: check if company exists in DB
      } else {
        val company: CompanyModel = CompanyModel.create
        company.name(name)
        company.code(code)
        val saved: Boolean = company.save
        S.notice("Spółka dodana")
        // TODO: fix redirect
        RedirectResponse("/")
      }
    
    Helpers.bind("entry", in, 
      "name" -> SHtml.text(name.is, name(_)),
      "code" -> SHtml.text(code.is, code(_)),
      "submit" -> SHtml.submit("Dodaj", processCompanyAdd))
  }
}

