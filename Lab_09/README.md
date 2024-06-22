# Conexion de base de datos con JDBC usando transacciones de Java.

## Pasos para Compilar y Ejecutar

**Para crear el contenedor con la base de datos**

   ```sh
   docker-compose up --build
   ```
**Para conexion a la base de datos por consola**

   ```sh
   docker exec -it mysql-container bash
   ```
   
   ```sh
   mysql -u root -p
   ```
   
   Ingresar la contraseña de MYSQL_ROOT_PASSWORD: rootpassword
   
   ```sql
   USE testdb;
   SHOW TABLES;
   SELECT * FROM mitabla;
   SELECT * FROM miotratabla;
   ```