name := "EmployeeDetailsSpring"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.hibernate" % "hibernate-core" % "3.6.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "3.6.3.Final",
  "org.springframework.data" % "spring-data-jpa" %    "1.5.3.RELEASE",
  "org.hibernate" % "hibernate-validator" % "4.3.1.Final",
  "org.springframework.data" % "spring-data-jpa" % "1.5.3.RELEASE",
  "commons-dbcp" % "commons-dbcp" % "1.4",
  "mysql" % "mysql-connector-java" % "5.1.29"
)     

play.Project.playJavaSettings
