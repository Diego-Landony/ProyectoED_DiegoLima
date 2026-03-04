# Prompts utilizados con IA (Claude Code)

## Anexo de Integridad Academica
Se documenta cada prompt utilizado durante el desarrollo del proyecto, tal como lo requiere la politica del examen parcial.

---

## Prompt 1 — Analisis de requisitos y diagnostico del proyecto

```
Analiza la estructura completa del proyecto Java y comparala contra los requisitos del PDF del examen
parcial. Identifica que algoritmos ya estan implementados, cuales faltan, y verifica compatibilidad
con el entorno Linux. Genera un reporte de cumplimiento.
```

**Resultado:** Se realizo un analisis comparativo entre la estructura actual del proyecto (que unicamente
contenia `Fibonacci.java` con sus versiones iterativa y recursiva) y los requisitos del PDF del examen.
Se identificaron los siguientes faltantes:
- 3 clases de algoritmos: `Factorial.java`, `BusquedaLineal.java`, `OrdenamientoBurbuja.java` (6 implementaciones)
- Clase utilitaria `GeneradorDatos.java` en el paquete `utils/` para generar arreglos de prueba
- Integracion de los 4 algoritmos en `Main.java` con sus respectivos tamanos de entrada
- Se confirmo compatibilidad con Linux (SDKMAN nativo, Java 21 Temurin compatible con el requisito de Java 11+)

---

## Prompt 2 — Implementacion de algoritmos y benchmark completo

```
Implementa el resto por favor:
- src/algorithms/Factorial.java (iterativo + recursivo)
- src/algorithms/BusquedaLineal.java (iterativo + recursivo)
- src/algorithms/OrdenamientoBurbuja.java (iterativo + recursivo)
- src/utils/GeneradorDatos.java (genera arreglos para A3 y A4)
- Actualizar Main.java para que mida los 4 algoritmos con los tamanos correctos:
  - Factorial y Fibonacci: n = 5, 10, 15, 20, 25, 30
  - Busqueda Lineal y Burbuja: n = 100, 500, 1000, 5000, 10000
- Que compile y ejecute correctamente
- Que exporte todos los resultados al CSV
```

**Resultado:** Se generaron los siguientes archivos y modificaciones:

| Archivo | Descripcion tecnica |
|---------|-------------------|
| `Factorial.java` | Iterativo con ciclo acumulador O(n), recursivo con caso base `0!=1` y llamada `n*fact(n-1)` O(n). Limite `n<=20` por desbordamiento de `long`. |
| `BusquedaLineal.java` | Iterativo con recorrido secuencial O(n), recursivo con metodo auxiliar que incrementa el indice en cada llamada O(n). Retorna indice o -1. |
| `OrdenamientoBurbuja.java` | Iterativo con ciclos anidados O(n^2), recursivo donde cada llamada ejecuta una pasada y reduce `n` en 1, equivalente a O(n^2). Ordenamiento in-place. |
| `GeneradorDatos.java` | Genera arreglos de `n` enteros aleatorios con semilla fija (`Random(42)`) para reproducibilidad. Incluye metodo `copiar()` para que Burbuja trabaje con datos frescos en cada medicion. |
| `Main.java` | Se reestructuro para ejecutar benchmarks de los 4 algoritmos. Usa `Medidor.medir()` con `System.nanoTime()` promediando 10 ejecuciones. Exporta resultados a `resultados/tiempos.csv`. |

Compilacion: `javac -d out src/algorithms/*.java src/utils/*.java src/benchmark/*.java`
Ejecucion: `java -cp out benchmark.Main`

---

## Prompt 3 — Llenado automatico del archivo Excel con datos reales

```
Lee el archivo Excel 'Tiempos Rendimiento.xlsx' y llena la hoja '1. Datos_Crudos' con los tiempos
de ejecucion reales. Ejecuta el benchmark de Java 5 veces para obtener las 5 ejecuciones
independientes que requiere cada fila (columnas Ej.1 a Ej.5).
```

**Resultado:** Se utilizo un script en Python con la libreria `openpyxl` para:
- Ejecutar `java -cp out benchmark.Main` 5 veces de forma independiente
- Parsear el CSV generado en cada ejecucion para extraer los tiempos por algoritmo, version y n
- Mapear los 44 registros del CSV a las 47 filas de datos de la hoja `1. Datos_Crudos`
- Escribir 220 celdas (44 filas x 5 ejecuciones) en las columnas E a I
- Las formulas `=AVERAGE(E:I)` en columna J y las formulas `AVERAGEIFS` en las hojas de cada algoritmo calculan los promedios automaticamente

---

## Prompt 4 — Generacion de este anexo de prompts

```
Genera un archivo prompts.md que documente todos los prompts utilizados durante el desarrollo
del proyecto, incluyendo una descripcion tecnica de los resultados obtenidos en cada caso.
Esto es para el anexo de integridad academica requerido por el examen.
```

**Resultado:** Se genero este archivo como anexo de integridad academica, documentando los prompts
enviados a la IA y una descripcion tecnica de lo que se obtuvo como respuesta en cada caso.

---

## Herramientas utilizadas

| Herramienta | Version / Detalle | Uso |
|-------------|-------------------|-----|
| **Claude Code** | CLI de Anthropic, modelo Claude Opus 4 | Generacion de codigo, analisis de requisitos, automatizacion |
| **Java (Temurin)** | 21.0.6 via SDKMAN | Compilacion y ejecucion del proyecto |
| **VS Code** | Extension Pack for Java | IDE de desarrollo |
| **Python 3** | 3.12 + openpyxl 3.1.5 | Script para llenar el Excel con datos de benchmark |
| **Sistema Operativo** | Linux (Ubuntu 24.04, kernel 6.17) | Entorno de desarrollo |
| **SDKMAN** | Gestion de versiones de Java | Instalacion y cambio de JDK |
