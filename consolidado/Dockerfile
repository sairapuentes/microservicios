  # ========================
  # Configuracion multietapa donde se va a compilar el microservicio y creara el .jar
  # ========================
 #Como ya se creo el .jar con el mvn clean package se comentan las lineas 5,6,7,8 y 12
 #FROM maven:3.9.6-eclipse-temurin-17 AS build
 #WORKDIR /app
 #COPY ..
 #RUN mvn clean package -DskipTests

 FROM eclipse-temurin:17-jdk
 WORKDIR /app
 #COPY --from=build /app/target/*.jar app.jar
 COPY target/*.jar app.jar
 EXPOSE 8014
 ENTRYPOINT ["java","-jar","app.jar"]