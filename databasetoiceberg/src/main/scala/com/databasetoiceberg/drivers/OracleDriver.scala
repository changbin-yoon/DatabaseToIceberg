package com.databasetoiceberg.drivers

import org.apache.spark.sql.{DataFrame, SparkSession}
import com.databasetoiceberg.common.IcebergUtils
import com.databasetoiceberg.utils.DbConnector

class OracleDriver(spark: SparkSession, dbParams: Map[String, String]) {

  private val jdbcUrl = s"jdbc:oracle:thin:@${dbParams("host")}:${dbParams("port")}:${dbParams("serviceName")}"
  private val query = dbParams("query")
  private val icebergTable = dbParams("icebergTable")

  def execute(): Unit = {
    val df: DataFrame = DbConnector.readFromDatabase(spark, jdbcUrl, dbParams("user"), dbParams("password"), query)
    IcebergUtils.writeToIceberg(df, icebergTable)
  }
}