import Keys._

name := """agora-proposal"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  filters,   // A set of built-in filters (including csrf)
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "underscorejs" % "1.6.0-1",
  "org.webjars" % "backbonejs" % "1.1.2-1",
  "com.typesafe.play" %% "play-slick" % "0.6.0.1",
  "ws.securesocial" %% "securesocial" % "2.1.3",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4"
)

scalaVersion := "2.10.3"

resolvers += Resolver.sonatypeRepo("releases")

scalacOptions ++= Seq("-deprecation","-unchecked","-feature")

javaOptions in Test += "-Dconfig.file=conf/test.conf"

play.Project.playScalaSettings