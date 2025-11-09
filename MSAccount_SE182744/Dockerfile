FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/MSAccount_SE182744-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]