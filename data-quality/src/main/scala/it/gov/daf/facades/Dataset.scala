package it.gov.daf.facades

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class Dataset(datasetName: String, catalogManager: CatalogManager) extends Serializable {

  def getDataSchema = dataSchema
  def getColumnNames = columnNames
  def getColumnOntologyTags = columnOntologyTags
  def getData = data

  private lazy val dataSchema: Map[String,Any] = catalogManager.getDatasetSchema(datasetName)

  private lazy val columnNames =
    dataSchema("fields").asInstanceOf[Seq[Map[String, Object]]]
      .map(x => x("name").asInstanceOf[String])

  private lazy val columnOntologyTags =
    dataSchema("fields").asInstanceOf[Seq[Map[String, Object]]]
      .map{x =>
        val metadata = x.asInstanceOf[Map[String, Object]].get("metadata")
        val semantics = metadata match{
          case Some(s) => s.asInstanceOf[Map[String,String]].get("semantics")
          case None => None
        }
        x("name").asInstanceOf[String] -> semantics
      }
      .filter(x=>x._2.isDefined).map(x=> x._1->x._2.get).toMap

  private lazy val data = catalogManager.getData(datasetName)


}