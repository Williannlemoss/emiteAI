FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /usr/src/app
COPY "./emiteai-0.0.1-SNAPSHOT.jar" .
ENTRYPOINT ["java", "-jar", "emiteai-0.0.1-SNAPSHOT.jar"]