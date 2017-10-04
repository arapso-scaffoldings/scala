import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

  private[this] val akkaVersion = "2.4.17"
  private[this] val akkaHttpVersion = "10.0.5"
  private[this] val sparkVersion = "2.1.0"
  private[this] val hadoopVersion = "2.7.3"

  val spark = {
    Seq(
      "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion,
      "org.apache.hadoop" % "hadoop-client" % hadoopVersion,
      "org.apache.spark" %% "spark-core" % sparkVersion
    )
  }

  val swing = {
    Seq(
      "org.scala-lang" % "scala-swing" % "2.11.0-M7"
    )
  }

  val akka = {
    Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-agent" % akkaVersion,
      "com.typesafe.akka" %% "akka-camel" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
      "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
      "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-osgi" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence-tck" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-distributed-data-experimental" % akkaVersion,
      "com.typesafe.akka" %% "akka-typed-experimental" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence-query-experimental" % akkaVersion
    )
  }

  val akkaHttp = {
    Seq(
      "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-jackson" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion
    )
  } ++ akka


  val redis = {
    val redisV = "1.6.0"
    Seq(
      "com.github.etaty" %% "rediscala" % redisV exclude("com.typesafe.akka", "akka-actor_2.11")
    )
  }

  val monix = {
    val monixV = "2.3.0"
    Seq(
      "io.monix" %% "monix" % monixV,
      "io.monix" %% "monix-types" % monixV,
      "io.monix" %% "monix-execution" % monixV,
      "io.monix" %% "monix-eval" % monixV,
      "io.monix" %% "monix-reactive" % monixV
    )
  }

  val cats = {
    val catsV = "1.0.0-MF"
    val catsEffectsV = "0.4"
    Seq(
      "org.typelevel" %% "cats-core" % catsV,
      "org.typelevel" %% "cats-kernel" % catsV,
      "org.typelevel" %% "cats-macros" % catsV,
      "org.typelevel" %% "cats-laws" % catsV,
      "org.typelevel" %% "cats-free" % catsV,
      "org.typelevel" %% "cats-testkit" % catsV,
      "org.typelevel" %% "cats-effect" % catsEffectsV
    )
  }
}