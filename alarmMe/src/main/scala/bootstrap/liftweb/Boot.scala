package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http._
import _root_.net.liftweb.http.provider._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.net.liftweb.mapper.{DB, ConnectionManager, Schemifier, DefaultConnectionIdentifier, StandardDBVendor}
import _root_.java.sql.{Connection, DriverManager}
import _root_.pl.dwazero.alarmme.model._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor = 
	new StandardDBVendor(Props.get("db.driver") openOr "org.h2.Driver",
			     Props.get("db.url") openOr 
			     "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
			     Props.get("db.user"), Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }

    // where to search snippet
    LiftRules.addToPackages("pl.dwazero.alarmme")
    Schemifier.schemify(true, Schemifier.infoF _, 
      User, Company, ExchangeRate, SharePortfolio, Transaction)


    val isLoggedIn = If(() => User.loggedIn_?, () => RedirectResponse("/login"))

    // Build SiteMap
    def sitemap() = SiteMap(
      Menu("Home") / "index" :: // Simple menu form
      Menu("Utwórz portfel") / "portfolio" / "create" :: //>> If(() => User.loggedIn_?, "You must be logged in"),
      // Menu with special Link
      Menu(Loc("Static", Link(List("static"), true, "/static/index"), "Static Content")) ::
      Company.menu(List("company"), isLoggedIn) ::
      // Menu entries for the User management stuff
      User.sitemap :_*
    )
      // Company.menus ::: 
      // ExchangeRate.menus ::: 
      // SharePortfolio.menus ::: 
      // Transaction.menus :_*)

    LiftRules.setSiteMapFunc(sitemap)
    
    
    // set DocType to HTML5
    LiftRules.docType.default.set((r: Req) => r match {
      case _ if S.skipDocType => Empty
      case _ if S.getDocType._1 => S.getDocType._2
      case _ => Full(DocType.html5)
    })
    

    /*
     * Show the spinny image when an Ajax call starts
     */
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    /*
     * Make the spinny image go away when it ends
     */
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(makeUtf8)

    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    S.addAround(DB.buildLoanWrapper)
  }

  /**
   * Force the request to be UTF-8
   */
  private def makeUtf8(req: HTTPRequest) {
    req.setCharacterEncoding("UTF-8")
  }
}
