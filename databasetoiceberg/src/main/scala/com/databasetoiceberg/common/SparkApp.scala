package com.databasetoiceberg.common

import org.apache.spark.sql.SparkSession

object SparkApp {
  def createSparkSession(appName: String): SparkSession = {
    SparkSession.builder()
      .appName(appName)
      .config("spark.sql.catalog.spark_catalog", "org.apache.iceberg.spark.SparkCatalog")
      .config("spark.sql.catalog.spark_catalog.type", "hive")
      .config("spark.sql.catalog.spark_catalog.uri", "thrift://localhost:9083") // Update with your Hive Metastore URI
      .config("spark.sql.extensions", "org.apache.iceberg.spark.extensions.IcebergSparkSessionExtensions")
      .getOrCreate()
  }
}