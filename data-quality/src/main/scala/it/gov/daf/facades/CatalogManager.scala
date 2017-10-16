package it.gov.daf.facades

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
trait CatalogManager {

  def getDatasetSchema(datasetName: String): Map[String,Any]

  def getData(datasetName: String): List[List[String]]

}
