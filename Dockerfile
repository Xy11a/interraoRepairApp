FROM adoptopenjdk/openjdk11:latest
copy ./target/itrepair-0.0.2-SNAPSHOT.jar itrepair-0.0.2-SNAPSHOT.jar
CMD ["java","-jar","itrepair-0.0.3-SNAPSHOT.jar"]