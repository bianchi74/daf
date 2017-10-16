package it.gov.daf.facades

import it.gov.daf.facades.test.CatalogManagerMock
import it.gov.daf.facades.test.testcases.luoghi.MetadataMock

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class DatasetFacade(ds_uri: String) extends Serializable {



  private lazy val dummyDatasetName= ds_uri.substring("test-".length, ds_uri.length)
  private lazy val catalogManager= new CatalogManagerMock(dummyDatasetName)

  /**
    * schema of the specified dataset
    */
  lazy val datasetSchema: Map[String,Any] = {

    if (ds_uri.startsWith("test-")) {
      MetadataMock.getCatalogMetadata(dummyDatasetName,"dataschema")
    } else {
      //TODO Put here the real code
      Map()
    }
  }

  lazy val columnNames =
    datasetSchema("fields").asInstanceOf[Seq[Map[String, Object]]]
      .map(x => x("name").asInstanceOf[String])


  lazy val columnOntologyTags =
    datasetSchema("fields").asInstanceOf[Seq[Map[String, Object]]]
      .map{x =>
        val metadata = x.asInstanceOf[Map[String, Object]].get("metadata")
        val semantics = metadata match{
          case Some(s) => s.asInstanceOf[Map[String,String]].get("semantics")
          case None => None
        }
        x("name").asInstanceOf[String] -> semantics
      }
      .filter(x=>x._2.isDefined).map(x=> x._1->x._2.get).toMap
}