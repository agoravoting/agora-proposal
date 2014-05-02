package controllers

import play.api.mvc.{Action, Controller}

object MainController extends Controller with securesocial.core.SecureSocial {

  def index =  SecuredAction { implicit request =>
    Ok(views.html.index.render(s"Hello ${request.user}"))
  }

  def api =  SecuredAction(ajaxCall = true)(parse.json) { implicit request =>
    Ok(views.html.index.render(s"Hello ${request.user}"))
  }
}