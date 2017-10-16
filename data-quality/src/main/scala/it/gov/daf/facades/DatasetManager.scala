package it.gov.daf.facades

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
trait DatasetManager {

  def loadDataframe(datasetName: String): Option[DataFrame];

}
