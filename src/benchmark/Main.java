package benchmark;

import algorithms.Factorial;
import algorithms.Fibonacci;
import algorithms.BusquedaLineal;
import algorithms.OrdenamientoBurbuja;
import utils.GeneradorDatos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ============================================================
 * PUNTO DE ENTRADA — BENCHMARK COMPLETO
 * ============================================================
 * Ejecuta los 4 algoritmos (iterativo + recursivo) con distintos
 * valores de n, mide sus tiempos y exporta los resultados a CSV.
 *
 * Algoritmos:
 *   A1 - Factorial        (n = 5, 10, 15, 20, 25, 30)
 *   A2 - Fibonacci         (n = 5, 10, 15, 20, 25, 30)
 *   A3 - Busqueda Lineal   (n = 100, 500, 1000, 5000, 10000)
 *   A4 - Ordenamiento Burbuja (n = 100, 500, 1000, 5000, 10000)
 *
 * COMO COMPILAR (desde la carpeta raiz del proyecto):
 *   javac -d out src/algorithms/*.java src/utils/*.java src/benchmark/*.java
 *
 * COMO EJECUTAR:
 *   java -cp out benchmark.Main
 * ============================================================
 */
public class Main {

    // ----------------------------------------------------------------
    // TAMANOS DE PRUEBA
    // ----------------------------------------------------------------
    /** Para Factorial y Fibonacci: n = 5, 10, 15, 20, 25, 30 */
    private static final int[] TAMANOS_PEQUENOS = {5, 10, 15, 20, 25, 30};

    /** Para Busqueda Lineal y Burbuja: n = 100, 500, 1000, 5000, 10000 */
    private static final int[] TAMANOS_GRANDES = {100, 500, 1000, 5000, 10000};

    /** Ruta del archivo de resultados */
    private static final String CSV_PATH = "resultados/tiempos.csv";

    // ----------------------------------------------------------------
    // MAIN
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        imprimirBanner();

        StringBuilder csv = new StringBuilder();
        csv.append("Algoritmo,Version,n,Resultado,Tiempo_ms\n");

        // ===================== A1 - FACTORIAL =====================
        benchmarkFactorial(csv);

        // ===================== A2 - FIBONACCI =====================
        benchmarkFibonacci(csv);

        // ===================== A3 - BUSQUEDA LINEAL =====================
        benchmarkBusquedaLineal(csv);

        // ===================== A4 - ORDENAMIENTO BURBUJA =====================
        benchmarkBurbuja(csv);

        // ---- EXPORTAR CSV ----
        exportarCSV(csv.toString());

