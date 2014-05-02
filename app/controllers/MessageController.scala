package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import play.api.Routes

import play.api.Play.current
import models.Cat
import play.api.db.slick.DB
import models.current.dao._
import models.current.dao.profile.simple._

case class Message(value: String)

object MessageController extends Controller {

  implicit val fooWrites = Json.writes[Message]
  implicit val catWrites = Json.writes[Cat]

  def getMessage = Action { implicit request =>
    DB("default").withSession{ implicit session:Session =>
      Ok(Json.toJson(Cats.list))
    }
  }

  def javascriptRoutes = Action { implicit request =>
    Ok(Routes.javascriptRouter("jsRoutes")(
      // list of routes
      routes.javascript.MessageController.getMessage
    )).as(JAVASCRIPT)
  }
}