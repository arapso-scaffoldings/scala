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
    libraryDependencies ++= spark
  )
