package it.gov.daf.facades.test.testcases

import it.gov.daf.facades.test.MetadataRepo

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
object EmptyMetadataRepoImpl  extends MetadataRepo {
  override def getCatalogMetadata(datasetName: String) = Map()

  override def getMetadata(datasetName: String, metadataset: String) = Map()
}
