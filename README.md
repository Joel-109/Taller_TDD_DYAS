# Proyecto: Registro de Votantes (Clases de Equivalencia)}

**Integrantes**
- Julian Pedraza
- Camilo Otalora
- Joel Alba

## Descripción del dominio:
Este proyecto implementa un sistema básico de registro de votantes. El dominio está centrado en la clase Person, que representa a un ciudadano con los siguientes atributos:

- **name:** nombre de la persona

- **id:** número identificador único ```(no puede repetirse ni ser negativo o cero)```

- **age:** edad ```(solo mayores de 18 y menores o iguales a 120 pueden registrarse)```

- **gender:** género de la persona ```(MALE, FEMALE, UNDENTIFIED)```

- **alive:** estado de vida (solo personas vivas pueden registrarse)

El servicio principal Registry se encarga de aplicar las reglas del dominio y devolver un resultado de tipo RegisterResult.

### Reglas validadas:

1. **Nulidad:** No se permite registrar una persona nula.

2. **Estado de vida:** Solo se pueden registrar personas vivas.

3. **Identificador:**

- El ID debe ser mayor que 0.

- No se permiten IDs duplicados.

4. **Edad:**

- Menores de 0 o mayores de 120 → ```INVALID_AGE.```

- Menores de 18 → ```UNDERAGE.```

- De 18 a 120 años → ```VALID.```

## Instrucciones para compilar y ejecutar pruebas:

1. Abre una terminal en la raíz del proyecto.

2. Ejecuta los siguientes comandos Maven:

```
mvn clean test
```

Este comando:

* clean -> Limpia los archivos de compilación previos.

* test -> Ejecuta todos los casos de prueba unitarios usando JUnit 4.13.2.

Los resultados de ejecución y cobertura (si está habilitado JaCoCo) se generan en:
```
target/site/jacoco/index.html
```

### Desarrollo guiado por pruebas (TDD):
El desarrollo de este proyecto siguió el ciclo TDD (Test Driven Development), que consiste en tres fases:

**Red:** Primero se escribe una prueba unitaria que falla, ya que la funcionalidad aún no está implementada. Se aplicó al proyecto en el commit "RED", donde la función de `registerVoter(person)` retornaría como resultado un valor `null`, consiguiendo que el assert de cada prueba fallara. El commit mencionado contiene el RED de TDD para cada prueba unitaria desarrollada.

**Green:** Luego se implementa la lógica mínima necesaria en el código de producción (Registry) para que la prueba pase. En este sentido, el commit "GREEN" contiene el desarrollo básico de la función `registerVoter(person)`, de modo que cumpliera con cada prueba unitaria definida.

**Refactor:** Finalmente se refactoriza el código, manteniendo las pruebas en verde y mejorando la legibilidad y estructura. De esta manera, el commit "REFACTOR" posee la lógica limpia y optimizada, que permite pasar las pruebas unitarias desarrolladas.

Los resultados de cada etapa se exponen en el apartado de **Anexos**.

Conforme a lo anterior, algunas de las pruebas unitarias desarrolladas están en torno a los siguientes aspectos:

* Verificación de nulidad.

* Estado de vida.

* Validación de ID.

* Reglas de edad.

* Detección de duplicados.

### Patrón AAA (Arrange – Act – Assert):
Cada prueba unitaria sigue la estructura AAA, que mejora la claridad y separación de responsabilidades en los tests:

**Arrange:** Se preparan los objetos y datos de prueba ```(por ejemplo, Registry registry = new Registry())```.

**Act:** Se ejecuta la acción que se desea probar ```(por ejemplo, RegisterResult result = registry.registerVoter(person))```.

**Assert:** Se verifica el resultado esperado ```(por ejemplo, Assert.assertEquals(RegisterResult.VALID, result))```.

### Dependencias principales:

* JUnit 4.13.2 – Framework de pruebas unitarias.

* JaCoCo 0.8.12 – Generación de reportes de cobertura de código.

* Maven Surefire 3.2.5 – Ejecutor de pruebas.

### Pruebas unitarias

