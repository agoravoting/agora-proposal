package commands

import play.api.Play.current
import play.core.StaticApplication
import scala.slick.jdbc.meta.MTable

import play.api.db.slick.DB
import models.current.dao._
import models.current.dao.profile.simple._

object SyncDB extends App {
  System.setProperty("config.file", "conf/test.conf")
  val application = new StaticApplication(new java.io.File("."))

  DB.withSession{ implicit session:Session =>
    val tables = Seq(Cats, Dogs)
    val present = MTable.getTables.list().filter(_.name.schema==Some("public")).map(_.name.name)

    val toDrop = tables.filter(x => present.contains(x.baseTableRow.tableName))
    toDrop.foreach(_.ddl.drop)
    tables.map(_.ddl).reduceLeft(_ ++ _).create
  }
}

/*


import models.current.dao._
import models.current.dao.profile.simple._
import play.api.Play.current
import play.api.db.slick.DB
System.setProperty("config.file", "conf/test.conf")
new play.core.StaticApplication(new java.io.File("."))


Cats.ddl

 DB.withSession { implicit session =>
 Cats.list
 }

play.api.db.slick.DB("default").withSession{ implicit session =>
     | Cats.list
     | }

*/