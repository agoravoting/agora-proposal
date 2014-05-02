package models

import play.api.db.slick.Profile

trait WithId {
  val id: Option[Int]
}

trait BaseComponent[C <: WithId] { this: Profile =>
  import profile.simple._

  trait BaseTableQuery[T <: BaseTable[C]] { this: TableQuery[T] =>
    val findByID = this.findBy(_.id)

    def add(entity: C)(implicit session:Session) = {
      this.insert(entity)
    }
    def delete(id: Int)(implicit session:Session) = {
      this.filter(_.id === id).delete
    }
    def update(entity: C)(implicit session:Session) = {
     this.filter(_.id === entity.id).update(entity)
    }
  }

  abstract class BaseTable[C](tag: Tag, name: String) extends Table[C](tag, name) {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  }
}