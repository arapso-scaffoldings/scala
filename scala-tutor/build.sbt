import Dependencies._

lazy val commonSettings = Seq(
  organization := "pl.arapso.scaffoldings.scala",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.11"
)

lazy val root = (project in file("scala-examples")).
  settings(
    commonSettings,
    name := "ScalaExamples"
  )

lazy val custom = (project in file("custom")).
  settings(
    commonSettings,
    name := "CustomExamples",
    libraryDependencies += scalaTest,
    libraryDependencies ++= spark,
    libraryDependencies ++= swing,
    libraryDependencies ++= cats
  )

lazy val fpis = (project in file("fpis")).
  settings(
    commonSettings,
    name := "FunctionalProgramingInScala",
    libraryDependencies += scalaTest,
    libraryDependencies ++= spark,
    libraryDependencies ++= swing
  )


lazy val akka = (project in file("akka")).
  settings(
    commonSettings,
    name := "AkkaFramework",
    libraryDependencies += scalaTest,
    libraryDependencies ++= akkaHttp,
    libraryDependencies ++= redis
  )

lazy val monixExamples = (project in file("monix")).
  settings(
    commonSettings,
    name := "MonixExamples",
    libraryDependencies += scalaTest,
    libraryDependencies ++= monix
  )