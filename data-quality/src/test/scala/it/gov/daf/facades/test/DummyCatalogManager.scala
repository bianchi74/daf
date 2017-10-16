package it.gov.daf.facades.test

import it.gov.daf.facades.CatalogManager
import it.gov.daf.facades.test.testcases.EmptyMetadataRepoImpl

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class DummyCatalogManager(testcaseName:String) extends CatalogManager {

  lazy val metadataRepo : MetadataRepo = testcaseName match {
    case "luoghi" => it.gov.daf.facades.test.testcases.luoghi.MetadataRepoImpl
    case _ => EmptyMetadataRepoImpl
  }

  lazy val dataRepo = testcaseName match {
    case "luoghi" => it.gov.daf.facades.test.testcases.luoghi.DataRepoImpl
    case _ =>it.gov.daf.facades.test.testcases.EmptyDataRepoImpl
  }

  override def getDatasetSchema(datasetName: String)= metadataRepo.getMetadata(datasetName,"dataschema")

  //.asInstanceOf[Map[String, Any]].get("dataschema").asInstanceOf
  //Map[String, Any]
  //data_schema("fields").asInstanceOf[Seq[Map[String, Object]]]
  // .map(x => x("name").asInstanceOf[String])

//    (metadataRepo.getMetadata(datasetName)).asInstanceOf[Map[String,Any]].get("dasetschema")

  override def getData(datasetName: String) = dataRepo.getData(datasetName)

}