Las pruebas unitarias fueron diseñadas bajo el enfoque de Clases de Equivalencia y Escenarios BDD, asegurando que cada regla del dominio estuviera validada por al menos una prueba. Todas las pruebas se escribieron utilizando el patrón AAA (Arrange – Act – Assert) para mantener claridad y consistencia.

**Clases de Equivalencia**

| Caso de Prueba | Entrada | Resultado Esperado | Escenario BDD |
|----------------|---------|-------------------|--------------|
| **Persona válida** | `Person("Ana", 1, 30, FEMALE, true)` | **VALID** | Dado que la persona es mayor de edad, tiene ID válido y está viva, cuando se registra, entonces el resultado debe ser VALID. |
| **Persona muerta** | `Person("Carlos", 2, 40, MALE, false)` | **DEAD** | Dado que la persona está muerta, cuando se intenta registrar, entonces el sistema debe rechazarla con resultado DEAD. |
| **Persona con ID duplicado** | `Person("Javier", 2, 34, MALE, true)` y `Person("Francisco", 2, 76, MALE, true)` | **DUPLICATED** | Dado que ya existe una persona registrada con el mismo ID, cuando se intenta registrar otra con ese mismo ID, entonces el sistema debe devolver DUPLICATED. |
| **Persona nula** | `null` | **INVALID** | Dado que la persona no existe (es null), cuando se intenta registrar, entonces el sistema debe devolver INVALID. |
| **Persona con ID inválido** | `Person("Carlos", 0, 25, MALE, true)` | **INVALID** | Dado que el ID no es mayor que 0, cuando se intenta registrar, entonces el sistema debe devolver INVALID. |
| **Persona menor de edad** | `Person("Joel", 3, 17, MALE, true)` | **UNDERAGE** | Dado que la persona tiene menos de 18 años, cuando se intenta registrar, entonces el sistema debe devolver UNDERAGE. |
| **Persona en límite inferior de edad** | `Person("Michael", 4, 18, MALE, true)` | **VALID** | Dado que la persona tiene exactamente 18 años, cuando se registra, entonces el resultado debe ser VALID. |
| **Persona en límite superior de edad** | `Person("Luisa", 5, 120, FEMALE, true)` | **VALID** | Dado que la persona tiene la edad máxima permitida (120 años), cuando se registra, entonces el sistema debe devolver VALID. |
| **Persona con edad inválida** | `Person("Sara", 6, 121, FEMALE, true)` | **INVALID_AGE** | Dado que la persona supera el límite máximo de edad permitido, cuando se intenta registrar, entonces el sistema debe devolver INVALID_AGE. |

### Aplicación del patrón AAA (Arrange - Act- Assert)

Todas las pruebas en la clase RegistryTest se desarrollaron bajo este patrón:

* Arrange: Se crean los objetos de prueba, por ejemplo, Registry registry = new Registry(); Person person = new Person(...);

* Act: Se ejecuta la acción que se desea probar, por ejemplo, RegisterResult result = registry.registerVoter(person);

* Assert: Se verifica que el resultado sea el esperado, por ejemplo, Assert.assertEquals(RegisterResult.VALID, result);

Ejemplo práctico:

```
@Test
public void shouldRegisterValidPerson() {
```
```
// Arrange
Registry registry = new Registry();
Person person = new Person("Ana", 1, 30, Gender.FEMALE, true);
```
```
// Act
RegisterResult result = registry.registerVoter(person);
```

```
// Assert
Assert.assertEquals(RegisterResult.VALID, result);
```

## Nomenclatura de métodos y estructura de pruebas

Todas las pruebas unitarias siguen una nomenclatura descriptiva y clara, basada en el formato should[Acción][Condición], lo que permite entender rápidamente el comportamiento esperado sin necesidad de leer la implementación.

**Ejemplos de métodos**

* shouldRegisterValidPerson()

* shouldRejectDeadPerson()

* shouldRejectIdDuplicated()

* shouldRejectWhenPersonIsNull()

* shouldRejectWhenIdIsZeroOrNegative()

* shouldRejectUnderageAt17()

* shouldAcceptAdultAt18()

* shouldAcceptMaxAge120()

* shouldRejectInvalidAgeOver120()

