# Documentación Técnica — Algoritmos Iterativos vs. Recursivos

**Nombre:** Diego Lima
**Asignatura:** Estructura de Datos
**Catedrático:** Ing. Brandon Chitay
**Universidad:** Da Vinci de Guatemala — Facultad de Ingeniería, Industria y Tecnología
**Fecha:** Marzo 2026

---

## 1. Introducción — ¿Qué es la notación Big-O?

La notación Big-O describe cómo crece el tiempo de ejecución de un algoritmo conforme aumenta el tamaño de la entrada (n). No mide segundos exactos, sino la **tendencia de crecimiento** en el peor caso.

Por ejemplo:
- **O(n)** significa que si n se duplica, el tiempo también se duplica (crecimiento lineal).
- **O(n²)** significa que si n se duplica, el tiempo se cuadruplica.
- **O(2ⁿ)** significa que cada incremento de n duplica el tiempo (crecimiento exponencial).

Esto permite comparar algoritmos sin depender del hardware, y elegir la solución más eficiente para un problema dado.

---

## 2. Descripción de algoritmos

### A1 — Factorial

Calcula el producto de todos los enteros desde 1 hasta n (n!). Por ejemplo, 5! = 5 × 4 × 3 × 2 × 1 = 120. El caso base es 0! = 1. Se limita n a 20 porque 21! supera el rango del tipo `long` en Java.

- **Iterativo:** usa un ciclo `for` que acumula el producto en una variable. Recorre de 2 hasta n, multiplicando en cada paso.
- **Recursivo:** la función se llama a sí misma con `n * factorial(n-1)` hasta llegar al caso base `n == 0`.

Ambas versiones son O(n) porque realizan exactamente n operaciones.

### A2 — Serie de Fibonacci

Calcula el n-ésimo término de la secuencia 0, 1, 1, 2, 3, 5, 8, 13... donde cada número es la suma de los dos anteriores.

- **Iterativo:** mantiene dos variables (`anterior` y `actual`) que se van desplazando en cada paso del ciclo. Solo necesita recorrer n veces.
- **Recursivo:** llama a `fib(n-1) + fib(n-2)`, generando un árbol de llamadas que crece exponencialmente. Por ejemplo, `fib(5)` calcula `fib(3)` dos veces y `fib(2)` tres veces, duplicando trabajo innecesariamente.

El iterativo es O(n); el recursivo es O(2ⁿ).

### A3 — Búsqueda Lineal

Busca un valor dentro de un arreglo recorriéndolo elemento por elemento. Retorna el índice donde lo encuentra, o -1 si no existe.

- **Iterativo:** un ciclo `for` que compara cada posición con el valor buscado.
- **Recursivo:** un método auxiliar que revisa la posición actual; si no coincide, se llama a sí mismo con el siguiente índice.

Ambas versiones son O(n) porque en el peor caso recorren todo el arreglo.

### A4 — Ordenamiento Burbuja

Ordena un arreglo comparando pares adyacentes e intercambiándolos si están en orden incorrecto. En cada pasada completa, el elemento más grande "burbujea" hasta su posición final.

- **Iterativo:** dos ciclos anidados. El externo controla las pasadas, el interno compara e intercambia pares.
- **Recursivo:** cada llamada recursiva hace una pasada completa (ciclo interno) y luego se llama con `n-1`, reduciendo el problema en cada nivel.

Ambas versiones son O(n²) porque se necesitan aproximadamente n pasadas de n comparaciones cada una.

---

## 3. Fragmentos de código relevantes

### Factorial — Iterativo
```java
public static long iterativo(int n) {
    long resultado = 1;
    for (int i = 2; i <= n; i++) {
        resultado *= i;
    }
    return resultado;
}
```

### Factorial — Recursivo
```java
public static long recursivo(int n) {
    if (n == 0) return 1;              // caso base
    return n * recursivo(n - 1);       // llamada recursiva
}
```

### Fibonacci — Iterativo
```java
public static long iterativo(int n) {
    if (n <= 1) return n;
    long anterior = 0, actual = 1;
    for (int i = 2; i <= n; i++) {
        long siguiente = anterior + actual;
        anterior = actual;
        actual = siguiente;
    }
    return actual;
}
```

### Fibonacci — Recursivo
```java
public static long recursivo(int n) {
    if (n == 0) return 0;              // caso base
    if (n == 1) return 1;              // caso base
    return recursivo(n - 1) + recursivo(n - 2);  // dos llamadas = O(2^n)
}
```

