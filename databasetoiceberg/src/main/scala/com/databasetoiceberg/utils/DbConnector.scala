package com.databasetoiceberg.utils

import java.sql.{Connection, DriverManager, ResultSet}
import scala.util.{Failure, Success, Try}

class DbConnector {

  def connectToDatabase(dbType: String, url: String, user: String, password: String): Option[Connection] = {
    Try {
      dbType.toLowerCase match {
        case "oracle" => Class.forName("oracle.jdbc.driver.OracleDriver")
        case "postgresql" => Class.forName("org.postgresql.Driver")
        case "mysql" => Class.forName("com.mysql.cj.jdbc.Driver")
        case "mssql" => Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        case _ => throw new IllegalArgumentException("Unsupported database type")
      }
      DriverManager.getConnection(url, user, password)
    } match {
      case Success(connection) => Some(connection)
      case Failure(_) => None
    }
  }

  def executeQuery(connection: Connection, query: String): ResultSet = {
    val statement = connection.createStatement()
    statement.executeQuery(query)
  }

  def closeConnection(connection: Connection): Unit = {
    if (connection != null && !connection.isClosed) {
      connection.close()
    }
  }
}