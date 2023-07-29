FROM openjdk:17-alpine
WORKDIR /usr/src/app
ARG JAR_PATH=./build/libs
COPY ${JAR_PATH}/CabbageMarket-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]