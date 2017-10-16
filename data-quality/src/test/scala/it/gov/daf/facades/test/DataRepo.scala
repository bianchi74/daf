package it.gov.daf.facades.test

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
trait DataRepo {

  def getData(datasetName: String): List[List[String]]

}
