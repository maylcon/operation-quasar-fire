<h1 align="center">
  <p align="center">Operación fuego de Quasar - MELI</p>
</h1>

## Introducción
El siguiente proyecto tiene como objetivo calcular las coordenadas de la nave que transporta raciones y armamento para la Alianza rebelde (Satos, Skywalker y Kenobi)  

## Funcionamiento
El servicio consta de 3 funcionalidades:
1. **topsecret - POST**
> Se encarga de obtener las coordenadas (X , Y) y el mensaje secreto que envian la Alianza rebelde. Recibe la información del body como el siguiente ejemplo  
```sh
{
  "satellites": [
    {
      "distance": 100.0,
      "message": [
        "este","","","mensaje",""
      ],
      "name": "kenobi"
    },
    {
      "distance": 115.5,
      "message": [
        "","es","","","secreto"
      ],
      "name": "skywalker"
    },
    {
      "distance": 142.7,
      "message": [
        "este","","un","",""
      ],
      "name": "sato"
    }
  ]
}
```
> Y responde la siguiente información   
```sh
{
  "position": {
    "x": -58.315252587138595,
    "y": -69.55141837312165
  },
  "message": "este es un mensaje secreto"
}
```
2. **topsecret_split - GET**
> Se encarga de obtener las coordenadas (X , Y) de un satelite de la Alianza rebelde. Se debe enviar en el path el nombre del satelite aliado (kenobi, skywalker o sato).  
3. **topsecret_split - POST**
> Se encarga de obtener las coordenadas (X , Y) de un satelite de la Alianza rebelde. Recibe la información del body así  
```sh
{
  "distance": 0,
  "message": [
    "string"
  ]
}
```
> Y responde la siguiente estructura   
```sh
{
  "message": "string",
  "position": {
    "x": 0,
    "y": 0
  }
}
```

## Librerias Especiales
Algunas de las librerias que se usaron para un mejor enfoque de desarrollo o rendimiento son las siguiente.

- **Lemmingapex**
> Resuelve una formulación del problema de trilateración espacial n-D utilizando un optimizador de mínimos cuadrados no lineal.
- **Swagger**
> Cuando hablamos de Swagger nos referimos a una serie de reglas, especificaciones y herramientas que nos ayudan a documentar nuestras APIs. De esta manera, podemos realizar documentación que sea realmente útil para las personas que la necesitan. Swagger nos ayuda a crear documentación que todo el mundo entienda.  
- **Lombok**
> Project Lombok es una biblioteca de Java que se conecta automáticamente a su editor y crea herramientas, lo que le da sabor a su Java . Nunca vuelva a escribir otro método getter o equals, con una anotación su clase tiene un generador con todas las funciones, automatice sus variables de registro y mucho más.  

## Documentación
**[Lemmingapex](https://github.com/lemmingapex/trilateration)**  
**[Swagger](https://swagger.io/tools/open-source/open-source-integrations/)**  
**[Lombok](https://projectlombok.org/)**  
**[Instalación lombok](https://www.digitalocean.com/community/tutorials/java-project-lombok)**  

## Imagenes de referencia  
![](https://raw.githubusercontent.com/maylcon/operation-quasar-fire/main/swagger-quasar.PNG)  

## Servicio desplegado en una instancia de Amazon (ubuntu)  
[Operación fuego de Quasar](http://54.89.86.252:8080/swagger-ui.html)

## Colaboración

Maylcon Sanson Ramirez Saray
Ing. Esp Ingenieria de Software