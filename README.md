Proyecto Cliente-Servidor en Java


Descripción
Este proyecto implementa un sistema básico de cliente-servidor en Java que permite realizar operaciones matemáticas simples (suma, resta, multiplicación y división) a través de una conexión de red. El servidor recibe solicitudes del cliente y devuelve los resultados correspondientes.

Estructura del Proyecto
Server.java: Código fuente del servidor que escucha conexiones, procesa solicitudes de operaciones matemáticas y envía respuestas al cliente.
Client.java: Código fuente del cliente que se conecta al servidor, permite al usuario introducir operaciones y números, y muestra los resultados.


Cómo Ejecutar
Compilar el Proyecto: Asegúrate de que el JDK esté instalado y configura tu entorno de desarrollo. Abre una terminal y navega hasta la carpeta del proyecto.


Abrir Terminal
javac Server.java 
javac Client.java


Ejecutar el Servidor: En una terminal, ejecuta el servidor:
--java Server
El servidor comenzará a escuchar en el puerto 12345.


Ejecutar el Cliente: Abre otra terminal y ejecuta el cliente:
--java Client
El cliente se conectará al servidor y te pedirá que ingreses operaciones y números.
