# Sistema de Tarjetas de Crédito con RMI

Este proyecto implementa un sistema simple de tarjetas de crédito utilizando Java RMI (Remote Method Invocation). Los usuarios pueden autorizar transacciones, consultar saldos, añadir fondos y obtener detalles de las tarjetas de crédito.

## Estructura del Proyecto

- `CreditCardServiceInterface.java`: Define la interfaz remota.
- `CreditCardServiceImpl.java`: Implementa la lógica del servicio remoto.
- `CreditCardServer.java`: Configura y lanza el servidor RMI.
- `CreditCardClient.java`: Cliente para interactuar con el servicio remoto.
- `CreditCard.java`: Clase que representa una tarjeta de crédito.

## Pasos para Compilar y Ejecutar

1. **Abre una terminal.**
2. **Navega al directorio del proyecto (SD_GRUPO4_2024A).**
3. **Compila todas las clases del proyecto:**

   ```sh
   javac Lab_04/Tarjetas_Credito/*.java
   ```

Este comando compilará las clases y las ubicará en el directorio correspondiente según el paquete.

3. **Cambiar el segundo valor por el ip del equipo**
   
   Linea 10 - CreditCardServer.java

   ```java
      //Segundo valor es la ip del equipo
      System.setProperty("java.rmi.server.hostname","192.168.56.1");
   ```

3. **Ejecutar Servidos y Cliente**

Ejecuta el servidor para registrar el servicio:

```sh
java Lab_04.Tarjetas_Credito.CreditCardServer
```

Deberías ver el mensaje "CreditCardService is running..." indicando que el servidor está listo.

En una nueva terminal, ejecuta el cliente para interactuar con el servicio remoto:

```sh
java Lab_04.Tarjetas_Credito.CreditCardClient
```