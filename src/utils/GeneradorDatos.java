package utils;

import java.util.Random;

/**
 * ============================================================
 * GENERADOR DE DATOS
 * ============================================================
 * Genera arreglos de enteros de distintos tamanos para las
 * pruebas de Busqueda Lineal y Ordenamiento Burbuja.
 *
 * USO:
 *   int[] arr = GeneradorDatos.arregloAleatorio(1000);
 *   int[] copia = GeneradorDatos.copiar(arr);
 * ============================================================
 */
public class GeneradorDatos {

    private static final Random random = new Random(42); // semilla fija para reproducibilidad

    /**
     * Genera un arreglo de n enteros aleatorios en el rango [0, n*10).
     *
     * @param n tamano del arreglo
     * @return arreglo con valores aleatorios
     */
    public static int[] arregloAleatorio(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n * 10);
        }
        return arr;
    }

    /**
     * Crea una copia independiente del arreglo.
     * Necesario para que cada prueba trabaje con los mismos datos,
     * ya que el ordenamiento modifica el arreglo original.
     *
     * @param original arreglo a copiar
     * @return nueva copia del arreglo
     */
    public static int[] copiar(int[] original) {
        int[] copia = new int[original.length];
        System.arraycopy(original, 0, copia, 0, original.length);
        return copia;
    }
}
