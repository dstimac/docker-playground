import xerial.sbt.Pack._

lazy val root = Project("docker-playground-rest", file("."))

organization := "com.mentatlabs"
name := "docker-playground-rest"
version := "0.0.1"

resolvers ++= Seq(
  "NGS Nexus"     at "http://ngs.hr/nexus/content/groups/public/"
, "Element Nexus" at "http://repo.element.hr/nexus/content/groups/public/"
, Resolver.sonatypeRepo("snapshots")
)

packAutoSettings ++ Seq(
  packMain := Map("startRest.sh" -> "com.mentatlabs.RestService")
)


libraryDependencies ++= Seq(
  "org.scala-lang" %  "scala-reflect" % scalaVersion.value

, "com.typesafe.scala-logging" %% "scala-logging"   % "3.1.0"
, "ch.qos.logback"             %  "logback-classic" % "1.1.3"

, "org.specs2"  %% "specs2-scalacheck" % "3.6.5"  % "test"

, "io.spray"  %% "spray-can"     % "1.3.3"
, "io.spray"  %% "spray-httpx"   % "1.3.3"
, "io.spray"  %% "spray-routing" % "1.3.3"

, "com.typesafe.akka" %% "akka-actor" % "2.4.1"
, "com.typesafe.akka" %% "akka-slf4j" % "2.4.1"

, "commons-cli" % "commons-cli" % "1.3.1"

, "com.codacy" %% "scala-consul" % "1.1.0"
)

Revolver.settings
Revolver.enableDebugging(port = 9999, suspend = false)
