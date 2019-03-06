FROM openjdk

MAINTAINER Roman Kagan 

RUN apt-get update

RUN apt-get install -y maven

RUN git clone https://github.com/KaganRoman/Argus

RUN mvn -f ./Argus clean package

EXPOSE 8080

WORKDIR ./Argus
ENTRYPOINT ["./mvnw", "spring-boot:run"]
