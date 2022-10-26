FROM 180.183.170.62:4443/base-openjdk:8
ADD target/*.jar agenteD.jar
ENTRYPOINT ["java","-Xms512m","-Xmx512m","-Doracle.jdbc.defaultNChar=true",
"-Duser.timezone=America/Caracas","-XX:+DisableExplicitGC","-XX:+UseG1GC",
"-XX:ParallelGCThreads=4","-XX:G1HeapWastePercent=10","-XX:G1HeapRegionSize=20",
"-Dspring.profiles.active=qa","-Djava.security.egd=file:/dev/./urandom","-jar","agenteD.jar"]
