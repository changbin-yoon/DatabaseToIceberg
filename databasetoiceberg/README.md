# databasetoiceberg Project

## Overview
The `databasetoiceberg` project is designed to facilitate the extraction of data from various relational databases (Oracle, PostgreSQL, MySQL, and MSSQL) and store it in MinIO in Parquet + Iceberg format. The project utilizes Apache Spark and operates within a Kubernetes environment using the Spark operator.

## Features
- Connects to multiple databases and executes user-defined queries.
- Stores results in MinIO using Parquet + Iceberg format.
- Utilizes Hive Metastore as the catalog for Iceberg tables.
- Supports configuration through external files for database access and parameters.

## Project Structure
```
databasetoiceberg
├── src
│   ├── main
│   │   ├── scala
│   │   │   └── com
│   │   │       └── databasetoiceberg
│   │   │           ├── drivers
│   │   │           │   ├── OracleDriver.scala
│   │   │           │   ├── PostgresDriver.scala
│   │   │           │   ├── MySQLDriver.scala
│   │   │           │   └── MSSQLDriver.scala
│   │   │           ├── common
│   │   │           │   ├── SparkApp.scala
│   │   │           │   └── IcebergUtils.scala
│   │   │           └── utils
│   │   │               └── DbConnector.scala
│   │   ├── java
│   │   │   └── com
│   │   │       └── databasetoiceberg
│   │   │           └── helpers
│   │   │               └── JavaHelpers.java
│   │   └── resources
│   │       ├── application.conf
│   │       └── log4j.properties
├── k8s
│   └── spark-application-cr.yaml
├── docker
│   └── Dockerfile
├── ci
│   ├── Jenkinsfile
│   └── pipeline.groovy
├── scripts
│   ├── build.sh
│   └── submit-spark.sh
├── conf
│   └── example-db-params.conf
├── pom.xml
├── README.md
├── .gitignore
└── LICENSE
```

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd databasetoiceberg
   ```

2. **Build the Project**
   Use Maven to build the project and create the JAR file:
   ```bash
   mvn clean package
   ```

3. **Build the Docker Image**
   Navigate to the `docker` directory and build the Docker image:
   ```bash
   cd docker
   docker build -t databasetoiceberg:latest .
   ```

4. **Deploy to Kubernetes**
   Ensure you have the Spark operator installed in your Kubernetes cluster. Then, apply the Custom Resource definition:
   ```bash
   kubectl apply -f k8s/spark-application-cr.yaml
   ```

## Usage
To run the application, you need to provide the necessary database access parameters, including:
- Database type (Oracle, PostgreSQL, MySQL, MSSQL)
- Database host
- Database port
- Database name
- Username
- Password
- Query to execute
- Target database name for storage in MinIO

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.