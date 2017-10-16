package it.gov.daf.facades

import it.gov.daf.facades.test.DummyCatalogManager
import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class DatasetTest extends FunSuite {

  val datasetLuoghi = new Dataset("ds_luoghi", new DummyCatalogManager("luoghi"))

  println(datasetLuoghi.getDataSchema)
  println(datasetLuoghi.getColumnNames)
  println(datasetLuoghi.getColumnOntologyTags)
  println(datasetLuoghi.getData)

  val vocabularyLuoghi = new Dataset("voc_luoghi", new DummyCatalogManager("luoghi"))

  println(vocabularyLuoghi.getDataSchema)
  println(vocabularyLuoghi.getColumnNames)
  println(vocabularyLuoghi.getColumnOntologyTags)

}