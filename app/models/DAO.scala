package models

import scala.slick.driver.JdbcProfile
import play.api.db.slick.Profile
import play.api.db.slick.DB

class DAO(override val profile: JdbcProfile) extends DogComponent with CatComponent with Profile {
  import profile.simple._

  object Dogs extends TableQuery(new DogsTable(_)) {
  }
  object Cats extends TableQuery(new CatsTable(_)) with BaseTableQuery[CatsTable] {
  }
}

object current {
  val dao = new DAO(DB("default")(play.api.Play.current).driver)
}