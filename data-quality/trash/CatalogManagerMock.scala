package it.gov.daf.facades.test

import it.gov.daf.facades.CatalogManager


/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class CatalogManagerMock(dummyDatasetUri :String)extends CatalogManager{

  lazy val datasetSchema: Map[String,Map[String,Object]] = dummyDatasetUri match {
    case "ds_luoghi" => it.gov.daf.facades.test.testcases.luoghi.DummyMetadataRepoImpl.getCatalogMetadata(dummyDatasetUri)
    case _ => Map()
  }

/*  lazy val columnNames =
    datasetSchema("fields").asInstanceOf[Seq[Map[String, Object]]]
      .map(x => x("name").asInstanceOf[String])
*/

}
