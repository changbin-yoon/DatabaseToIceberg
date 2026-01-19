package com.databasetoiceberg.drivers

import org.apache.spark.sql.{DataFrame, SparkSession}
import com.databasetoiceberg.utils.DbConnector
import com.databasetoiceberg.common.IcebergUtils

class MySQLDriver(spark: SparkSession, dbParams: Map[String, String]) {

  def executeQuery(query: String, databaseName: String): Unit = {
    val jdbcUrl = s"jdbc:mysql://${dbParams("host")}:${dbParams("port")}/${dbParams("database")}"
    val connectionProperties = new java.util.Properties()
    connectionProperties.put("user", dbParams("user"))
    connectionProperties.put("password", dbParams("password"))

    val df: DataFrame = spark.read
      .jdbc(jdbcUrl, s"($query) as query", connectionProperties)

    IcebergUtils.writeToIceberg(df, databaseName, dbParams("icebergTable"))
  }
}