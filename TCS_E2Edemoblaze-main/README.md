# Proyecto: Prueba E2E -- Flujo de Compra en Demoblaze

Automatización funcional con Serenity BDD (Screenplay Pattern)

Este repositorio contiene la solución al **Ejercicio 1**, que consiste
en implementar una **prueba funcional automatizada E2E** del flujo de
compra en:

🔗 https://www.demoblaze.com/

La prueba automatiza las siguientes acciones:

-   Agregar dos productos al carrito\
-   Visualizar el carrito\
-   Completar el formulario de compra\
-   Finalizar la compra

------------------------------------------------------------------------

## 🛠️ Tecnologías utilizadas

  Tecnología                Versión      Descripción
  ------------------------- ------------ ---------------------------------
  **Java**                  17           Lenguaje principal del proyecto
  **Gradle**                8.x          Sistema de construcción
  **Serenity BDD**          4.2.0        Framework para automatización
  **Serenity Screenplay**   4.2.0        Patrón Screenplay
  **JUnit 5**               5.10.0       Ejecutor de pruebas
  **Selenium WebDriver**    Integrado    Control del navegador
  **ChromeDriver**          Automático   Usado por Serenity
  **Logback**               1.5.8        Logging
  **Apache POI**            5.3.0        Manejo de Excel

------------------------------------------------------------------------

## 📂 Estructura del proyecto

    📦 Sofka_E2EDemoblaze
     ┣ 📁 src
     │  ┗ 📁 test
     │     ┣ 📁 java
     │     │  ┣ 📁 runners
     │     │  ┣ 📁 tasks
     │     │  ┣ 📁 interactions
     │     │  ┣ 📁 questions
     │     │  ┣ 📁 userinterface
     │     │  ┗ 📁 utils
     │     ┗ 📁 resources
     │         ┣ serenity.conf
     │         ┗ features
     ┣ build.gradle
     ┣ settings.gradle
     ┗ README.md

------------------------------------------------------------------------

## 📦 Requisitos previos

### ✔️ Java 17

Verificar con:

    java -version

### ✔️ Chrome instalado

------------------------------------------------------------------------

## ▶️ Cómo ejecutar el proyecto

### 1️⃣ Clonar el repositorio

    git clone https://github.com/tu-usuario/Sofka_E2Edemoblaze.git
    cd Sofka_E2Edemoblaze

### 2️⃣ Ejecutar las pruebas (con navegador)

    gradlew clean test -Dserenity.headless.mode=false

### 3️⃣ Reporte de Serenity

    target/site/serenity/index.html

------------------------------------------------------------------------

## 📝 Ejercicio solicitado

-   Agregar dos productos al carrito\
-   Visualizar el carrito\
-   Completar el formulario\
-   Finalizar la compra

------------------------------------------------------------------------

## ✔️ Conclusiones (resumen)

-   Serenity permite reportes claros y detallados.\
-   Screenplay facilita la mantenibilidad.\
-   El flujo de compra es totalmente automatizable.
