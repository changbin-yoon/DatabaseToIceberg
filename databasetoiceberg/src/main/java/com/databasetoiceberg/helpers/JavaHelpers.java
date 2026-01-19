package com.databasetoiceberg.helpers;

import java.util.Properties;

public class JavaHelpers {

    public static Properties createDbProperties(String user, String password) {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        return properties;
    }

    public static String buildJdbcUrl(String dbType, String host, String port, String dbName) {
        switch (dbType.toLowerCase()) {
            case "oracle":
                return String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, dbName);
            case "postgresql":
                return String.format("jdbc:postgresql://%s:%s/%s", host, port, dbName);
            case "mysql":
                return String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
            case "mssql":
                return String.format("jdbc:sqlserver://%s:%s;databaseName=%s", host, port, dbName);
            default:
                throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }

    public static void logConnectionDetails(String dbType, String host, String dbName) {
        System.out.println("Connecting to " + dbType + " database at " + host + " for database: " + dbName);
    }
}