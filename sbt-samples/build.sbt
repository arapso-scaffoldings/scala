import Dependencies._

lazy val helloWorld = (project in file("scala-hello-world")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test
  )

lazy val akkaHttpProject = (project in file("akka-http")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "AkkaHttpExample",
    libraryDependencies += scalaTest % Test
  ).settings(libraryDependencies ++= akkaHttp)
