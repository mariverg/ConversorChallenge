import javax.swing.*;

public class main {
    public static void main(String[] args) {
        ConversorMoneda conversorMoneda = new ConversorMoneda();

        while (true) {
            String[] opciones = {"Convertir moneda", "Consultar historial", "Salir"};
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    conversorMoneda.convertirMoneda();
                    break;
                case 1:
                    HistorialConversion.verHistorialConversiones();
                    break;
                case 2:
                    System.out.println("¡Adiós!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }
}

