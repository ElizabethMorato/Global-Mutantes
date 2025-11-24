    MUTANT DETECTOR API
        
El sistema permite detectar si un humano es mutante a partir de su secuencia de ADN, 
exponiendo la funcionalidad mediante una API REST y almacenando estadísticas en 
una base de datos H2.

TECNOLOGÍAS UTILIZADAS
------------------------------------------------------------
- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Lombok
- Gradle
- Swagger / OpenAPI
- Render (deploy en cloud)

------------------------------------------------------------
ARQUITECTURA DEL PROYECTO

org.example
├── controller      → Controladores REST
├── service         → Lógica de negocio + algoritmo mutante
├── repository      → Persistencia JPA (H2)
├── entity          → Entidades de base de datos
├── dto             → Objetos de Request/Response
├── validation      → Validación custom de ADN
├── exception       → Manejo global de errores
└── config          → Configuración de Swagger/OpenAPI

------------------------------------------------------------
ALGORITMO isMutant

- El ADN debe ser una matriz NxN válida 
- Se buscan secuencias de 4 letras iguales en:
  → Horizontal
  → Vertical
  → Diagonal ↘
  → Diagonal inversa ↗

- Early exit: si se encuentran 2 o más secuencias, retorna TRUE.

------------------------------------------------------------
POST /mutant

Request Body:
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Responses:
200 OK         → Mutante detectado
403 Forbidden  → No mutante
400 Bad Req    → ADN inválido

Ejemplo curl:

curl -X POST https://tu-api.onrender.com/mutant \
-H "Content-Type: application/json" \
-d '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'

------------------------------------------------------------
GET /stats

{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}

------------------------------------------------------------
PERSISTENCIA

- Base H2 
- Se guarda uno registro DNA 
- Se almacena:
    * hash SHA-256
    * si es mutante o no
    * timestamp
