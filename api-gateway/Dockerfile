# DEFINIR IMAGEN/ENTORNO BASE PARA ARMADO DE PROYECTO
FROM openjdk:17-jdk-slim

# DEFINIR VARIABLES/PARAMETROS CONSTRUCCCION
ARG JAR_FILE=target/api-gateway-0.0.1.jar

# COPIA APP DENTRO DEL CONTENEDOR
COPY ${JAR_FILE} app_apigateway.jar

# EJECUTAR AL INICIAR CONTENEDOR
ENTRYPOINT ["java", "-jar", "app_apigateway.jar"]

# INDICAR PUERTO DISP COMUNICARNOS FUERA CONTENEDOR
# EXPOSE