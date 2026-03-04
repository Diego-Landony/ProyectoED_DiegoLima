package algorithms;

/**
 * ============================================================
 * A4 - ORDENAMIENTO BURBUJA
 * ============================================================
 * Ordena un arreglo de enteros de menor a mayor usando el
 * metodo burbuja: compara pares adyacentes y los intercambia
 * si estan en orden incorrecto.
 *
 * Complejidad temporal:
 *   - Iterativo:  O(n^2) — dos ciclos anidados
 *   - Recursivo:  O(n^2) — recursion externa + ciclo interno
 *
 * Complejidad espacial:
 *   - Iterativo:  O(1) — ordenamiento in-place
 *   - Recursivo:  O(n) — profundidad del call stack
 * ============================================================
 */
public class OrdenamientoBurbuja {

    // ----------------------------------------------------------------
    // VERSION ITERATIVA
    // ----------------------------------------------------------------
    /**
     * Ordena el arreglo con dos ciclos anidados (burbuja clasico).
     *
     * Traza para arr = [5, 3, 1]:
     *   Pasada i=0: compara (5,3)->swap->(3,5,1), compara (5,1)->swap->(3,1,5)
     *   Pasada i=1: compara (3,1)->swap->(1,3,5), compara (3,5)->ok
     *   Resultado: [1, 3, 5]
     *
     * @param arr arreglo a ordenar (se modifica in-place)
     */
    public static void iterativo(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // ----------------------------------------------------------------
    // VERSION RECURSIVA
    // ----------------------------------------------------------------
    /**
     * Ordena el arreglo usando burbuja recursivo.
     *
     * Estrategia: cada llamada recursiva realiza UNA pasada
     * (burbujea el mayor al final), y luego se llama a si misma
     * con un arreglo "logicamente" mas pequeno (n-1).
     *
     * Formula recursiva:
     *   burbuja(arr, n):
     *     Si n <= 1 -> retorna                    (caso base: ya ordenado)
     *     Hacer una pasada de 0 a n-2             (ciclo interno)
     *     burbuja(arr, n-1)                       (recursion: ignorar el ultimo)
     *
     * @param arr arreglo a ordenar (se modifica in-place)
     */
    public static void recursivo(int[] arr) {
        burbujaRecursivo(arr, arr.length);
    }

    /**
     * Metodo auxiliar: realiza una pasada y reduce el problema.
     *
     * Cadena de llamadas para arr = [5, 3, 1]:
     *   burbuja(arr, 3): pasada -> [3, 1, 5], llama burbuja(arr, 2)
     *   burbuja(arr, 2): pasada -> [1, 3, 5], llama burbuja(arr, 1)
     *   burbuja(arr, 1): caso base -> retorna
     *   Resultado: [1, 3, 5]
     *
     * @param arr arreglo a ordenar
     * @param n   cantidad de elementos a considerar en esta pasada
     */
    private static void burbujaRecursivo(int[] arr, int n) {
        // --- CASO BASE: arreglo de 0 o 1 elemento ya esta ordenado ---
        if (n <= 1) return;

        // --- UNA PASADA: burbujear el mayor al final ---
        for (int j = 0; j < n - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        // --- LLAMADA RECURSIVA: ordenar los primeros n-1 elementos ---
        burbujaRecursivo(arr, n - 1);
    }
}
