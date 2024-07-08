# Sistema de gestion para una empresa

## Pasos para Compilar y Ejecutar

1. **Abre una terminal.**

   ```sh
   docker-compose up
   ```
3. **En otra terminal**
   
   Linea 10 - CreditCardServer.java

   ```java
      ./mvnw spring-boot:run
   ```

4. **Conectarse en**
```sh
http://localhost:8080/gestionEmpresa/departamentos

http://localhost:8080/gestionEmpresa/proyectos

http://localhost:8080/gestionEmpresa/ingenieros
```

## Adicionales
1. **Las sentencias sql que se ejecuan al crear la base de datos estan en**
```sh
   init.sql
```

1. **Para usar PostMan importar las siguientes colecciones**
```sh
GestionEmpresa.postman_collection.json
```