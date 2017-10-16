package it.gov.daf.facades.test

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
trait MetadataRepo {

  def getCatalogMetadata(datasetName: String) : Map[String, Any]
  def getMetadata(datasetName: String, metadataset: String) : Map[String, Any]

}
