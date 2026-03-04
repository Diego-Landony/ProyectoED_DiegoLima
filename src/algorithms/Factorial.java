package algorithms;

/**
 * ============================================================
 * A1 - FACTORIAL
 * ============================================================
 * Calcula n! = n * (n-1) * (n-2) * ... * 1
 * Caso base: 0! = 1
 *
 * Complejidad temporal:
 *   - Iterativo:  O(n) — un ciclo lineal
 *   - Recursivo:  O(n) — n llamadas recursivas lineales
 *
 * Complejidad espacial:
 *   - Iterativo:  O(1) — solo una variable acumuladora
 *   - Recursivo:  O(n) — profundidad del call stack
 *
 * LIMITE: n <= 20 porque 21! desborda el rango de long.
 * ============================================================
 */
public class Factorial {

    // ----------------------------------------------------------------
    // VERSION ITERATIVA
    // ----------------------------------------------------------------
    /**
     * Calcula n! con un ciclo acumulador.
     *
     * Traza para n = 5:
     *   resultado = 1
     *   i=1 -> resultado = 1
     *   i=2 -> resultado = 2
     *   i=3 -> resultado = 6
     *   i=4 -> resultado = 24
     *   i=5 -> resultado = 120  <- retorna 120
     *
     * @param n numero entero no negativo (0 <= n <= 20)
     * @return n!
     */
    public static long iterativo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");
        if (n > 20) throw new IllegalArgumentException("n > 20 desborda long");

        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // ----------------------------------------------------------------
    // VERSION RECURSIVA
    // ----------------------------------------------------------------
    /**
     * Calcula n! con recursion directa.
     *
     * Formula recursiva:
     *   fact(0) = 1            <- caso base
     *   fact(n) = n * fact(n-1) <- llamada recursiva
     *
     * Cadena de llamadas para n = 5:
     *   fact(5) = 5 * fact(4)
     *           = 5 * 4 * fact(3)
     *           = 5 * 4 * 3 * fact(2)
     *           = 5 * 4 * 3 * 2 * fact(1)
     *           = 5 * 4 * 3 * 2 * 1 * fact(0)
     *           = 5 * 4 * 3 * 2 * 1 * 1 = 120
     *
     * @param n numero entero no negativo (0 <= n <= 20)
     * @return n!
     */
    public static long recursivo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");
        if (n > 20) throw new IllegalArgumentException("n > 20 desborda long");

        // --- CASO BASE ---
        if (n == 0) return 1;

        // --- LLAMADA RECURSIVA ---
        return n * recursivo(n - 1);
    }
}
