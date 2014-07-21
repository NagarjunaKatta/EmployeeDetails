name := "training"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.2",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.2.2",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.2.2"
)     

play.Project.playJavaSettings
