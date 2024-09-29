import java.io.*; // Importa clases para entrada/salida
import java.net.*; // Importa clases para comunicación de red
import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario

public class Client {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor (localhost para conexión local)
        int port = 12345; // Puerto del servidor

        try (Socket socket = new Socket(host, port)) {
            // Crea un socket para conectarse al servidor en la dirección y puerto especificados
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Flujo de salida para enviar datos al servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Flujo de entrada para recibir datos del servidor
            Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario

            System.out.println("Conectado al servidor. Escribe 'salir' para desconectar.");

            // Bucle para permitir múltiples operaciones hasta que el usuario decida desconectarse
            while (true) {
                System.out.print("Elige una operación (suma, resta, multiplicacion, division) o 'salir': ");
                String operation = scanner.nextLine(); // Lee la operación elegida por el usuario

                // Verificar si el usuario desea salir
                if (operation.equalsIgnoreCase("salir")) {
                    out.println("salir"); // Envía "salir" al servidor
                    break; // Rompe el bucle y cierra el cliente
                }

                // Solicitar el primer número
                System.out.print("Introduce el primer número: ");
                double num1 = scanner.nextDouble(); // Lee el primer número
                scanner.nextLine(); // Consumir la línea restante para evitar problemas en la siguiente entrada

                // Solicitar el segundo número
                System.out.print("Introduce el segundo número: ");
                double num2 = scanner.nextDouble(); // Lee el segundo número
                scanner.nextLine(); // Consumir la línea restante

                // Construir la solicitud para enviar al servidor
                String request = operation + " " + num1 + " " + num2;
                out.println(request); // Enviar la solicitud al servidor

                // Leer la respuesta del servidor y mostrarla al usuario
                String response = in.readLine(); // Lee la respuesta del servidor
                System.out.println(response); // Muestra la respuesta al usuario
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el stack trace si hay un error de I/O
        }
    }
}

