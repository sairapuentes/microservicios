  # ========================
  # Configuracion multietapa donde se va a compilar el microservicio y creara el .jar
  # ========================
 FROM maven:3.9.6-eclipse-temurin-17 AS build
 WORKDIR /app
 COPY . .
 RUN mvn clean package -pl eureka -am -DskipTests

 FROM eclipse-temurin:17-jre
 WORKDIR /app
 COPY --from=build /app/eureka/target/*.jar app.jar
 #COPY target/*.jar app.jar
 EXPOSE 8761
 ENTRYPOINT ["java","-jar","app.jar"]