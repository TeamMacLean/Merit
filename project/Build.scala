import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "PlayBadges"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.webjars" %% "webjars-play" % "2.1.0-2",
    "org.webjars" % "jquery" % "1.8.2",
    "org.webjars" % "bootstrap" % "2.1.1",
    "org.webjars" % "flat-ui" % "bcaf2de95e"
    )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here     
  )

}
