# Sistema de Tarjetas de Crédito con RMI

Este proyecto implementa un sistema simple de tarjetas de crédito utilizando Java RMI (Remote Method Invocation). Los usuarios pueden autorizar transacciones, consultar saldos, añadir fondos y obtener detalles de las tarjetas de crédito.

## Estructura del Proyecto

- `CreditCardServiceInterface.java`: Define la interfaz remota.
- `CreditCardServiceImpl.java`: Implementa la lógica del servicio remoto.
- `CreditCardServer.java`: Configura y lanza el servidor RMI.
- `CreditCardClient.java`: Cliente para interactuar con el servicio remoto.
- `CreditCard.java`: Clase que representa una tarjeta de crédito.

## Pasos para Compilar y Ejecutar

### 1. Compilar el Código

1. **Abre una terminal.**
2. **Navega al directorio del proyecto.**
3. **Compila todas las clases del proyecto:**

   ```sh
   javac -d . CreditCardSystem/*.java
   ```

Este comando compilará las clases y las ubicará en el directorio correspondiente según el paquete.

### 2. Generar los Esqueletos y Componentes Sustitutos
Utiliza rmic para generar los esqueletos y los stubs:

```sh
rmic CreditCardSystem.CreditCardServiceImpl
```

Esto generará los archivos necesarios para la comunicación RMI.

### 3. Iniciar el Registro RMI
Inicia el registro RMI en el puerto 1099:

```sh
rmiregistry 1099 &
```
El símbolo & se usa para ejecutar el comando en segundo plano.

### 4. Ejecutar el Servidor
Ejecuta el servidor para registrar el servicio:

```sh
java CreditCardSystem.CreditCardServer
```
Deberías ver el mensaje "CreditCardService is running..." indicando que el servidor está listo.

### 5. Ejecutar el Cliente
En una nueva terminal, ejecuta el cliente para interactuar con el servicio remoto:

```sh
java CreditCardSystem.CreditCardClient
```
Sigue las instrucciones en pantalla para interactuar con el sistema.

## Funcionamiento del Cliente

El cliente presenta un menú con las siguientes opciones:
1. Autorizar una transacción
2. Consultar saldo
3. Añadir fondos
4. Imprimir detalles de la tarjeta

### Ejemplo de Uso

1. **Selecciona una opción del menú ingresando el número correspondiente.**
2. **Ingresa el número de tarjeta cuando se te solicite.**
3. **Ingresa el monto u otros detalles según la opción seleccionada.**
4. **El sistema responderá con la información solicitada o confirmación de la operación.**

## Notas Adicionales

- **Asegúrate de que el registro RMI esté ejecutándose antes de iniciar el servidor.**
- **Si cambias el puerto o la dirección, actualiza los valores correspondientes en el código.**
- **Para detener el registro RMI, usa `kill` en el proceso correspondiente (en Linux/Mac) o cierra la terminal donde se ejecutó (en Windows).**

## Problemas Comunes

- **Exception: java.rmi.ConnectException**: Asegúrate de que el registro RMI esté ejecutándose y que el puerto especificado sea correcto.
- **ClassNotFoundException**: Verifica que todas las clases estén correctamente compiladas y ubicadas en el directorio correcto.
