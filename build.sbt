name := """play-java-myapp"""
organization := "com.wkb.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.2"

libraryDependencies += guice
libraryDependencies += javaJpa
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += evolutions
libraryDependencies += javaJdbc
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
