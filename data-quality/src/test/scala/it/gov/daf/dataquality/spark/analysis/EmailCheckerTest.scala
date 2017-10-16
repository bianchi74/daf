package it.gov.daf.dataquality.spark.analysis

import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class EmailCheckerTest extends FunSuite {


  test("EmailChecker") {

    val appName = "EmailCheck"
    val master = "local[*]"

    val spark = SparkSession
      .builder()
      .appName(appName)
      .master("local[*]")
      .getOrCreate()

    val emailCheck = new EmailChecker
    /*
     //   ingestStd.init("ds_luoghi", spark)
    */

    spark.stop()



  }



}
