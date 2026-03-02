# Prueba TCS Ecuador – Automatización API Demoblaze

Automatización de pruebas de servicios REST utilizando **Karate DSL** sobre los endpoints de registro e inicio de sesión de la plataforma [Demoblaze](https://www.demoblaze.com).

---

## 1. Descripción del ejercicio

El sitio web [https://www.demoblaze.com](https://www.demoblaze.com) expone servicios REST para registro y autenticación de usuarios. El objetivo es automatizar y validar el comportamiento de los siguientes endpoints:

| Endpoint | URL |
|----------|-----|
| Signup   | `POST https://api.demoblaze.com/signup` |
| Login    | `POST https://api.demoblaze.com/login`  |

### Casos de prueba implementados

| ID | Descripción |
|----|-------------|
| CP1 | Crear un nuevo usuario en signup |
| CP2 | Intentar crear un usuario ya existente |
| CP3 | Login con usuario y contraseña correctos |
| CP4 | Login con usuario y contraseña incorrectos |

---

## 2. Requisitos previos

Antes de ejecutar el proyecto, asegúrese de contar con:

| Herramienta | Versión mínima | Verificación |
|-------------|---------------|--------------|
| Java JDK    | 8 o superior  | `java -version` |
| Apache Maven | 3.6 o superior | `mvn -version` |
| Karate DSL  | 1.2.0 (incluida en pom.xml) | — |

> Karate se descarga automáticamente como dependencia Maven. No requiere instalación adicional.

---

## 3. Estructura del proyecto

```
TCS_ApiKarateDemoglaze/
├── pom.xml                                        # Configuración Maven y dependencias
├── karate-config.js                               # Configuración global de Karate
├── readme.txt                                     # Instrucciones de ejecución
├── conclusiones.txt                               # Hallazgos del ejercicio
└── src/
    └── test/
        ├── java/karate/
        │   └── DemoblazeTest.java                 # Runner JUnit 5
        └── resources/features/
            └── demoblaze.feature                  # Casos de prueba Karate DSL
```

Los reportes se generan automáticamente en `target/karate-reports/` tras cada ejecución.

---

## 4. Instrucciones de ejecución

### Ejecutar todos los casos de prueba

```bash
mvn test
```

Resultado esperado en consola:

```
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Ejecutar un caso de prueba específico

```bash
# Caso 1 – Crear nuevo usuario
mvn test -Dkarate.options="--tags @id:CasodePrueba1"

# Caso 2 – Usuario ya existente
mvn test -Dkarate.options="--tags @id:CasodePrueba2"

# Caso 3 – Login correcto
mvn test -Dkarate.options="--tags @id:CasodePrueba3"

# Caso 4 – Login incorrecto
mvn test -Dkarate.options="--tags @id:CasodePrueba4"
```

### Ver el reporte HTML

Abrir en el navegador tras ejecutar las pruebas:

```
target/karate-reports/karate-summary.html
```

El reporte muestra: escenarios ejecutados, entradas enviadas, respuestas recibidas, validaciones y tiempos de ejecución.

---

## 5. Casos de prueba implementados

### CP1 – Crear nuevo usuario

**Endpoint:** `POST /signup`
**Descripción:** Registra un usuario nuevo con username único generado dinámicamente (timestamp).

| Campo     | Valor de entrada              |
|-----------|-------------------------------|
| username  | `testUser_<timestamp>`        |
| password  | `Password123`                 |

**Salida esperada:**

```json
HTTP 200
""
```

**Validación:** La respuesta es de tipo String (sin campo `errorMessage`).

---

### CP2 – Intentar crear un usuario ya existente

**Endpoint:** `POST /signup`
**Descripción:** Intenta registrar un usuario que ya existe en el sistema.

| Campo     | Valor de entrada  |
|-----------|-------------------|
| username  | `nuevoUsuario1`   |
| password  | `password1234`    |

**Salida esperada:**

```json
HTTP 200
{ "errorMessage": "This user already exist." }
```

**Validación:** El cuerpo de respuesta contiene el mensaje de error esperado.

---

### CP3 – Login con credenciales correctas

**Endpoint:** `POST /login`
**Descripción:** Autentica un usuario recién creado con credenciales válidas conocidas.

| Campo     | Valor de entrada              |
|-----------|-------------------------------|
| username  | `loginTest_<timestamp>`       |
| password  | `TestPass123`                 |

**Salida esperada:**

```json
HTTP 200
"Auth_token: <token_base64>"
```

**Validación:** La respuesta es un String no vacío (token de autenticación).

---

### CP4 – Login con contraseña incorrecta

**Endpoint:** `POST /login`
**Descripción:** Intenta autenticarse con un usuario válido pero contraseña incorrecta.

| Campo     | Valor de entrada            |
|-----------|-----------------------------|
| username  | `nuevoUsuario1`             |
| password  | `contrasenaIncorrecta999`   |

**Salida esperada:**

```json
HTTP 200
{ "errorMessage": "Wrong password." }
```

**Validación:** El cuerpo de respuesta contiene el mensaje de error esperado.

---

## 6. Código del archivo Feature

```gherkin
Feature: Prueba TCS Ecuador API Demoblaze

  Background:
    * url 'https://api.demoblaze.com'

  @id:CasodePrueba1
  Scenario: Crear nuevo Usuario exitosamente
    * def timestamp = java.lang.System.currentTimeMillis() + ''
    * def uniqueUser = 'testUser_' + timestamp
    Given path '/signup'
    And request { username: '#(uniqueUser)', password: 'Password123' }
    When method post
    Then status 200
    And match response == '#string'

  @id:CasodePrueba2
  Scenario: Intentar crear un usuario ya existente
    Given path '/signup'
    And request { username: 'nuevoUsuario1', password: 'password1234' }
    When method post
    Then status 200
    And match response == { errorMessage: 'This user already exist.' }

  @id:CasodePrueba3
  Scenario: Login con usuario y contrasena correctos
    * def timestamp = java.lang.System.currentTimeMillis() + ''
    * def testUser = 'loginTest_' + timestamp
    * def testPass = 'TestPass123'
    Given path '/signup'
    And request { username: '#(testUser)', password: '#(testPass)' }
    When method post
    Then status 200
    Given path '/login'
    And request { username: '#(testUser)', password: '#(testPass)' }
    When method post
    Then status 200
    And match response == '#string'
    And match response != ''

  @id:CasodePrueba4
  Scenario: Login con usuario y contrasena incorrectos
    Given path '/login'
    And request { username: 'nuevoUsuario1', password: 'contrasenaIncorrecta999' }
    When method post
    Then status 200
    And match response == { errorMessage: 'Wrong password.' }
```

---

## 7. Tecnologías utilizadas

| Tecnología    | Versión | Propósito                        |
|---------------|---------|----------------------------------|
| Karate DSL    | 1.2.0   | Framework de pruebas REST (BDD)  |
| JUnit 5       | —       | Motor de ejecución de pruebas    |
| Apache Maven  | 3.6+    | Gestión de dependencias y build  |
| Java          | 8+      | Plataforma de ejecución          |
