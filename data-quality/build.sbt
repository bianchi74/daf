
name := "data-quality"

version := "0.2"

scalaVersion := "2.11.1"

lazy val sparkVersion = "2.0.0"
lazy val spark = "org.apache.spark"

def dependencyToProvide(scope: String = "compile") = Seq(
  spark %% "spark-core" % sparkVersion % scope,
  spark %% "spark-sql" % sparkVersion % scope,
  spark %% "spark-streaming" % sparkVersion % scope
)


libraryDependencies ++= dependencyToProvide()

// https://mvnrepository.com/artifact/commons-validator/commons-validator
libraryDependencies += "commons-validator" % "commons-validator" % "1.6"

// https://mvnrepository.com/artifact/commons-io/commons-io
libraryDependencies += "commons-io" % "commons-io" % "2.5"

// https://mvnrepository.com/artifact/net.sourceforge.htmlunit/htmlunit
libraryDependencies += "net.sourceforge.htmlunit" % "htmlunit" % "2.27"

// https://mvnrepository.com/artifact/org.json/json
libraryDependencies += "org.json" % "json" % "20170516"

// https://mvnrepository.com/artifact/com.google.api-client/google-api-client
//libraryDependencies += "com.google.api-client" % "google-api-client" % "1.22.0"

// https://mvnrepository.com/artifact/log4j/log4j
libraryDependencies += "log4j" % "log4j" % "1.2.17"

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.11
//libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.4" % "test"
libraryDependencies += "org.specs2" %% "specs2-core" % "3.9.5" % "test"

// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.12" % "test"

// http://www.wartremover.org/doc/install-setup.html
//wartremoverErrors ++= Warts.unsafe


libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.3"