### Búsqueda Lineal — Iterativo
```java
public static int iterativo(int[] arr, int valor) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == valor) return i;
    }
    return -1;
}
```

### Búsqueda Lineal — Recursivo
```java
private static int buscarRecursivo(int[] arr, int valor, int indice) {
    if (indice >= arr.length) return -1;    // caso base: no encontrado
    if (arr[indice] == valor) return indice; // caso base: encontrado
    return buscarRecursivo(arr, valor, indice + 1);
}
```

### Burbuja — Iterativo
```java
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
```

### Burbuja — Recursivo
```java
private static void burbujaRecursivo(int[] arr, int n) {
    if (n <= 1) return;                     // caso base
    for (int j = 0; j < n - 1; j++) {      // una pasada
        if (arr[j] > arr[j + 1]) {
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
    }
    burbujaRecursivo(arr, n - 1);           // reducir problema
}
```

### Medición de tiempos
```java
long inicio = System.nanoTime();
// llamada al algoritmo
long fin = System.nanoTime();
double durMs = (fin - inicio) / 1_000_000.0;
```
Cada medición se repite 10 veces y se registra el promedio.

---

## 4. Tabla de tiempos medidos

### A1 — Factorial

| n | Iterativo (ms) | Recursivo (ms) |
|---|----------------|----------------|
| 5 | 0.001142 | 0.004914 |
| 10 | 0.000288 | 0.000807 |
| 15 | 0.000336 | 0.000922 |
| 20 | 0.000384 | 0.002305 |
| 25 | 0.000387 | 0.000275 |
| 30 | 0.000394 | 0.000277 |

*Nota: para n > 20, Factorial se limita internamente a n = 20 (desbordamiento de `long`).*

### A2 — Fibonacci

| n | Iterativo (ms) | Recursivo (ms) |
|---|----------------|----------------|
| 5 | 0.000543 | 0.000898 |
| 10 | 0.000336 | 0.010293 |
| 15 | 0.000383 | 0.020205 |
| 20 | 0.000446 | 0.104921 |
| 25 | 0.000513 | 0.434826 |
| 30 | 0.000660 | 4.960082 |

### A3 — Búsqueda Lineal

| n | Iterativo (ms) | Recursivo (ms) |
|---|----------------|----------------|
| 100 | 0.002005 | 0.004637 |
| 500 | 0.007519 | 0.003918 |
| 1,000 | 0.014813 | 0.001454 |
| 5,000 | 0.073502 | 0.048108 |
| 10,000 | 0.029836 | 0.115592 |

### A4 — Ordenamiento Burbuja

| n | Iterativo (ms) | Recursivo (ms) |
|---|----------------|----------------|
| 100 | 0.186683 | 0.050683 |
| 500 | 0.639427 | 0.506874 |
| 1,000 | 0.921401 | 0.841425 |
| 5,000 | 18.597437 | 18.635577 |
| 10,000 | 108.837467 | 109.913374 |

---

## 5. Gráficas de rendimiento

*(Las siguientes gráficas se encuentran en la carpeta `resultados/` y en el archivo Excel `Tiempos Rendimiento.xlsx`.)*

### Gráfica A1 — Factorial
![Factorial](resultados/factorial.png)

Ambas curvas (iterativa y recursiva) se mantienen prácticamente planas con tiempos menores a 0.005 ms. Esto confirma que ambas versiones son **O(n)** — el crecimiento es lineal pero tan pequeño que es casi imperceptible en la gráfica.

### Gráfica A2 — Fibonacci
![Fibonacci](resultados/fibonacci.png)

La curva iterativa permanece pegada a cero (~0.0006 ms incluso en n=30). La curva recursiva explota a partir de n=25 y alcanza ~5 ms en n=30. Esta forma de «palo de hockey» es la firma visual de **O(2ⁿ)**. El iterativo confirma su **O(n)**.

### Gráfica A3 — Búsqueda Lineal
![Búsqueda Lineal](resultados/bisqueda%20lineal.png)

Ambas versiones muestran una tendencia general ascendente conforme n crece, con algunas variaciones por el tamaño pequeño de los tiempos (microsegundos). La tendencia lineal confirma que ambas son **O(n)**.

### Gráfica A4 — Ordenamiento Burbuja
![Burbuja](resultados/burbuja.png)

