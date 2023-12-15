# Prueba_Credibanco
## Descripción del Proyecto - Bank Inc Card System

El Bank Inc Card System es un robusto sistema de gestión de tarjetas de débito y crédito desarrollado utilizando Java 11 y el framework Spring 2.7.14. Diseñado para la eficiente emisión, activación, y gestión de tarjetas, así como para facilitar transacciones seguras y recargas de saldo.

## Características Principales:

Generación y Activación de Tarjetas:

Utilizando el identificador de producto, se genera el número de tarjeta.
Proceso de activación para garantizar la seguridad de las transacciones.
Datos de Tarjetas:

Números de tarjeta de 16 dígitos, con los primeros 6 representando el identificador del producto.
Información del titular de la cuenta (nombre y apellido).
Fecha de vencimiento automáticamente establecida para 3 años posteriores a la creación.
Exclusividad en transacciones en dólares.
Recarga de Saldo y Transacciones:

Saldo inicial de tarjetas configurado en cero pesos, con la opción de realizar recargas.
Condiciones para realizar compras: saldo suficiente, vigencia de la tarjeta, activación, y no bloqueo.
## Tecnologías Utilizadas:

Desarrollado en Java 11 y Spring Framework 2.7.14 para una arquitectura moderna y escalable.
Base de datos PostgreSQL para el almacenamiento seguro y eficiente de la información.

## Despliegue en AWS EC2 y RDS:

El sistema está diseñado para desplegarse en instancias de Amazon EC2 y la base de datos en RDS, aprovechando la flexibilidad y escalabilidad de la nube de Amazon Web Services.
El Bank Inc Card System representa una solución integral para instituciones financieras que buscan una plataforma segura y eficiente para la gestión de tarjetas. Con tecnologías de vanguardia y un enfoque en la seguridad, este sistema es capaz de adaptarse a las necesidades cambiantes del entorno financiero, proporcionando una experiencia de usuario optimizada y confiable.

## Diagrama Base de datos
Image:

![](https://github.com/Deadmanw/Prueba_Credibanco/blob/main/BankIncER.png)

> Diagrama relacional.

## Swagger
En el siguiente enlace encontraremos toda la informacion de los servicios creados, tipo de peticion y request y responses segun corresponda

http://18.191.84.143/swagger-ui/index.html

## Colecion Postman
Encontraremos el listado de peticiones tanto en local como al servicio desplegado en AWS

https://github.com/Deadmanw/Prueba_Credibanco/blob/main/BankInc.postman_collection.json

## Despliegue local

### Requisitos Previos:

Asegúrate de tener Java JDK 11 instalado.
Descarga e instala Maven para construir tu proyecto.
### Clona el Repositorio:
    git clone https://github.com/Deadmanw/Prueba_Credibanco.git

cd /Prueba credibanco/prueba
### Construye la Aplicación:
    mvn clean install

### Ejecuta la Aplicación:

    mvn spring-boot:run
### Accede a la Aplicación:

Visita http://localhost:8089 en tu navegador web o utiliza la coleccion postman ya mencionada.
