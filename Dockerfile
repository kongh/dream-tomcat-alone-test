FROM openjdk:8
COPY ./target/dream-tomcat-alone-test-1.0-SNAPSHOT.jar /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ["java", "-jar ${WORKDIR}/dream-tomcat-alone-test-1.0-SNAPSHOT.jar"]