Ambas curvas (iterativa y recursiva) son prácticamente idénticas y forman una parábola clásica: de n=1000 a n=5000 el tiempo salta de ~1 ms a ~18 ms, y de n=5000 a n=10000 salta a ~110 ms. Esta curva que «sube rápido» confirma que ambas versiones son **O(n²)**.

---

## 6. Tabla de conclusiones — Big-O experimental vs. teórico

| Algoritmo | Versión | Big-O experimental | Big-O teórico | ¿Coincide? |
|---|---|---|---|---|
| A1 - Factorial | Iterativo | O(n) | O(n) | Sí |
| A1 - Factorial | Recursivo | O(n) | O(n) | Sí |
| A2 - Fibonacci | Iterativo | O(n) | O(n) | Sí |
| A2 - Fibonacci | Recursivo | O(2ⁿ) | O(2ⁿ) | Sí |
| A3 - Búsqueda Lineal | Iterativo | O(n) | O(n) | Sí |
| A3 - Búsqueda Lineal | Recursivo | O(n) | O(n) | Sí |
| A4 - Burbuja | Iterativo | O(n²) | O(n²) | Sí |
| A4 - Burbuja | Recursivo | O(n²) | O(n²) | Sí |

**Justificación:** en todos los casos, la forma de las gráficas coincide con la complejidad teórica. Las líneas planas/rectas corresponden a O(n), la curva parabólica a O(n²), y la curva explosiva a O(2ⁿ).

---

## 7. Análisis y conclusiones

### ¿Por qué Fibonacci recursivo es drásticamente más lento que el iterativo conforme n crece?

Porque cada llamada a `fib(n)` genera **dos** llamadas más: `fib(n-1)` y `fib(n-2)`. Esto crea un árbol binario de llamadas donde los mismos valores se recalculan muchas veces. Por ejemplo, para calcular `fib(30)` se realizan aproximadamente 2.7 millones de llamadas. El iterativo solo necesita 30 pasos con un ciclo, ya que no repite cálculos.

### ¿Qué significa que un algoritmo sea O(n²)? ¿Cómo se refleja en la gráfica del Burbuja?

Significa que el tiempo de ejecución crece proporcionalmente al **cuadrado** del tamaño de la entrada. En la gráfica de Burbuja se observa como una curva que sube cada vez más rápido (parábola): al pasar de n=1000 a n=10000 (10x más datos), el tiempo pasa de ~1 ms a ~110 ms (aproximadamente 100x más tiempo, que es 10²).

### Si n se duplica, ¿cuánto aumenta el tiempo para O(n), O(n²) y O(2ⁿ)?

- **O(n):** el tiempo se **duplica** (×2). Si n=100 tarda 1 ms, n=200 tarda ~2 ms.
- **O(n²):** el tiempo se **cuadruplica** (×4). Si n=100 tarda 1 ms, n=200 tarda ~4 ms.
- **O(2ⁿ):** el tiempo se eleva al **cuadrado**. Si n=20 tarda 0.1 ms, n=40 tardaría ~0.1 × 2²⁰ ms, que es un crecimiento devastador. En la práctica, duplicar n lo hace inviable.

### ¿Cuál es el costo de usar recursión en Java en términos de memoria (stack)?

Cada llamada recursiva agrega un **frame** al call stack de Java, que almacena las variables locales y la dirección de retorno. Si la recursión es muy profunda (por ejemplo, n=10000 en búsqueda lineal), se arriesga a un `StackOverflowError`. El iterativo solo usa un espacio constante O(1) en memoria, mientras que el recursivo usa O(n) porque necesita n frames apilados antes de empezar a retornar.

### ¿En qué situaciones elegirías la versión recursiva sobre la iterativa, y viceversa?

- **Recursiva:** cuando el problema se descompone naturalmente en subproblemas más pequeños (árboles, grafos, divide y vencerás) y la profundidad de recursión es manejable. El código suele ser más legible y corto.
- **Iterativa:** cuando se necesita eficiencia en memoria, cuando n puede ser muy grande (evitar StackOverflow), o cuando la versión recursiva repite trabajo innecesariamente (como Fibonacci sin memoización). En general, para problemas lineales simples (factorial, búsqueda), el iterativo es preferible.

---

## 8. Referencias

- Cormen, T. et al. *Introduction to Algorithms*, 4th Edition. MIT Press.
- Oracle. *Java SE Documentation — System.nanoTime()*. https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/System.html
- Sedgewick, R. & Wayne, K. *Algorithms*, 4th Edition. Addison-Wesley.
