FROM openjdk:17-alpine

WORKDIR /certivale/api/

RUN mkdir config

COPY ./build/libs/TICKETS-API.jar ./TicketsApi.jar

ENTRYPOINT ["java", "-jar", "TicketsApi.jar"]