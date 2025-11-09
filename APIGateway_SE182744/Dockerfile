FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/APIGateway_SE182744-0.0.1-SNAPSHOT.jar app.jar
# Cổng chạy service
EXPOSE 9090

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
