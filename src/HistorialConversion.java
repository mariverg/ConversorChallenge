import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversion {
    private static final List<String> historialConversiones = new ArrayList<>();

    public static void agregarEntrada(String entrada) {
        historialConversiones.add(entrada);
    }

    public static void verHistorialConversiones() {
        StringBuilder mensajeHistorial = new StringBuilder("----- HISTORIAL DE CONVERSIONES -----\n");
        if (historialConversiones.isEmpty()) {
            mensajeHistorial.append("El historial está vacío.");
        } else {
            for (String entrada : historialConversiones) {
                mensajeHistorial.append(entrada).append("\n");
            }
        }
        mensajeHistorial.append("--------------------------------------");
        JOptionPane.showMessageDialog(null, mensajeHistorial.toString(), "Historial de Conversiones", JOptionPane.PLAIN_MESSAGE);
    }
}
