# Tabla de defectos (bug tracking)

| ID  | Caso de Prueba | Entrada | Resultado Esperado | Resultado Obtenido | Causa Probable | Estado |
|-----|----------------|---------|--------------------|--------------------|----------------|--------|
| 01  | Registrar Persona            |   Person("Ana", 1, 30, Gender.FEMALE, true)   | VALID             |        null       | No se ha aplicado la lógica de registrar una persona en  `registerVoter` y la función no retorna ningún RegistryResult | Resuelto |
| 02  | Persona Muerta            | Person("Carlos", 2, 40, Gender.MALE, false)     |      DEAD      | null                | No se ha implementado la validación de una persona muerta en `registerVoter` y la función no retorna ningún RegistryResult          | Resuelto |
| 03  | ID Duplicado            | Person("Francisco", 2, 76, Gender.MALE, true)     | DUPLICATED                | null                | No se ha programado la validación de una persona con ID duplicado en `registerVoter` y la función no retorna ningún RegistryResult          | Resuelto |
| 04  | Persona Nula           | null     | INVALID             | null                | No se ha programado la confirmación de que el objeto persona es nulo dentro de `registerVoter` y la función no retorna ningún RegistryResult         | Resuelto |
| 05  | Menor de Edad          | Person("Joel", 3, 17, Gender.MALE, true)    | UNDER_AGE                | null                | Falta Establecer límite inferior de edad en `registerVoter` y la función no retorna ningún RegistryResult         | Resuelto |
| 06  | Límite Inferior 18 años          | Person("Michael", 4, 18, Gender.MALE, true)     | VALID                | null                | No se está verificando la mayoría de edad en  `registerVoter` y la función no retorna ningún RegistryResult          | Resuelto |
| 07  | 18-120 Años         | Person("Luisa", 5, 120,Gender.FEMALE, true)     | VALID               | null                | Falta de establecimiento de Umbral de edad en  `registerVoter` y la función no retorna ningún RegistryResult        | Resuelto |
| 07  | Mayor 120          | Person("Sara", 6, 121,Gender.FEMALE, true)     | INVALID_aGE                | null                | Falta de establecimiento del límite superior de edad en `registerVoter` y la función no retorna ningún RegistryResult          | Resuelto |
| 08  | ID Duplicado            | Person("Francisco", 2, 76, Gender.MALE, true)     | DUPLICATED                | null                | Sólo se inserto 1 persona, no dos con el mismo ID          | Resuelto |

