========================================================
  PRUEBAS API REST - DEMOBLAZE (Signup y Login)
  Herramienta: Karate Framework + JUnit 5 + Maven
========================================================

DESCRIPCION
-----------
Este proyecto contiene pruebas automatizadas de los servicios REST
de la pagina https://www.demoblaze.com/, cubriendo los endpoints:

  - POST https://api.demoblaze.com/signup
  - POST https://api.demoblaze.com/login

Se implementaron 4 casos de prueba:
  1. Crear un nuevo usuario en signup
  2. Intentar crear un usuario ya existente
  3. Login con usuario y contrasena correctos
  4. Login con usuario y contrasena incorrectos

========================================================
  REQUISITOS PREVIOS
========================================================

1. Java Development Kit (JDK) 8 o superior
   Verificar con: java -version

2. Apache Maven 3.6 o superior
   Verificar con: mvn -version

3. Conexion a internet (las pruebas consumen la API real de demoblaze)

4. El usuario 'nuevoUsuario1' debe existir en demoblaze (usado en Casos 2 y 4).
   Si no existe, ejecutar una vez el Caso 1 con ese usuario o crearlo
   manualmente desde https://www.demoblaze.com/

========================================================
  ESTRUCTURA DEL PROYECTO
========================================================

TCS_ApiKarateDemoglaze-main/
  pom.xml                                          -> Configuracion Maven y dependencias
  karate-config.js                                 -> Configuracion global de Karate
  readme.txt                                       -> Este archivo
  conclusiones.txt                                 -> Hallazgos del ejercicio
  src/
    test/
      java/karate/
        DemoblazeTest.java                         -> Runner JUnit 5
      resources/features/
        demoblaze.feature                          -> Casos de prueba Karate
  target/
    karate-reports/
      karate-summary.html                          -> Reporte HTML de resultados
      features.demoblaze.html                      -> Detalle por feature

========================================================
  PASOS DE EJECUCION
========================================================

OPCION A - Ejecutar todas las pruebas (recomendado):
----------------------------------------------------
1. Abrir una terminal/consola
2. Navegar a la carpeta raiz del proyecto:
   cd "TCS_ApiKarateDemoglaze-main"
3. Ejecutar el siguiente comando:
   mvn test
4. Al finalizar se mostrara en consola:
   [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
   [INFO] BUILD SUCCESS

OPCION B - Ejecutar un caso de prueba especifico:
-------------------------------------------------
Reemplazar @id:CasodePruebaX con el tag deseado:

  mvn test -Dkarate.options="--tags @id:CasodePrueba1"
  mvn test -Dkarate.options="--tags @id:CasodePrueba2"
  mvn test -Dkarate.options="--tags @id:CasodePrueba3"
  mvn test -Dkarate.options="--tags @id:CasodePrueba4"

========================================================
  VISUALIZAR EL REPORTE
========================================================

Despues de ejecutar las pruebas, abrir en el navegador:
  target/karate-reports/karate-summary.html

El reporte muestra:
  - Resumen de escenarios ejecutados (pasados/fallidos)
  - Detalle de cada paso (request, response, assertions)
  - Tiempo de ejecucion por escenario
  - Entradas y salidas capturadas con el comando print

========================================================
  SOLOLUCION DE PROBLEMAS COMUNES
========================================================

Error: "mvn is not recognized"
  -> Instalar Apache Maven y agregar al PATH del sistema

Error: "java is not recognized"
  -> Instalar JDK y agregar al PATH del sistema

Error en Caso 2 o 4: "User does not exist"
  -> El usuario 'nuevoUsuario1' no existe en la plataforma
  -> Crearlo manualmente en https://www.demoblaze.com/ (sign up)
     con username: nuevoUsuario1 (cualquier password)

Error de conexion: "Connection refused" o timeout
  -> Verificar conectividad a internet
  -> Verificar que https://api.demoblaze.com este disponible