        System.out.println("\n============================================================");
        System.out.println("  Resultados exportados -> " + CSV_PATH);
        System.out.println("============================================================");
    }

    // ================================================================
    // A1 - FACTORIAL
    // ================================================================
    private static void benchmarkFactorial(StringBuilder csv) {
        System.out.println("\n  A1 - FACTORIAL ITERATIVO  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_PEQUENOS) {
            final int fn = n;
            // Factorial: limitar a 20 para no desbordar long
            int nFactorial = Math.min(n, 20);
            final int fnFact = nFactorial;

            long resultado = Factorial.iterativo(fnFact);
            double tiempoMs = Medidor.medir(() -> Factorial.iterativo(fnFact));

            Medidor.imprimirFila("Factorial", "Iterativo", fn, tiempoMs);
            csv.append(String.format("Factorial,Iterativo,%d,%d,%.6f%n", fn, resultado, tiempoMs));
        }

        System.out.println("\n  A1 - FACTORIAL RECURSIVO  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_PEQUENOS) {
            final int fn = n;
            int nFactorial = Math.min(n, 20);
            final int fnFact = nFactorial;

            long resultado = Factorial.recursivo(fnFact);
            double tiempoMs = Medidor.medir(() -> Factorial.recursivo(fnFact));

            Medidor.imprimirFila("Factorial", "Recursivo", fn, tiempoMs);
            csv.append(String.format("Factorial,Recursivo,%d,%d,%.6f%n", fn, resultado, tiempoMs));
        }

        // ---- COMPARACION ----
        imprimirComparacion("A1 - FACTORIAL");
        for (int n : TAMANOS_PEQUENOS) {
            final int fn = n;
            int nFactorial = Math.min(n, 20);
            final int fnFact = nFactorial;

            double tIter = Medidor.medir(() -> Factorial.iterativo(fnFact));
            double tRec  = Medidor.medir(() -> Factorial.recursivo(fnFact));
            double factor = (tIter > 0) ? tRec / tIter : 0;

            System.out.printf("n=%-6d | %-14.6f | %-14.6f | %.1fx%n", fn, tIter, tRec, factor);
        }
    }

    // ================================================================
    // A2 - FIBONACCI
    // ================================================================
    private static void benchmarkFibonacci(StringBuilder csv) {
        System.out.println("\n  A2 - FIBONACCI ITERATIVO  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_PEQUENOS) {
            final int fn = n;
            long resultado = Fibonacci.iterativo(fn);
            double tiempoMs = Medidor.medir(() -> Fibonacci.iterativo(fn));

            Medidor.imprimirFila("Fibonacci", "Iterativo", n, tiempoMs);
            csv.append(String.format("Fibonacci,Iterativo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        System.out.println("\n  A2 - FIBONACCI RECURSIVO  [O(2^n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_PEQUENOS) {
            final int fn = n;
            long resultado = Fibonacci.recursivo(fn);
            double tiempoMs = Medidor.medir(() -> Fibonacci.recursivo(fn));

            Medidor.imprimirFila("Fibonacci", "Recursivo", n, tiempoMs);
            csv.append(String.format("Fibonacci,Recursivo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- COMPARACION ----
        imprimirComparacion("A2 - FIBONACCI");
        for (int n : TAMANOS_PEQUENOS) {
            final int fn = n;
            double tIter = Medidor.medir(() -> Fibonacci.iterativo(fn));
            double tRec  = Medidor.medir(() -> Fibonacci.recursivo(fn));
            double factor = (tIter > 0) ? tRec / tIter : 0;

            System.out.printf("n=%-6d | %-14.6f | %-14.6f | %.1fx%n", n, tIter, tRec, factor);
        }
    }

    // ================================================================
    // A3 - BUSQUEDA LINEAL
    // ================================================================
    private static void benchmarkBusquedaLineal(StringBuilder csv) {
        System.out.println("\n  A3 - BUSQUEDA LINEAL ITERATIVA  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_GRANDES) {
            // Generar arreglo y buscar el ULTIMO elemento (peor caso)
            int[] arr = GeneradorDatos.arregloAleatorio(n);
            int valorBuscar = arr[n - 1]; // peor caso: ultimo elemento

            int resultado = BusquedaLineal.iterativo(arr, valorBuscar);
            double tiempoMs = Medidor.medir(() -> BusquedaLineal.iterativo(arr, valorBuscar));

            Medidor.imprimirFila("BusqLineal", "Iterativo", n, tiempoMs);
            csv.append(String.format("BusquedaLineal,Iterativo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        System.out.println("\n  A3 - BUSQUEDA LINEAL RECURSIVA  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_GRANDES) {
            int[] arr = GeneradorDatos.arregloAleatorio(n);
            int valorBuscar = arr[n - 1];

            int resultado = BusquedaLineal.recursivo(arr, valorBuscar);
            double tiempoMs = Medidor.medir(() -> BusquedaLineal.recursivo(arr, valorBuscar));

            Medidor.imprimirFila("BusqLineal", "Recursivo", n, tiempoMs);
            csv.append(String.format("BusquedaLineal,Recursivo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- COMPARACION ----
        imprimirComparacion("A3 - BUSQUEDA LINEAL");
        for (int n : TAMANOS_GRANDES) {
            int[] arr = GeneradorDatos.arregloAleatorio(n);
            int valorBuscar = arr[n - 1];

            double tIter = Medidor.medir(() -> BusquedaLineal.iterativo(arr, valorBuscar));
            double tRec  = Medidor.medir(() -> BusquedaLineal.recursivo(arr, valorBuscar));
            double factor = (tIter > 0) ? tRec / tIter : 0;

            System.out.printf("n=%-6d | %-14.6f | %-14.6f | %.1fx%n", n, tIter, tRec, factor);
        }
    }

    // ================================================================
    // A4 - ORDENAMIENTO BURBUJA
    // ================================================================
    private static void benchmarkBurbuja(StringBuilder csv) {
        System.out.println("\n  A4 - BURBUJA ITERATIVO  [O(n^2)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_GRANDES) {
            int[] original = GeneradorDatos.arregloAleatorio(n);

            // Medir: cada repeticion usa una copia fresca del arreglo
            double tiempoMs = Medidor.medir(() -> {
                int[] copia = GeneradorDatos.copiar(original);
                OrdenamientoBurbuja.iterativo(copia);
            });

            Medidor.imprimirFila("Burbuja", "Iterativo", n, tiempoMs);
            csv.append(String.format("Burbuja,Iterativo,%d,%s,%.6f%n", n, "ordenado", tiempoMs));
        }

        System.out.println("\n  A4 - BURBUJA RECURSIVO  [O(n^2)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS_GRANDES) {
            int[] original = GeneradorDatos.arregloAleatorio(n);

            double tiempoMs = Medidor.medir(() -> {
                int[] copia = GeneradorDatos.copiar(original);
                OrdenamientoBurbuja.recursivo(copia);
            });

            Medidor.imprimirFila("Burbuja", "Recursivo", n, tiempoMs);
            csv.append(String.format("Burbuja,Recursivo,%d,%s,%.6f%n", n, "ordenado", tiempoMs));
        }

        // ---- COMPARACION ----
        imprimirComparacion("A4 - BURBUJA");
        for (int n : TAMANOS_GRANDES) {
            int[] original = GeneradorDatos.arregloAleatorio(n);

            double tIter = Medidor.medir(() -> {
                int[] c = GeneradorDatos.copiar(original);
                OrdenamientoBurbuja.iterativo(c);
            });
            double tRec = Medidor.medir(() -> {
                int[] c = GeneradorDatos.copiar(original);
                OrdenamientoBurbuja.recursivo(c);
            });
            double factor = (tIter > 0) ? tRec / tIter : 0;

            System.out.printf("n=%-6d | %-14.6f | %-14.6f | %.1fx%n", n, tIter, tRec, factor);
        }
    }

    // ----------------------------------------------------------------
    // AUXILIARES
    // ----------------------------------------------------------------
    private static void imprimirBanner() {
        System.out.println("============================================================");
        System.out.println("  ESTRUCTURA DE DATOS — BENCHMARK COMPLETO");
        System.out.println("  Universidad Da Vinci de Guatemala");
        System.out.println("  Ing. Brandon Chitay");
        System.out.println("============================================================");
        System.out.println("  Algoritmos: Factorial, Fibonacci, Busqueda Lineal, Burbuja");
        System.out.println("  Cada prueba se ejecuta " + 10 + " veces (promedio)");
        System.out.println("============================================================");
    }

    private static void imprimirComparacion(String titulo) {
        System.out.println("\n  COMPARACION " + titulo);
        System.out.println("-".repeat(60));
        System.out.printf("%-8s | %-14s | %-14s | %s%n",
                "n", "Iterativo (ms)", "Recursivo (ms)", "Rec/Iter");
        System.out.println("-".repeat(60));
    }

    private static void exportarCSV(String contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH))) {
            pw.print(contenido);
            System.out.println("\n  CSV generado exitosamente en: " + CSV_PATH);
        } catch (IOException e) {
            System.err.println("  Error al escribir CSV: " + e.getMessage());
        }
    }
}
