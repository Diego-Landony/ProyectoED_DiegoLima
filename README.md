# Proyecto Estructura de Datos — Algoritmos Iterativos vs. Recursivos

**Diego Lima**
Universidad Da Vinci de Guatemala
Estructura de Datos | Ing. Brandon Chitay

---

## Descripción

Proyecto del examen parcial práctico de Estructura de Datos. Implementa 4 algoritmos en sus versiones **iterativa** y **recursiva** (8 implementaciones en total), mide sus tiempos de ejecución con `System.nanoTime()` y exporta los resultados a CSV para su análisis en Excel.

### Algoritmos implementados

| # | Algoritmo | Iterativo | Recursivo |
|---|-----------|-----------|-----------|
| A1 | Factorial | O(n) | O(n) |
| A2 | Serie de Fibonacci | O(n) | O(2^n) |
| A3 | Búsqueda Lineal | O(n) | O(n) |
| A4 | Ordenamiento Burbuja | O(n²) | O(n²) |

---

## Requisitos

- **Java 11** o superior (el proyecto usa `String.repeat()` que no existe en Java 8)
- **Terminal** (Git Bash en Windows, Terminal en macOS/Linux)

---

## Estructura del proyecto

```
ProyectoED_DiegoLima/
├── src/
│   ├── algorithms/
│   │   ├── Factorial.java            ← A1: iterativo y recursivo O(n)
│   │   ├── Fibonacci.java           ← A2: iterativo O(n) y recursivo O(2ⁿ)
│   │   ├── BusquedaLineal.java      ← A3: iterativo y recursivo O(n)
│   │   └── OrdenamientoBurbuja.java ← A4: iterativo y recursivo O(n²)
│   ├── benchmark/
│   │   ├── Medidor.java             ← Mide tiempos con System.nanoTime(), promedia 10 ejecuciones
│   │   └── Main.java               ← Punto de entrada, ejecuta benchmarks y exporta CSV
│   └── utils/
│       └── GeneradorDatos.java      ← Genera arreglos aleatorios para A3 y A4
├── resultados/
│   └── tiempos.csv                  ← Se genera al ejecutar
├── Tiempos Rendimiento.xlsx         ← Datos y gráficas en Excel
├── prompts.md                       ← Anexo de integridad académica (prompts IA)
├── .sdkmanrc                        ← Configura Java automáticamente con SDKMAN
└── README.md
```

---

## Compilar y ejecutar

Desde la carpeta raíz del proyecto:

```bash
# Compilar
javac -d out src/algorithms/*.java src/utils/*.java src/benchmark/*.java

# Ejecutar
java -cp out benchmark.Main
```

Al ejecutar, el programa muestra los tiempos de los 4 algoritmos y genera `resultados/tiempos.csv`.

---

## Tamaños de prueba

| Algoritmo | Valores de n |
|-----------|-------------|
| Factorial y Fibonacci | 5, 10, 15, 20, 25, 30 |
| Búsqueda Lineal y Burbuja | 100, 500, 1000, 5000, 10000 |

---

## Salida esperada

El programa muestra para cada algoritmo:

1. **Tiempos iterativos** — tabla con n y tiempo en ms
2. **Tiempos recursivos** — mismos valores de n
3. **Tabla comparativa** — cuántas veces más lento es el recursivo vs iterativo

Al finalizar, exporta todos los resultados a `resultados/tiempos.csv`.

---

## Documentación técnica

[Documentacion_Tecnica.pdf](Documentacion_Tecnica.pdf)

---

## Video de demostración

[Ver video](https://youtu.be/2TGqv7r0UgU)
