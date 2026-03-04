package algorithms;

/**
 * ============================================================
 * A3 - BUSQUEDA LINEAL
 * ============================================================
 * Busca un valor en un arreglo recorriendolo secuencialmente.
 * Retorna el indice donde se encuentra, o -1 si no existe.
 *
 * Complejidad temporal:
 *   - Iterativo:  O(n) — recorre el arreglo una vez
 *   - Recursivo:  O(n) — una llamada por elemento
 *
 * Complejidad espacial:
 *   - Iterativo:  O(1) — solo un indice
 *   - Recursivo:  O(n) — profundidad del call stack
 * ============================================================
 */
public class BusquedaLineal {

    // ----------------------------------------------------------------
    // VERSION ITERATIVA
    // ----------------------------------------------------------------
    /**
     * Busca 'valor' en el arreglo recorriendolo con un ciclo.
     *
     * Traza para arr = [4, 7, 2, 9, 1], valor = 9:
     *   i=0 -> arr[0]=4 != 9
     *   i=1 -> arr[1]=7 != 9
     *   i=2 -> arr[2]=2 != 9
     *   i=3 -> arr[3]=9 == 9  <- retorna 3
     *
     * @param arr   arreglo de enteros
     * @param valor elemento a buscar
     * @return indice del elemento, o -1 si no se encuentra
     */
    public static int iterativo(int[] arr, int valor) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    // ----------------------------------------------------------------
    // VERSION RECURSIVA
    // ----------------------------------------------------------------
    /**
     * Busca 'valor' en el arreglo recursivamente desde el indice 0.
     *
     * @param arr   arreglo de enteros
     * @param valor elemento a buscar
     * @return indice del elemento, o -1 si no se encuentra
     */
    public static int recursivo(int[] arr, int valor) {
        return buscarRecursivo(arr, valor, 0);
    }

    /**
     * Metodo auxiliar que busca desde la posicion 'indice'.
     *
     * Formula recursiva:
     *   buscar(arr, valor, indice):
     *     Si indice >= arr.length -> retorna -1       (caso base: se acabo el arreglo)
     *     Si arr[indice] == valor -> retorna indice   (caso base: encontrado)
     *     Sino -> buscar(arr, valor, indice + 1)      (avanza al siguiente)
     *
     * Cadena de llamadas para arr = [4, 7, 2, 9], valor = 2:
     *   buscar(arr, 2, 0) -> arr[0]=4 != 2 -> buscar(arr, 2, 1)
     *   buscar(arr, 2, 1) -> arr[1]=7 != 2 -> buscar(arr, 2, 2)
     *   buscar(arr, 2, 2) -> arr[2]=2 == 2 -> retorna 2
     *
     * @param arr    arreglo de enteros
     * @param valor  elemento a buscar
     * @param indice posicion actual de busqueda
     * @return indice del elemento, o -1 si no se encuentra
     */
    private static int buscarRecursivo(int[] arr, int valor, int indice) {
        // --- CASO BASE: se recorrio todo el arreglo ---
        if (indice >= arr.length) return -1;

        // --- CASO BASE: elemento encontrado ---
        if (arr[indice] == valor) return indice;

        // --- LLAMADA RECURSIVA: buscar en el siguiente indice ---
        return buscarRecursivo(arr, valor, indice + 1);
    }
}
