name := "mnmCount"

version := "0.1"

scalaVersion := "2.12.10"

idePackagePrefix := Some("com.github")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.2",
  "org.apache.spark" %% "spark-sql" % "3.1.2"
)
