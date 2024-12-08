FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/atencion-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_XK76QUD2USJP3V99 /app/oracle_wallet
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]