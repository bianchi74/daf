package it.gov.daf

import org.codehaus.jackson.JsonNode
import org.codehaus.jackson.map.ObjectMapper
import scala.collection.JavaConverters._

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  * @author Fabiana
  */

class Dataset (idDataset : String){

  private val catalogManagerClient : String = ""
  private val datasetManagerClient : String = ""

  var schema: Option[String] = None
  var phisicalInfo: Option[(String, String)] = None

  def getSchema : String = {
    schema match {
      case None =>
        val res = "" //TODO chiamare catalogManager client
        schema = Some(res)
        res
      case Some(s) => s

    }
  }

  def getColumnsName: Seq[String]= {
    val arrNode: Seq[JsonNode] = new ObjectMapper().readTree(getSchema).get("fields").asScala.toList
    for {
      e <- arrNode
    } yield e.get("name").asText()
  }

  def getAvailableFormats: (String, String) = {

    phisicalInfo match {
      case None =>
        val res = ("", "") //TODO chiamare datasetManager client
        phisicalInfo = Some(res)
        res
      case Some(s) => s

    }
  }
}
