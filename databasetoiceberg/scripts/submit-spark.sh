#!/bin/bash

# Check if the required parameters are provided
if [ "$#" -ne 5 ]; then
    echo "Usage: $0 <database_type> <db_url> <username> <password> <query>"
    exit 1
fi

DATABASE_TYPE=$1
DB_URL=$2
USERNAME=$3
PASSWORD=$4
QUERY=$5

# Submit the Spark application to the Kubernetes cluster
kubectl run spark-submit \
    --image=<your-spark-image> \
    --restart=Never \
    -- spark-submit \
    --class com.databasetoiceberg.common.SparkApp \
    --master k8s://<your-k8s-api-server> \
    --conf spark.kubernetes.namespace=<your-namespace> \
    --conf spark.executor.instances=2 \
    --conf spark.kubernetes.container.image=<your-spark-image> \
    --conf spark.hadoop.fs.s3a.access.key=<your-minio-access-key> \
    --conf spark.hadoop.fs.s3a.secret.key=<your-minio-secret-key> \
    --conf spark.hadoop.fs.s3a.endpoint=<your-minio-endpoint> \
    --conf spark.hadoop.hive.metastore.uris=<your-hive-metastore-uri> \
    --conf spark.sql.catalog.spark_catalog=org.apache.iceberg.spark.SparkCatalog \
    --conf spark.sql.catalog.spark_catalog.type=hive \
    --conf spark.sql.catalog.spark_catalog.warehouse=<your-warehouse-path> \
    --conf spark.sql.extensions=org.apache.iceberg.spark.extensions.IcebergSparkSessionExtensions \
    --conf spark.sql.parquet.enableVectorizedReader=false \
    --conf spark.sql.iceberg.handle-timestamp-without-timezone=true \
    --conf spark.sql.iceberg.write.format=parquet \
    --conf spark.sql.iceberg.write.metadata.format=parquet \
    --conf spark.sql.iceberg.write.data.format=parquet \
    --conf spark.sql.iceberg.write.partitioned.by=<your-partition-columns> \
    --conf spark.sql.iceberg.write.database=<your-database-name> \
    --conf spark.sql.iceberg.write.query="$QUERY" \
    --conf spark.sql.iceberg.write.username="$USERNAME" \
    --conf spark.sql.iceberg.write.password="$PASSWORD" \
    --conf spark.sql.iceberg.write.db.url="$DB_URL" \
    --conf spark.sql.iceberg.write.db.type="$DATABASE_TYPE" \
    --conf spark.sql.iceberg.write.driver=<your-db-driver> \
    -- \
    --databaseType "$DATABASE_TYPE" \
    --dbUrl "$DB_URL" \
    --username "$USERNAME" \
    --password "$PASSWORD" \
    --query "$QUERY"

# Clean up the pod after execution
kubectl delete pod spark-submit