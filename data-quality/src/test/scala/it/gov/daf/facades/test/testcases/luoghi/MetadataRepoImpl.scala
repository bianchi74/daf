package it.gov.daf.facades.test.testcases.luoghi

import it.gov.daf.facades.test.MetadataRepo

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
object MetadataRepoImpl extends MetadataRepo{

  override def getCatalogMetadata(datasetName: String) = {
    Map(
      "dcatap" -> catalog_dcat.getOrElse(datasetName, Map()),
      "dataschema" -> catalog_dataschema.getOrElse(datasetName, Map()),
      "operational" -> catalog_operational.getOrElse(datasetName, Map())
    )
  }

  override def getMetadata(ds_uri: String, metadata: String): Map[String, Any] = {
    metadata match {
      case "dcatap" => catalog_dcat.getOrElse(ds_uri, Map())
      case "dataschema" => catalog_dataschema.getOrElse(ds_uri, Map())
      case "operational" => catalog_operational.getOrElse(ds_uri, Map())
    }
  }

  //Metadata Info per dataset
  val catalog_dataschema = Map(
    "ds_luoghi" -> Map(
      "namespace" -> "it.daf.ds.ordinary",
      "type" -> "record",
      "name" -> "test",
      "fields" -> Seq(
        Map(
          "name"->"id",
          "type"->"int"
        ),
        Map(
          "name"->"comune",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.comune.nome",
            "tag" -> "comune, residenza",
            "fields_connection" -> "luogo_residenza"
          )
        ),
        Map(
          "name"->"provincia",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.provincia.nome",
            "tag" -> "provincia, residenza",
            "fields_connection" -> "luogo_residenza"
          )
        ),
        Map(
          "name"->"provincia_code",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.provincia.code",
            "tag" -> "provincia, residenza",
            "fields_connection" -> "luogo_residenza"
          )
        ),
        Map(
          "name"->"regione",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.regione.nome",
            "tag" -> "regione, residenza",
            "fields_connection" -> "luogo_residenza"
          )
        ),
        Map(
          "name"->"pop",
          "type"->"int"
        )
      )
    ),
    "voc_luoghi" -> Map(

      "namespace" -> "it.daf.ds.voc",
      "type" -> "record",
      "name" -> "voc_luoghi",
      "fields" -> Seq(
        Map(
          "name"->"id",
          "type"->"int",
          "metadata" -> Map(
            "semantics"->"luoghi.id.val"
          )
        ),
        Map(
          "name"->"comune",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.comune.nome"
          )
        ),
        Map(
          "name"->"prov",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.provincia.nome"
          )
        ),
        Map(
          "name"->"prov_code",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.provincia.code"
          )
        ),
        Map(
          "name"->"regione",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.regione.nome"
          )
        ),
        Map(
          "name"->"stato",
          "type"->"String",
          "metadata" -> Map(
            "semantics"->"luoghi.stato.nome"
          )
        )
      )
    )
  )

  val catalog_dcat = Map(
    "ds_luoghi" -> Map("" ->""),
    "voc_luoghi" -> Map("" ->"")
  )

  val catalog_operational = Map(
    "ds_luoghi" -> Map("" ->""),
    "voc_luoghi" -> Map("" ->"")
  )

}
