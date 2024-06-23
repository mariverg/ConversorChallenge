# Conversor de Monedas

## Descripción

Este proyecto es una aplicación de escritorio en Java que permite a los usuarios convertir cantidades de dinero de una moneda a otra. Utiliza la API de ExchangeRate-API para obtener las tasas de cambio actualizadas y proporciona una interfaz gráfica de usuario (GUI) basada en Swing para interactuar con el usuario.

## Funcionalidades

- Convertir cantidades de una moneda a otra.
- Consultar el historial de conversiones realizadas.
- Interfaz gráfica intuitiva y fácil de usar.

## Requisitos

- JDK 11 o superior.
- Conexión a Internet para obtener las tasas de cambio.

## Instalación y Ejecución

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/tu_usuario/conversor-monedas.git
    cd conversor-monedas
    ```

2. Compilar y ejecutar el proyecto:

    ```bash
    javac -cp .;lib/gson-2.8.8.jar Main.java
    java -cp .;lib/gson-2.8.8.jar Main
    ```

## Uso

1. Al ejecutar la aplicación, se presentará un menú principal con tres opciones:
    - Convertir moneda
    - Consultar historial
    - Salir

2. Seleccione "Convertir moneda" para iniciar una conversión.
    - Seleccione la moneda de origen.
    - Seleccione la moneda de destino.
    - Ingrese la cantidad a convertir.
    - Verá el resultado de la conversión y se le preguntará si desea realizar otra conversión.

3. Seleccione "Consultar historial" para ver todas las conversiones realizadas durante la sesión actual.

4. Seleccione "Salir" para cerrar la aplicación.

## Estructura del Código

- `Main.java`: Contiene la clase principal y la lógica de la aplicación.
    - `main`: Punto de entrada del programa.
    - `convertirMoneda`: Maneja la conversión de monedas.
    - `verHistorialConversiones`: Muestra el historial de conversiones.
    - `obtenerCodigoMoneda`: Convierte el nombre de la moneda a su código.
    - `obtenerTasaConversion`: Obtiene la tasa de conversión desde la API.

## Dependencias

- Gson: Utilizado para manejar la conversión de JSON.

## Notas

- Asegúrese de tener acceso a Internet al utilizar la aplicación, ya que las tasas de cambio se obtienen en tiempo real desde la API.
- Puede personalizar la lista de monedas modificando el método `obtenerCodigoMoneda`.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulte el archivo LICENSE para obtener más detalles.
