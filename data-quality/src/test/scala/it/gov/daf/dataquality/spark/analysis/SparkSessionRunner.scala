package it.gov.daf.dataquality.spark.analysis

import org.apache.spark.sql.SparkSession

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */

object SparkSessionRunner {

  def main(args: Array[String]): Unit = {
    val appName = "EmailCheck"
    val master = "local[*]"

    val spark = SparkSession
      .builder()
      .appName(appName)
      .master("local[*]")
      .getOrCreate()

    val dataFrame =

    val emailCheck = new EmailChecker

    /*
     //   ingestStd.init("ds_luoghi", spark)
    */

    spark.stop()

  }
}