Cada método contiene un único assert principal, o varios únicamente si verifican la misma intención lógica (por ejemplo, asegurar que un mismo comportamiento se mantiene ante distintas entradas).
Esto garantiza claridad, legibilidad y trazabilidad en la relación entre pruebas y reglas de negocio.

# Cobertura de código (JaCoCo)

Se utilizó el plugin JaCoCo integrado en Maven para medir la cobertura de las pruebas.
El reporte se genera automáticamente al ejecutar el comando:

```
mvn clean test
```
El reporte HTML se encuentra en la ruta:

```
target/site/jacoco/index.html
```
**Resultados obtenidos:**


![Jacoco](https://github.com/Joel-109/Taller_TDD_DYAS/blob/main/img/JACOCO-REPORT.png)
**Resumen general**

Tipo de cobertura | Total cubierto | Total faltante | Porcentaje
-- | -- | -- | --
Instrucciones (líneas de código ejecutadas) | 162 de 176 | 14 no ejecutadas | 92 %
Ramas de decisión (if / else) | 15 de 18 | 3 no ejecutadas | 83 %
Métodos cubiertos | 11 de 12 | 1 sin probar | 92 %
Clases cubiertas | 4 de 5 | 1 sin probar | 80 %

**Análisis por paquete**

Paquete | Cobertura de instrucciones | Cobertura de ramas | Comentario
-- | -- | -- | --
edu.unisabana.tyvs | 0 % | N/A | Es el paquete raíz del proyecto, sin lógica implementada, por eso no muestra cobertura.
edu.unisabana.tyvs.domain.model | 93 % | N/A | Muy buena cobertura en las clases del modelo (Person, Gender, RegisterResult). Solo faltan algunos métodos simples como getters.
edu.unisabana.tyvs.domain.service | 98 % | 83 % | Excelente cobertura del servicio Registry. Solo faltan unas pocas ramas poco comunes, como edad negativa o valores no probados.


Como podemos observar la cobertura global obtenida fue de 92 % de líneas y 83 % de ramas, lo que se considera muy alto tanto a nivel académico como profesional.
Esto significa que las pruebas implementadas verifican prácticamente todos los caminos y condiciones posibles dentro de la clase Registry.

Los pocos casos no cubiertos se deben a condiciones límite que no se probaron, como una persona con edad negativa o escenarios extremos de validación.
Estos casos no afectan la funcionalidad principal, pero podrían incluirse en el futuro para lograr un 100 % de cobertura. Aun así el nivel de cobertura alcanzado demuestra que las pruebas unitarias cumplen su propósito y que la implementación tiene una alta calidad y fiabilidad.


### Evidencia de TDD

El desarrollo de la clase ```Registry``` se realizó siguiendo el enfoque TDD (Test-Driven Development), aplicando el ciclo iterativo Rojo → Verde → Refactor en cada regla de negocio implementada.

A continuación, se describe la historia del desarrollo con al menos tres iteraciones representativas:

### Iteración 1: Validación de persona nula

* **Rojo (RED):**
Se escribió la prueba ```shouldRejectWhenPersonIsNull()```, la cual falló porque el método ```registerVoter()``` no manejaba el caso de un objeto ```Person``` nulo (resultado obtenido: ```null```).

* **Verde (GREEN):**
Se implementó la validación inicial dentro de ```registerVoter()```:

```
if (p == null) {
    return RegisterResult.INVALID;
}
```
Al ejecutar ```mvn clean test```, todas las pruebas existentes pasaron correctamente.

* **Refactor (REFACTOR):**
Se mantuvo el código limpio, sin duplicación, y se ajustaron los comentarios y nombres de variables para mayor claridad.

### Iteración 2: Validación de estado de vida (persona muerta)

* Rojo (RED):
Se agregó la prueba ```shouldRejectDeadPerson()```.
El test falló con salida esperada ```<DEAD>``` pero obtenida ```<null>```.

* Verde (GREEN):
Se añadió la condición correspondiente:
```
if (!p.isAlive()) {
    return RegisterResult.DEAD;
}
```
* Refactor (REFACTOR):
Se reutilizaron las variables locales ```alive```, ```age```, ```id``` para reducir llamadas repetidas a los getters y mejorar legibilidad.

### Iteración 3: Validación de duplicados e identificación

* Rojo (RED):
Se creó el test ```shouldRejectIdDuplicated()```, que falló inicialmente porque ```registerVoter()``` no verificaba duplicidad.

* Verde (GREEN):
Se agregó un bucle para recorrer la lista de personas registradas:

```
for (Person person : this.personsArray) {
    if (person.getId() == p.getId()) {
        return RegisterResult.DUPLICATED;
    }
}
```
La prueba pasó exitosamente junto con las anteriores.

* Refactor (REFACTOR):
Se extrajeron constantes ```minAge``` y ```maxAge```, y se ordenaron las validaciones para mantener coherencia de lectura.

### Iteración 4 (adicional): Validaciones de edad

* Rojo (RED):
Los tests ```shouldRejectUnderageAt17()``` y ```shouldRejectInvalidAgeOver120()``` fallaban con valores esperados ```<UNDERAGE>``` y ```<INVALID_AGE>```.

* Verde (GREEN):
Se implementaron las reglas de límite de edad:

```
if (age < 0 || age > this.maxAge) {
    return RegisterResult.INVALID_AGE;
} else if (age >= 0 && age < this.minAge) {
    return RegisterResult.UNDERAGE;
}
```

Ambos tests pasaron.

* Refactor (REFACTOR):
Se reorganizó el método y se añadieron comentarios explicativos mínimos.

## Resumen del ciclo TDD aplicado

Cada cambio en el código siguió el patrón:

1. Rojo: Crear o ejecutar una prueba que falla.

2. Verde: Implementar el código mínimo necesario para que la prueba pase.

3. Refactor: Limpiar, extraer constantes y mejorar la legibilidad sin romper las pruebas.

### Matriz de pruebas:

| Clase de equivalencia                      | Valores límite / Condición                              | Entrada (Person)                                                                         | Resultado esperado           | Test que lo cubre (`@Test` método)            |
| ------------------------------------------ | ------------------------------------------------------- | ---------------------------------------------------------------------------------------- | ---------------------------- | --------------------------------------------- |
| Persona válida (adulto)                    | `18 <= edad <= 120`, `id > 0`, `alive = true`, id único | `Person("Ana", 1, 30, Gender.FEMALE, true)`                                              | `RegisterResult.VALID`       | `shouldRegisterValidPerson`                   |
| Persona muerta                             | `alive = false`                                         | `Person("Carlos", 2, 40, Gender.MALE, false)`                                            | `RegisterResult.DEAD`        | `shouldRejectDeadPerson`                      |
| ID duplicado                               | `id` ya registrado en `Registry`                        | registro previo: `Person("Javier", 2, 34, ...)`, luego `Person("Francisco", 2, 76, ...)` | `RegisterResult.DUPLICATED`  | `shouldRejectIdDuplicated`                    |
| Persona nula                               | `person == null`                                        | `null`                                                                                   | `RegisterResult.INVALID`     | `shouldRejectWhenPersonIsNull`                |
| ID inválido                                | `id <= 0` (ej. `0`, `-5`)                               | `Person("Carlos", 0, 25, Gender.MALE, true)`                                             | `RegisterResult.INVALID`     | `shouldRejectWhenIdIsZeroOrNegative`          |
| Persona menor de edad                      | `edad < 18` (ej. `17`)                                  | `Person("Joel", 3, 17, Gender.MALE, true)`                                               | `RegisterResult.UNDERAGE`    | `shouldRejectUnderageAt17`                    |
| Límite inferior (edad mínima válida)       | `edad = 18`                                             | `Person("Michael", 4, 18, Gender.MALE, true)`                                            | `RegisterResult.VALID`       | `shouldAcceptAdultAt18`                       |
| Límite superior (edad máxima válida)       | `edad = 120`                                            | `Person("Luisa", 5, 120, Gender.FEMALE, true)`                                           | `RegisterResult.VALID`       | `shouldAcceptMaxAge120`                       |
| Edad inválida (sobre el máximo)            | `edad > 120` (ej. `121`)                                | `Person("Sara", 6, 121, Gender.FEMALE, true)`                                            | `RegisterResult.INVALID_AGE` | `shouldRejectInvalidAgeOver120`               |

### Calidad del código

#### Constantes extraídas

El programa maneja correctamente los valores fijos relacionados con la edad mínima y máxima para registrarse como votante (18 y 120). Estos valores están definidos dentro de la clase Registry, lo que permite mantener la coherencia del sistema y facilita hacer cambios si en el futuro las reglas cambian.
Aunque no están declarados como constantes (final), su uso es estable y no cambia durante la ejecución, por lo que funcionan efectivamente como tales. Además, los límites están probados en los tests (con personas de 17, 18, 120 y 121 años), lo cual garantiza que el comportamiento sea correcto en los valores frontera.

#### Sin código muerto ni duplicado

En el código no hay fragmentos innecesarios ni duplicaciones.
Cada clase tiene un propósito claro:

* Person solo guarda los datos del votante.

* Registry contiene la lógica principal de registro y validación.

* Los tests en RegistryTest prueban cada caso específico, sin repetir pasos o datos innecesarios.

Esto demuestra una buena organización y separación de responsabilidades, algo importante para mantener el código limpio y fácil de modificar.

#### Comentarios mínimos y nombres claros

Los comentarios se usan solo donde son útiles, sobre todo en los tests, donde explican de manera corta qué se está probando (Arrange, Act, Assert).
El resto del código no necesita muchos comentarios porque los nombres son autoexplicativos: métodos como isAlive, registerVoter o shouldRejectUnderageAt17 ya dejan claro lo que hacen. Gracias a esto, el código se entiende rápido y no hay que leer comentarios extensos para saber qué pasa.

### Reflexión sobre las pruebas y posibles mejoras

### Escenarios no cubiertos

Aunque los tests cubren muchos casos importantes, hay algunos escenarios que no se probaron y podrían servir para fortalecer el sistema:

1. Edad igual a 0 o negativa: sería útil probar qué pasa con edades como 0 o -5, además del caso actual.

2. IDs muy grandes o fuera de rango: no se prueba qué sucede con identificadores extremadamente altos.

3. Casos combinados: por ejemplo, una persona muerta y menor de edad, o un ID duplicado con edad inválida. Esto ayudaría a comprobar que el sistema prioriza correctamente las validaciones.

4. Registros simultáneos: no se evalúa cómo se comporta el sistema si varias personas se registran al mismo tiempo (algo más relevante en un contexto real o multiusuario).

#### Defectos detectados por los tests

Los tests no encontraron errores reales en el código: todos los casos dieron los resultados esperados.
El método registerVoter funciona bien al identificar personas muertas, duplicadas o con edades fuera del rango permitido.
También reconoce correctamente los casos válidos en los límites (18 y 120 años).
Esto muestra que la lógica actual es consistente y cubre las reglas principales sin comportamientos inesperados.

#### Cómo se podría mejorar la clase Registry?

Aunque el código funciona correctamente, se podrían hacer algunos ajustes para facilitar las pruebas y el mantenimiento:

* Configurar edades por parámetro: permitir pasar la edad mínima y máxima por el constructor ayudaría a probar otros rangos fácilmente sin modificar el código.

* Separar la validación: mover las validaciones a una clase o método aparte (por ejemplo, AgeValidator) haría que el código fuera más fácil de probar por partes.

* Usar estructuras más adecuadas: en lugar de ArrayList, se podría usar un Set para evitar duplicados automáticamente.

# Anexos

## RED CMD
![Alt text](https://github.com/Joel-109/Taller_TDD_DYAS/blob/main/img/RED.png)
## Green
![Alt text](https://github.com/Joel-109/Taller_TDD_DYAS/blob/main/img/GREEN_CODE.png)
## Green CMD
![Alt text](https://github.com/Joel-109/Taller_TDD_DYAS/blob/main/img/GREEN_CMD.png)
## REFACTOR CMD
![Alt text](https://github.com/Joel-109/Taller_TDD_DYAS/blob/main/img/REFACTOR_CODE.png)
## REFACTOR CMD
![Alt text](https://github.com/Joel-109/Taller_TDD_DYAS/blob/main/img/REFACTOR_CMD.png)
