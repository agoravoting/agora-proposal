package commands

import play.api.Play.current
import play.core.StaticApplication
import play.api.db.slick.DB
import models.current.dao._
import models.current.dao.profile.simple._

object GenSQL extends App {
  System.setProperty("config.file", "conf/test.conf")
  val application = new StaticApplication(new java.io.File("."))

  DB.withSession{ implicit session:Session =>
    val tables = Seq(Cats, Dogs)

    val sql = tables.map(_.ddl).reduceLeft(_ ++ _).createStatements.mkString("\n")
    println("------------------------------------------------------------------")
    println(sql)
    println("------------------------------------------------------------------")
  }
}