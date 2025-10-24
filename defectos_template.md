# Registro de Defectos (Template)

Este documento sirve como plantilla para documentar defectos encontrados durante la ejecución de pruebas unitarias.

---

## Formato 1: Lista detallada (narrativa)

### Defecto XX
- **Caso de prueba**: ...
- **Entrada**: ...
- **Resultado esperado**: ...
- **Resultado obtenido**: ...
- **Causa probable**: ...
- **Estado**: Abierto | En progreso | Resuelto

---

## Formato 2: Tabla de defectos (bug tracking)

| ID  | Caso de Prueba | Entrada | Resultado Esperado | Resultado Obtenido | Causa Probable | Estado |
|-----|----------------|---------|--------------------|--------------------|----------------|--------|
| 01  | Registrar Persona            |   Person("Ana", 1, 30, Gender.FEMALE, true)   | VALID             |        null       | No se ha aplicado la lógica de registrar una persona en  `registerVoter` y la función no retorna ningún RegistryResult | Cerrado |
| 02  | Persona Muerta            | Person("Carlos", 2, 40, Gender.MALE, false)     |      DEAD      | null                | No se ha implementado la validación de una persona muerta en `registerVoter` y la función no retorna ningún RegistryResult          | Cerrado |
| 03  | ID Duplicado            | Person("Francisco", 2, 76, Gender.MALE, true)     | DUPLICATED                | null                | No se ha programado la validación de una persona con ID duplicado en `registerVoter` y la función no retorna ningún RegistryResult          | Cerrado |
| 04  | Persona Nula           | null     | INVALID             | null                | No se ha programado la confirmación de que el objeto persona es nulo dentro de `registerVoter` y la función no retorna ningún RegistryResult         | Cerrado |
| 05  | Menor de Edad          | Person("Joel", 3, 17, Gender.MALE, true)    | UNDER_AGE                | null                | Falta Establecer límite inferior de edad en `registerVoter` y la función no retorna ningún RegistryResult         | Cerrado |
| 06  | Límite Inferior 18 años          | Person("Michael", 4, 18, Gender.MALE, true)     | VALID                | null                | No se está verificando la mayoría de edad en  `registerVoter` y la función no retorna ningún RegistryResult          | Cerrado |
| 07  | 18-120 Años         | Person("Luisa", 5, 120,Gender.FEMALE, true)     | VALID               | null                | Falta de establecimiento de Umbral de edad en  `registerVoter` y la función no retorna ningún RegistryResult        | Cerrado |
| 07  | Mayor 120          | Person("Sara", 6, 121,Gender.FEMALE, true)     | INVALID_aGE                | null                | Falta de establecimiento del límite superior de edad en `registerVoter` y la función no retorna ningún RegistryResult          | Cerrado |
| 08  | ID Duplicado            | Person("Francisco", 2, 76, Gender.MALE, true)     | DUPLICATED                | null                | Sólo se inserto 1 persona, no dos con el mismo ID          | Cerrado |

Validar Persona Person("Ana", 1, 30, Gender.FEMALE, true);
Persona Muerta Person("Carlos", 2, 40, Gender.MALE, false)
ID Duplicado Person("Francisco", 2, 76, Gender.MALE, true)
Persona Nula null
ID Negativo Person("Carlos", 0, 25, Gender.MALE, true)
Menor de Edad Person("Joel", 3, 17, Gender.MALE, true)
Mayor de Edad Person("Michael", 4, 18, Gender.MALE, true)
18-120 Años Person("Luisa", 5, 120,Gender.FEMALE, true)
Mayor 120 Person("Sara", 6, 121,Gender.FEMALE, true)
---

## Convenciones de Estado
- **Abierto** → El defecto aún no se corrige.  
- **En progreso** → El defecto está siendo trabajado.  
- **Resuelto** → El defecto fue corregido y validado con pruebas.  
