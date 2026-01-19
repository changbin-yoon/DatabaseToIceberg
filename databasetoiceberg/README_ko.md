# databasetoiceberg 프로젝트

## 개요
`databasetoiceberg` 프로젝트는 Oracle, PostgreSQL, MySQL, MSSQL 같은 다양한 관계형 데이터베이스에서 데이터를 추출하여 Parquet + Iceberg 형태로 MinIO에 저장하는 기능을 제공합니다. Apache Spark를 사용하며 Kubernetes 환경에서 Spark Operator로 실행됩니다.

## 기능
- 여러 데이터베이스에 연결해 사용자가 정의한 쿼리를 실행.
- 결과를 Parquet + Iceberg 형식으로 MinIO에 저장.
- Iceberg 테이블의 카탈로그로 Hive Metastore 사용.
- 데이터베이스 접근 및 파라미터를 외부 설정 파일로 관리.

## 프로젝트 구조
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

## 설정 방법
1. 리포지토리 클론
```bash
git clone <repository-url>
cd databasetoiceberg
```

2. 프로젝트 빌드
```bash
mvn clean package
```

3. 도커 이미지 빌드
```bash
cd docker
docker build -t databasetoiceberg:latest .
```

4. Kubernetes에 배포
Spark Operator가 클러스터에 설치되어 있는지 확인한 후 CR 적용:
```bash
kubectl apply -f k8s/spark-application-cr.yaml
```

## 사용법
애플리케이션 실행 시 다음 데이터베이스 접근 파라미터를 제공해야 합니다:
- 데이터베이스 타입 (Oracle, PostgreSQL, MySQL, MSSQL)
- 호스트
- 포트
- 데이터베이스 이름
- 사용자명
- 비밀번호
- 실행할 쿼리
- MinIO에 저장할 대상 데이터베이스(또는 테이블) 이름

## 기여
기여 환영합니다. 기능 개선이나 버그 수정은 풀 리퀘스트나 이슈를 통해 제출해 주세요.

## 라이선스
이 프로젝트는 MIT 라이선스입니다. 자세한 내용은 LICENSE 파일을 참고하세요.
```// filepath: /Users/ycb/study/DatabaseToIceberg/databasetoiceberg/README_ko.md
# databasetoiceberg 프로젝트

## 개요
`databasetoiceberg` 프로젝트는 Oracle, PostgreSQL, MySQL, MSSQL 같은 다양한 관계형 데이터베이스에서 데이터를 추출하여 Parquet + Iceberg 형태로 MinIO에 저장하는 기능을 제공합니다. Apache Spark를 사용하며 Kubernetes 환경에서 Spark Operator로 실행됩니다.

## 기능
- 여러 데이터베이스에 연결해 사용자가 정의한 쿼리를 실행.
- 결과를 Parquet + Iceberg 형식으로 MinIO에 저장.
- Iceberg 테이블의 카탈로그로 Hive Metastore 사용.
- 데이터베이스 접근 및 파라미터를 외부 설정 파일로 관리.

## 프로젝트 구조
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

## 설정 방법
1. 리포지토리 클론
```bash
git clone <repository-url>
cd databasetoiceberg
```

2. 프로젝트 빌드
```bash
mvn clean package
```

3. 도커 이미지 빌드
```bash
cd docker
docker build -t databasetoiceberg:latest .
```

4. Kubernetes에 배포
Spark Operator가 클러스터에 설치되어 있는지 확인한 후 CR 적용:
```bash
kubectl apply -f k8s/spark-application-cr.yaml
```

## 사용법
애플리케이션 실행 시 다음 데이터베이스 접근 파라미터를 제공해야 합니다:
- 데이터베이스 타입 (Oracle, PostgreSQL, MySQL, MSSQL)
- 호스트
- 포트
- 데이터베이스 이름
- 사용자명
- 비밀번호
- 실행할 쿼리
- MinIO에 저장할 대상 데이터베이스(또는 테이블) 이름

## 기여
기여 환영합니다. 기능 개선이나 버그 수정은 풀 리퀘스트나 이슈를 통해 제출해 주세요.

## 라이선스
이 프로젝트는 MIT 라이선스입니다. 자세한 내용은 LICENSE 파일을 참고하세요.