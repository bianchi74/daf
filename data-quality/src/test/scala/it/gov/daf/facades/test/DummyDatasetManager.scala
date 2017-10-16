package it.gov.daf.facades.test

import it.gov.daf.facades._

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class DummyDatasetManager extends DatasetManager{

  def loadDataframe(datasetName: String): Option[DataFrame] = {


    ds_uri match {
      case s if s.equals("ds_luoghi") => {
        val colName = (new DummyCatalogManager(s)).
        Some(DataMock.getData(s).map{case List(a,b,c,d,e,f) => (a,b,c,d,e,f)}.toDF(colName: _*))
      }
      case s if s.equals("voc_luoghi") => {
        val colName = (new CatalogManager(s)).colName
        Some(DataMock.getData(s).map{case List(a,b,c,d,e,f) => (a,b,c,d,e,f)}.toDF(colName: _*))
      }
      case _ => None
    }
  }
}
