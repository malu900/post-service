FROM maven:3.6.3-openjdk-17-slim as build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests
# production stage
FROM openjdk:17-slim
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","app.jar"]