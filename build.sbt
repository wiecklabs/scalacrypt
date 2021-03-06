import sbt.Keys.crossScalaVersions

lazy val root = project.in(file("."))
  .aggregate(scalacrypt)
  .settings(
    publish := {},
    publishLocal := {},
    publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo")))
  )

lazy val scalacrypt = project.in(file("."))
  .settings(
    name := "scalacrypt",
    organization := "xyz.wiedenhoeft",
    version := "0.5-SNAPSHOT",
    licenses := Seq("Apache" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/richard-w/scalacrypt")),
    scalaVersion := "2.12.8",
    crossScalaVersions := Seq("2.11.12", "2.12.8"),
    scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-opt:_"),
    libraryDependencies ++= {
      Seq(
        "org.scalatest" %% "scalatest" % "3.0.5"
      )
    },
    publishMavenStyle := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    useGpg := true,
    usePgpKeyHex("CB8F8B69"),
    pomExtra := (
      <developers>
        <developer>
          <id>richard-w</id>
          <name>Richard Wiedenhöft</name>
          <url>https://github.com/Richard-W</url>
        </developer>
      </developers>
      <scm>
        <url>https://github.com/richard-w/scalacrypt</url>
        <connection>scm:https://github.com/richard-w/scalacrypt.git</connection>
      </scm>
    )
  )
