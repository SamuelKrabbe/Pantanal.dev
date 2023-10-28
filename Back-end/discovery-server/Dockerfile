FROM eclipse-temurin:17-jdk as builder
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package
RUN cp target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17-jre
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 8761
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]