package com.databasetoiceberg.common

import org.apache.iceberg.{Table, TableIdentifier}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.iceberg.IcebergSparkSessionExtensions

object IcebergUtils {

  def createIcebergTable(spark: SparkSession, tableName: String, schema: String): Table = {
    val tableIdentifier = TableIdentifier.of("default", tableName)
    val catalog = spark.conf.get("spark.sql.catalog.default")
    
    spark.sql(s"CREATE TABLE $catalog.$tableName ($schema) USING iceberg")
    spark.table(tableIdentifier.toString)
  }

  def writeDataToIcebergTable(dataFrame: DataFrame, tableName: String): Unit = {
    dataFrame.write
      .format("iceberg")
      .mode("append")
      .save(tableName)
  }

  def readIcebergTable(spark: SparkSession, tableName: String): DataFrame = {
    spark.read
      .format("iceberg")
      .load(tableName)
  }
}