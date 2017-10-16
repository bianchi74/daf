package it.gov.daf.facades.test.testcases

import it.gov.daf.facades.test.DataRepo

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
object EmptyDataRepoImpl extends DataRepo {

  override def getData(datasetName: String) = List()

}
