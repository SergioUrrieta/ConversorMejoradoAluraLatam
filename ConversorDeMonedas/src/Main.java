import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String url_str = "https://v6.exchangerate-api.com/v6/5d8c3d86de664d44f46f6605/latest/USD";

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_str))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON
        ApiResponse data = gson.fromJson(response.body(), ApiResponse.class);

        if (!"success".equals(data.getResult())) {
            System.out.println("Error en la API: " + data.getResult());
            return;
        }

        Map<String, Double> tasas = data.getConversion_rates();

        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n=== CONVERSOR DE MONEDAS ===");
            System.out.println("1. USD ➝ Otra moneda");
            System.out.println("2. Otra moneda ➝ USD");
            System.out.println("3. Ver lista de monedas disponibles");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa código de moneda destino (ej: EUR, CLP, JPY): ");
                    String destino = scanner.next().toUpperCase();

                    if (!tasas.containsKey(destino)) {
                        System.out.println("Moneda no válida.");
                        break;
                    }

                    System.out.print("Cantidad en USD: ");
                    double montoUSD = scanner.nextDouble();

                    double convertido1 = montoUSD * tasas.get(destino);
                    System.out.println("Resultado: " + convertido1 + " " + destino);
                    break;

                case 2:
                    System.out.print("Ingresa código de moneda origen (ej: EUR, CLP, JPY): ");
                    String origen = scanner.next().toUpperCase();

                    if (!tasas.containsKey(origen)) {
                        System.out.println("Moneda no válida.");
                        break;
                    }

                    System.out.print("Cantidad en " + origen + ": ");
                    double montoOrigen = scanner.nextDouble();

                    double convertido2 = montoOrigen / tasas.get(origen);
                    System.out.println("Resultado: " + convertido2 + " USD");
                    break;

                case 3:
                    System.out.println("=== Monedas disponibles ===");
                    for (String key : tasas.keySet()) {
                        System.out.println(key);
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

    }
}


///SergioUrrieta