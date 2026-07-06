  # ========================
  # Configuracion multietapa donde se va a compilar el microservicio y creara el .jar
  # ========================
 FROM maven:3.9.6-eclipse-temurin-17 AS build
 WORKDIR /app
 COPY . .
 RUN mvn clean package -pl login -am -DskipTests

 FROM eclipse-temurin:17-jre
 WORKDIR /app
 COPY --from=build /app/login/target/*.jar app.jar
 #COPY target/*.jar app.jar
 EXPOSE 8010
 ENTRYPOINT ["java","-jar","app.jar"]