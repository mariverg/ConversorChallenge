import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorMoneda {
    private String monedaOrigen;
    private String monedaDestino;

    public void convertirMoneda() {
        String[] monedas = {"Peso Mexicano (MXN)", "Peso Argentino (ARS)", "Peso Colombiano (COP)", "Dólar (USD)", "Euro (EUR)", "Libra Esterlina (GBP)", "Yen Japonés (JPY)", "Won Sul-Coreano (KRW)"};

        // Selección de moneda de origen
        if (monedaOrigen == null || monedaDestino == null) {
            String monedaSeleccionadaOrigen = (String) JOptionPane.showInputDialog(null, "Selecciona la moneda de origen:",
                    "Convertir moneda",
                    JOptionPane.PLAIN_MESSAGE, null, monedas, monedas[0]);
            monedaOrigen = obtenerCodigoMoneda(monedaSeleccionadaOrigen);

            // Selección de moneda de destino
            String monedaSeleccionadaDestino = (String) JOptionPane.showInputDialog(null, "Selecciona la moneda de destino:",
                    "Convertir moneda",
                    JOptionPane.PLAIN_MESSAGE, null, monedas, monedas[0]);
            monedaDestino = obtenerCodigoMoneda(monedaSeleccionadaDestino);
        }

        // Ingreso de cantidad a convertir
        String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad a convertir:");
        if (cantidadStr != null && !cantidadStr.isEmpty()) {
            try {
                double cantidad = Double.parseDouble(cantidadStr);
                double tasaConversion = obtenerTasaConversion(monedaOrigen, monedaDestino);
                double resultado = cantidad * tasaConversion;
                String mensaje = "Resultado de la conversión: " + cantidad + " " + monedaOrigen + " = " + resultado + " " + monedaDestino;
                JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                HistorialConversion.agregarEntrada("Convertido " + cantidad + " " + monedaOrigen + " a " + resultado + " " + monedaDestino);

                // Preguntar si desea convertir otra cantidad
                int eleccion = JOptionPane.showConfirmDialog(null, "¿Desea convertir otra cantidad de " + monedaOrigen + " a " + monedaDestino + "?", "Convertir otra cantidad", JOptionPane.YES_NO_OPTION);
                if (eleccion == JOptionPane.YES_OPTION) {
                    convertirMoneda();
                } else {
                    // Reiniciar las monedas seleccionadas
                    monedaOrigen = null;
                    monedaDestino = null;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la conversión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String obtenerCodigoMoneda(String nombreMoneda) {
        switch (nombreMoneda) {
            case "Peso Mexicano (MXN)":
                return "MXN";
            case "Peso Argentino (ARS)":
                return "ARS";
            case "Peso Colombiano (COP)":
                return "COP";
            case "Dólar (USD)":
                return "USD";
            case "Euro (EUR)":
                return "EUR";
            case "Libra Esterlina (GBP)":
                return "GBP";
            case "Yen Japonés (JPY)":
                return "JPY";
            case "Won Sul-Coreano (KRW)":
                return "KRW";
            default:
                return null;
        }
    }

    private double obtenerTasaConversion(String monedaBase, String monedaObjetivo) throws Exception {
        String url = "https://v6.exchangerate-api.com/v6/42f07f436f6d7ab78de02e54/latest/" + monedaBase;
        URL apiUrl = new URL(url);
        HttpURLConnection conexion = (HttpURLConnection) apiUrl.openConnection();
        conexion.setRequestMethod("GET");

        int codigoRespuesta = conexion.getResponseCode();
        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();

            Gson gson = new Gson();
            JsonObject objetoJson = gson.fromJson(respuesta.toString(), JsonObject.class);

            JsonObject tasasConversion = objetoJson.getAsJsonObject("conversion_rates");
            if (tasasConversion != null && tasasConversion.has(monedaObjetivo)) {
                return tasasConversion.get(monedaObjetivo).getAsDouble();
            } else {
                throw new Exception("La tasa de conversión para " + monedaObjetivo + " no está disponible en el JSON recibido");
            }
        } else {
            throw new Exception("Error al obtener la tasa de conversión. Código de respuesta: " + codigoRespuesta);
        }
    }

}
