    Mutant Detector API – MercadoLibre
El sistema permite detectar si un humano es mutante a partir de su secuencia de ADN, 
exponiendo la funcionalidad mediante una API REST y almacenando estadísticas en 
una base de datos H2.

TECNOLIGIAS UTILIZADAS:
Java 17

Spring Boot 3

Spring Web

Spring Data JPA

Spring Validation

H2 Database

Lombok

Gradle

Swagger / OpenAPI

Render (deploy en cloud)
 ARQUITECTURA DEL PROYECTO;
org.example
├── controller      → Controladores REST
├── service         → Lógica de negocio + algoritmo mutante
├── repository      → Persistencia JPA
├── entity          → Entidades de BD
├── dto             → Objetos de Request/Response
├── validation      → Validación custom del ADN
├── exception       → Manejo global de errores
└── config          → Swagger/OpenAPI

Detección de Mutantes isMutant
Validación de matriz NxN (solo A/T/C/G)

Búsqueda horizontal

Búsqueda vertical

Búsqueda diagonal ↘

Búsqueda diagonal inversa ↗

Early exit: apenas se encuentren 2 o más secuencias, retorna true.

API REST
Detecta si un ADN pertenece a un mutante.
{
"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
Responses
Código	Descripción
200 OK	Mutante detectado
403 Forbidden	No mutante
400 Bad Request	ADN inválido (no NxN o caracteres incorrectos)
Ejemplo (mutante)
curl -X POST https://tu-api.onrender.com/mutant \
-H "Content-Type: application/json" \
-d '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'

GET /stats
Devuelve estadísticas de ADN analizados.
{
"count_mutant_dna": 40,
"count_human_dna": 100,
"ratio": 0.4
}
Persistencia
Se usa una base de datos H2 en memoria.

Se guarda un solo registro por ADN, usando hash SHA-256.

Se registra:

hash del ADN

si es mutante o no

timestamp

Tabla: dna_records"# Global" 
