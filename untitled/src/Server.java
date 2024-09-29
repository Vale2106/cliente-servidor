import java.io.*; // Importa clases para entrada/salida
import java.net.*; // Importa clases para comunicación de red
import java.text.DecimalFormat; // Importa clase para formateo de números

public class Server {
    public static void main(String[] args) {
        int port = 12345; // Define el puerto en el que el servidor escuchará conexiones
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // Crea un socket de servidor que escuchará en el puerto especificado
            System.out.println("Servidor escuchando en el puerto " + port);

            // Bucle infinito para aceptar conexiones de clientes
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    // Acepta una conexión de un cliente y crea un socket para la comunicación
                    System.out.println("Cliente conectado: " + clientSocket);

                    // Crear flujos de entrada y salida para la comunicación
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String request; // Variable para almacenar la solicitud del cliente
                    // Bucle para recibir múltiples solicitudes del mismo cliente
                    while (true) {
                        request = in.readLine(); // Lee la línea enviada por el cliente

                        // Verificar si la solicitud es nula (el cliente se desconectó)
                        if (request == null || request.equalsIgnoreCase("salir")) {
                            System.out.println("Cliente desconectado.");
                            break; // Salir del bucle si el cliente envía "salir" o se desconecta
                        }

                        String[] parts = request.split(" "); // Divide la solicitud en partes
                        String operation = parts[0]; // La primera parte es la operación

                        // Verificar que se hayan proporcionado suficientes argumentos
                        if (parts.length < 3) {
                            out.println("Error: Debes proporcionar una operación y dos números.");
                            continue; // Continuar para la próxima iteración
                        }

                        try {
                            // Intentar convertir los siguientes elementos a números
                            double num1 = Double.parseDouble(parts[1]);
                            double num2 = Double.parseDouble(parts[2]);
                            double result = 0; // Variable para almacenar el resultado

                            // Seleccionar la operación correspondiente
                            switch (operation) {
                                case "suma":
                                    result = num1 + num2; // Sumar
                                    System.out.println("Se realizo una Suma");
                                    break;
                                case "resta":
                                    result = num1 - num2; // Restar
                                    System.out.println("Se realizo una Resta");
                                    break;
                                case "multiplicacion":
                                    result = num1 * num2; // Multiplicar
                                    System.out.println("Se realizo una Multiplicacion");
                                    break;
                                case "division":
                                    if (num2 != 0) {
                                        result = num1 / num2; // Dividir
                                        System.out.println("Se realizo una Division");
                                    } else {
                                        out.println("Error: División por cero."); // Manejo de división por cero
                                        continue; // Continuar para la próxima iteración
                                    }
                                    break;
                                default:
                                    out.println("Error: Operación inválida."); // Manejo de operaciones inválidas
                                    continue; // Continuar para la próxima iteración
                            }

                            // Usar DecimalFormat para mostrar el resultado sin .0
                            DecimalFormat df = new DecimalFormat("#.##");
                            out.println("Resultado: " + df.format(result)); // Enviar el resultado al cliente
                        } catch (NumberFormatException e) {
                            // Manejo de errores si la entrada no es válida
                            out.println("Error: Entrada no válida. Asegúrate de que los números sean válidos.");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace(); // Imprimir el stack trace si hay un error de I/O
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el stack trace si hay un error al iniciar el servidor
        }
    }
}

