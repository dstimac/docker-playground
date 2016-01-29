import xerial.sbt.Pack._

lazy val root = Project("docker-playground-rest-client", file("."))

organization := "com.mentatlabs"
name := "docker-playground-rest-client"
version := "0.0.1"

resolvers ++= Seq(
  "NGS Nexus"     at "http://ngs.hr/nexus/content/groups/public/"
, "Element Nexus" at "http://repo.element.hr/nexus/content/groups/public/"
, Resolver.sonatypeRepo("snapshots")
)

packAutoSettings ++ Seq(
  packMain := Map("startClient.sh" -> "com.mentatlabs.RestClient")
)


libraryDependencies ++= Seq(
  "org.scala-lang" %  "scala-reflect" % scalaVersion.value

, "com.typesafe.scala-logging" %% "scala-logging"   % "3.1.0"
, "ch.qos.logback"             %  "logback-classic" % "1.1.3"

, "org.specs2"  %% "specs2-scalacheck" % "3.6.5"  % "test"


, "commons-cli" % "commons-cli" % "1.3.1"

, "com.ning" % "async-http-client" % "1.9.32"

//, "com.codacy" %% "scala-consul" % "1.1.0"
, "com.orbitz.consul" % "consul-client" % "0.9.16"
, "org.apache.cxf" % "cxf-rt-rs-client" % "3.0.3"
, "org.apache.cxf" % "cxf-rt-transports-http-hc" % "3.0.3"
)

Revolver.settings
Revolver.enableDebugging(port = 9999, suspend = false)
