package com.databasetoiceberg.drivers

import org.apache.spark.sql.SparkSession
import com.databasetoiceberg.utils.DbConnector
import com.databasetoiceberg.common.IcebergUtils

class MSSQLDriver(spark: SparkSession, dbParams: Map[String, String]) {

  private val jdbcUrl = s"jdbc:sqlserver://${dbParams("host")}:${dbParams("port")};databaseName=${dbParams("database")}"
  private val query = dbParams("query")
  private val minioPath = dbParams("minioPath")

  def execute(): Unit = {
    val df = DbConnector.readFromDatabase(spark, jdbcUrl, dbParams("user"), dbParams("password"), query)
    IcebergUtils.writeToIceberg(df, minioPath)
  }
}