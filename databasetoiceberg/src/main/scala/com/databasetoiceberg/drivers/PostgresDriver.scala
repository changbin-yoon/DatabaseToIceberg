package com.databasetoiceberg.drivers

import org.apache.spark.sql.{DataFrame, SparkSession}
import com.databasetoiceberg.common.IcebergUtils
import com.databasetoiceberg.utils.DbConnector

class PostgresDriver(spark: SparkSession, dbParams: Map[String, String]) {

  def readData(query: String): DataFrame = {
    val connector = new DbConnector()
    connector.connectToPostgres(dbParams)
    val data = spark.read
      .format("jdbc")
      .option("url", dbParams("url"))
      .option("dbtable", s"($query) as query")
      .option("user", dbParams("user"))
      .option("password", dbParams("password"))
      .load()
    
    data
  }

  def writeDataToIceberg(data: DataFrame, icebergTable: String): Unit = {
    IcebergUtils.writeToIceberg(data, icebergTable)
  }
}