name := "Railroad"

version := sys.props.getOrElse("tag", default = "0.0.0")

organization := "com.github.fabiank1"

scalaVersion := "2.12.3"

libraryDependencies ++= {
  Seq(
  "net.nextencia" % "rrdiagram" % "0.9.4",
   "commons-io" % "commons-io" % "2.6")
}

EclipseKeys.withSource := true
