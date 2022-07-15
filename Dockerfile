From openjdk:8
copy ./target/itrepair-0.0.1-SNAPSHOT.jar itrepair-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","itrepair-0.0.1-SNAPSHOT.jar"]