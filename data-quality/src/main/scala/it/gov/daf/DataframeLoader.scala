package it.gov.daf

import it.gov.daf.StorageType.StorageType
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  * @author Fabiana
  */
object DatasetAccess {

  def getDataFrame(sc: SparkSession, uri: String, sType: StorageType): Option[DataFrame] = {
    sType match {
      case StorageType.HDFS => Some(sc.read.json(""))
      case _ => None
    }

  }

}

object StorageType extends Enumeration {
  type StorageType = Value
  val HDFS, HBASE, KUDU = Value
}