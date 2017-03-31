import Dependencies._

lazy val custom = (project in file("custom")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "CustomExamples",
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= spark,
    libraryDependencies ++= swing
  )

lazy val fpis = (project in file("fpis")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "FunctionalProgramingInScala",
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= spark,
    libraryDependencies ++= swing
  )
