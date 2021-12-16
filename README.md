# mutants-meli
Mutants Repo for Meli Challenge

# Hosting


# Enunciado
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

`boolean isMutant(String[] dna);` // Ejemplo Java

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de
(NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales
representan cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.
Ejemplo (Caso mutante):
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}; En
este caso el llamado a la función isMutant(dna) devuelve “true”.

Desarrolla el algoritmo de la manera más eficiente posible.

Desafíos:
1. Programa (Java, Golang, .Net, NodeJS) que cumpla con el método pedido por Magneto.
2. Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine, Amazon
AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante
enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente
formato:
`POST → /mutant/{“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }`
En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden
3. Anexar una base de datos, la cual guarde los ADN’s verificados con la API. Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las
verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
(ratio = mutants / humans)
Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón
de peticiones por segundo).

Entregables:
- Código fuente en GitHub (Repositorio privado al cual se pedirá acceso)
- Instrucciones de cómo ejecutar el programa en local y cloud en README.md dentro del
repositorio.
- Pruebas unitarias con una cobertura > 80% con reporte
- Documentación que considere apropiada teniendo en cuenta que es una entrega de un
producto en producción pero también una prueba técnica (todo dentro del proyecto con los
enlaces apropiados, evitando archivos por correo u otros medios).
- Tener en cuenta buenas prácticas de desarrollo, calidad de código y patrones.
- Incluya todos los artefactos que haya utilizado durante el proceso para garantizar la calidad
del producto.

# Resultados
## Stack Utilizado
- SpringBoot 2.6
- Java 11
- Docker
- DynamoDB
- ECS
- ECR
- Intellij
- Junit
- Jacoco 
----
#### Nivel 1.
Para el algoritmo se realiza la búsqueda de los patrones de ADN `AAAA` `TTTT` `CCCC` `GGGG` en las cadenas de texto de ADN formado
por los caracteres ubicados en todas las direcciones de la matriz (horizontal, vertical, diagonal principal, secundaria, diagonal superior y diagonal inferior),
evitando realizar iteraciones innecesarias para cadenas de texto menores a 4 y búsquedas cuando se encuentra más de dos coincidencias para determinar un mutante.
Por cada cadena de ADN se realiza una búsqueda en cada dirección, por lo que el costo computacional se puede determinar como O(n).


#### Nivel 2
Para la creación de la API REST se crean un proyecto springboot separado endpoint `POST /mutant`, en springboot el cuál verifica si una cadena ingresada es mutante o no, para lo cuál retorna `200 ok `
si es mutante y `403 Forbidden` si no lo es.

![image](https://user-images.githubusercontent.com/7538150/146302028-562f26ca-9a7a-44dc-93c9-21be8452dd11.png)

![image](https://user-images.githubusercontent.com/7538150/146302078-4a9600ed-633c-475e-8086-dff7fc88ff52.png)


Se agrega documentación de la API, la cual se puede encontrar en http://localhost:8080/swagger-ui.html

#### Nivel 3
Para almacenar los ADN verificados, se hace uso de una Base de Datos No Relacional DynamoDB cuyo partitionKey es el ADN verificado, ya que se debe almacenar un registro por ADN.
Se expone un endpoint adicional `GET /stats` el cual retorna un json con las estadisticas de las verificaciones de ADN

![image](https://user-images.githubusercontent.com/7538150/146302107-3b57ffdd-bba7-4dbe-a6b6-150cce46be5b.png)

El diagrama de infraestructura es la siguiente:
![image](https://user-images.githubusercontent.com/7538150/146302738-ce65fcf1-3db8-468a-aec9-42d3572bba33.png)


## Entregables
#### Ejecución del proyecto local
- Clonar el repositorio 
  `git clone https://github.com/alexis0592/mutants-meli.git`
- Abrir el proyecto en el IDE de preferencia(Se recomienda Intellij)
- Actualizar dependencias de Gradle

#### Pruebas unitarias
Se realizaron pruebas unitarias con Junit y reporte de cobertura con Jacoco con un 87% de cobertura

![image](https://user-images.githubusercontent.com/7538150/146304468-3cf966d3-48e4-41b9-932b-bc207e868a31.png)

![image](https://user-images.githubusercontent.com/7538150/146304431-98a3d271-a792-4f0a-ba13-b59e0ed93905.png)

#### Performance
Se realizan pruebas locales al servicio con jmeter (proyecto se puede encontrar en la carpeta performance-test) y se realiza un monitoreo con la herramienta VisualVM para determinar los recursos que necesitara el contenedor para ser desplegado en nube.
Teniendo en cuenta las limitaciones que se tienen con la base de datos de Dynamos antes de que pasar el limite de request se encuentra lo siguiente:

Se logra alcanzar en promedio 230 TPS con picos de 300 para una instancia
![image](https://user-images.githubusercontent.com/7538150/146306300-d529e389-e8c8-4592-8341-091eb698d496.png)

Con un consumo de memoria de 200MB y 40% de uso de CPU
![image](https://user-images.githubusercontent.com/7538150/146306370-acbe4aec-890e-4412-81c0-ed925744764f.png)

![image](https://user-images.githubusercontent.com/7538150/146306380-d2bd7775-a10e-45bb-8068-eb25ec7213be.png)



