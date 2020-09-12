name := """play-java-myapp"""
organization := "com.wkb.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
//lazy val root = (project in file(".")).enablePlugins(PlayJava)


scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += javaJpa
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += evolutions
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"

// To provide an implementation of JAXB-API, which is required by Ebean.
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.3.1"
libraryDependencies += "javax.activation" % "activation" % "1.1.1"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2"


