# PC_Crawler
_Bienvenido a nuestro proyecto cooperativo de PC_Crawler. Actualmente se encuentra en desarrollo por lo que es posible que si se intenta ejecutar de errores. Se trata de un proyecto realzado con el IDE de Eclipse_
## Introducción

Un crawler web es un programa que analiza los documentos de los
sitios web. Los motores de búsqueda, como Google, Bing, DuckDuckGo…
los utilizan para analizar los sitios web y crear una base de datos
con la información recolectada.

A los crawlers también se les conoce como rastreadores. Tiene
lógica, según la función que tienen.

Su funcionamiento básicamente es el siguiente: comienza visitando
una lista de URL, identifica los hiperenlaces y los añade a la lista
de URL para visitar de acuerdo a determinado conjunto de reglas. En
esas URL nuevas vuelve a identificar los nuevos hiperenlaces, y los
vuelve a añadir a la lista de URL a visitar: y así sucesivamente.

Tareas comunes que suelen realizar los crawler son las siguientes:

- Crear el índice de una máquina de búsqueda.
- Analizar los enlaces de una web en busca de links rotos.
- Recolectar información de un tipo determinado (como pueden ser
precios de productos, códigos postales…).

Nosotros implementaremos un crawler para rastrear los archivos
locales dentro de una máquina. El crawler funcionará de la
siguiente forma:

- Se le dará un directorio con el que comenzar el rastreo
- Por cada archivo que contenga dicho directorio, lo analizará y
contará las diferentes 	palabras que haya en él, sumando la
aparición de cada palabra nueva.
- Por cada directorio, lo añadirá a una cola para tratarlo más
adelante.
- Generará un TreeMap que almacenará todos los pares clave-valor. Siendo la clave la palabra y el valor la frecuencia (número de apariciones).
- Controlamos la actualización de este TreeMap: cada vez que realizamos un análisis guardamos en "metadata.txt" el directorio y la fecha y horas de el último análisis.
- También posee una función de busqueda: al introducir la palabra muestra la frecuencia de esta.
- Hemos hecho una "pequeña interfaz" con swing que hace intuitiva la tarea del análisis.
## Pre-requisitos
Si se desea compilar el código en eclipse o en otro IDE es
necesario:

- jdk Java-8: [Descargar aquí](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- Librería commons-io de Apache: [Descargar aquí](https://mvnrepository.com/artifact/commons-io/commons-io/2.6)
## Ejecutable
También hemos creado un ejecutable (.jar) que se encuentra en el siguiente enlace:
https://drive.google.com/file/d/1zQuhwGMnmsuMOwHmK9U9q2xBGt_3-Ds0/view?usp=sharing
## Fuentes
https://en.wikipedia.org/wiki/Web_crawler

https://es.ryte.com/wiki/Crawler
## Blogs
Gonzalo: https://madiba.unex.es/ribw1920/a06/
kike: https://madiba.unex.es/ribw1920/a13/
