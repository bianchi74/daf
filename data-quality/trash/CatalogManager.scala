package it.gov.daf.core.mock

import it.gov.daf.facades.test.testcases.luoghi.MetadataMock

class CatalogManager(ds_uri: String) extends Serializable {

  val data_schema : Map[String, Any] = MetadataMock.getCatalogMetadata(ds_uri, "dataschema")

  val colName =
    data_schema("fields").asInstanceOf[Seq[Map[String, Object]]]
      .map(x => x("name").asInstanceOf[String])

  val ontoTag =
    data_schema("fields").asInstanceOf[Seq[Map[String, Object]]]
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
