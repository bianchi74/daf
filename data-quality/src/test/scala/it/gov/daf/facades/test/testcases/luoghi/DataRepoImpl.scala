package it.gov.daf.facades.test.testcases.luoghi

import it.gov.daf.facades.test.DataRepo

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
object DataRepoImpl extends DataRepo{

  def getData(datasetName: String): List[List[String]] = data.getOrElse(datasetName, List())

  val dataLuoghi = List(
    List("1", "Santeramo", "Verona", "VE", "Veneto", "1000"),
    List("2", "Bari","Bari", "BA", "Puglia", "2000"),
    List("3", "Bitritto","Bari", "BA", "Puglia", "3000"),
    List("4", "Santeramo in Colle","Bari", "BA", "Puglia", "1000"),
    List("5", "Santrmano in Colli","Bari", "BA", "Puglia", "3000")
  )

  // Voc Controllato
  val dicLuoghi = List(
    List("1", "Santeramo", "Verona", "VE", "Veneto", "Italia"),
    List("2", "Bari","Bari", "BA", "Puglia", "Italia"),
    List("3", "Bitritto","Bari", "BA", "Puglia", "Italia"),
    List("4", "Santeramo in Colle","Bari", "BA", "Puglia", "Italia")
  )

  val data = Map(
    "ds_luoghi" -> dataLuoghi,
    "voc_luoghi" -> dicLuoghi
  )

}
