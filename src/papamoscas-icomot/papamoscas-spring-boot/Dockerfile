#La imagen de partida es la imagen de JHispter.
FROM java:7

#Establece el directorio donde se van a ejecutar los comandos. 
WORKDIR /

#Creamos el directorio
RUN mkdir papamoscas

#ADD files
ADD target/*.war papamoscas/ 

#Cambiar al directorio de la aplicacion
WORKDIR papamoscas

#Ejecutamos el mvn spring-boot:run --spring.profiles.active=prod
CMD  java -jar papamoscas-spring-boot.war 