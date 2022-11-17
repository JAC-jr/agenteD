FROM 180.183.170.62:4443/oracle/serverjre:8
VOLUME /tmp
VOLUME /keys
ADD configuracion/Worker/docker/keys /keys
ADD aplicaciones/credit/config /
ADD aplicaciones/credit/credit-0.0.1-SNAPSHOT.jar CREDIT.jar
ENTRYPOINT ["java","-Xms512m","-Xmx512m","-Duser.timezone=America/Caracas","-XX:+DisableExplicitGC","-XX:+UseG1GC","-Dspring.profiles.active=qa","-Djava.security.egd=file:/dev/./urandom","-jar","CREDIT.jar"]
