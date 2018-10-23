## Proyecto Base Servidor Web (Spring-Postgresql)
### Requisitos
1. EclipseIDE o STS(Spring Tools Suite)
2. Java 1.8
3. Gradle
4. Project Loombok
5. SonarLint
6. EditorConfig

### Instalación

Para poder generar el proyecto correctamente debe ejecutar los siguientes comandos:

Ubicarse en el directorio del proyecto.
```
cd directorioProyecto/
```
Generar configuraciones de eclipseIDE.
```
gradle eclipse
```

Una vez generadas las configuraciones se procede a Importar el proyecto mediante eclipseIDE.

A continuación proceda a cambiar el nombre de los paquetes y las referencia a los mismos de acuerdo al nombre de su proyecto.

### Configuración

La configuraciones básicas del proyecto se encuentra en el archivo properties de la carpeta resources

```
directorioProyecto/src/main/resources/
```

Una vez localizado el archivo debe configurar el nombre de la base de datos y la contraseña de su motor de Base de datos
```

spring.datasource.url=jdbc:postgresql://localhost:5432/nombreBDD
spring.datasource.password=xxxxxxxxxxxxxxxxxx
```

En caso de utilizar las funciones de envío de emails, cambie las configuraciones acorde a sus necesidades 
```
spring.mail.host = smtp.gmail.com
spring.mail.username = username@gmail.com
spring.mail.password = xxxxxxxxxxxxxxxxxx
spring.mail.port=587
```

### Migración de Base de Datos

Antes de ejecutar el proyecto procesada a realizar las respectivas migraciones iniciales de la base de datos.

Las migraciones de ejemplo están ubicadas en la carpeta changelog ubicada dentro de la carpeta db
```
directorioProyecto/src/main/resources/db/changelog
```

Las migraciones constantan de 2 carpetas adcionales que son sql y versions

Las migraciones se deben ubicar dentro de la carpeta versions y desarrollar en un archivo con extensión .yaml

Si desea incluir un script sql, se debe ubicar en la carpeta sql

### Ejecución del Proyecto

Para ejecutar el proyecto dentro del IDE 

```
ejecutar como aplicación SpringBOOT
```

Si desea ejecutar el proyecto por medio de la consola
```
gradle bootRun
```

### CI/CD

Para ejecutar la integración continua se debe tomar seguir los siguientes pasos:

*Solicitar PIPE de Jenkins
*Actualizar el cliente en la rama correspondiente
*Enviar cambios de servidor a la rama correspondiente para disparar el PIPE
*No olvidar poner los respectivos tags de versionamiento 

```
git push --tags origin rama
```

### Proyecto Lombook

Lombook es una herramienta que ayuda a mantener limpio nuestro código fuente, la librería se encuentra incluida en el proyecto pero también se puede instalar el IDE para que no muestre errores mientras se desarrolla.
Los pasos para instalar son:
1. Ir a la página del Proyeto Loombok.
2. Descargar el archivo ejecutable jar.
3. Ejecutar la aplicación.
4. Seleccionar la ubicación de nuestro IDE.
5. Presionar el botón de instalación.

### SonarQube
Es una plataforma para evaluación continua de la calidad de código, dispone del plugin para Eclipse SonarLint.
Para conectar con SonarQube se debe instalar el plugin SonarLint desde el marketplace de Eclipse, en la configuración se debe colocar el servidor configurado en Yachay.


### Ayuda
Para mayor ayuda referirse a la documentación:
1. [Gradle] (https://gradle.org) 
3. [Spring] (https://projects.spring.io/spring-boot/)
4. [STS] (https://spring.io/tools/eclipse)
5. [Liquibase] (http://www.liquibase.org/)
6. [Lombok Project] (https://projectlombok.org/)
7. [SonarQube] (https://www.sonarqube.org/)

