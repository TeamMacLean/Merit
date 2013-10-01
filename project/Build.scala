import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "Merit"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.webjars" %% "webjars-play" % "2.1.0-2",
    "org.webjars" % "jquery" % "1.10.2",
    "org.webjars" % "flat-ui" % "bcaf2de95e",
    "org.apache.commons" % "commons-email" % "1.3.1",
    "mysql" % "mysql-connector-java" % "5.1.18"
    )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here  
  )

}
