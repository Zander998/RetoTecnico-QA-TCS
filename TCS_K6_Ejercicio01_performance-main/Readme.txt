README - Prueba de carga con K6

DESCRIPCIÓN GENERAL:
Este proyecto contiene una prueba de carga automatizada al servicio de autenticación de la API pública https://fakestoreapi.com/auth/login, utilizando la herramienta K6. 
El objetivo es evaluar el comportamiento del endpoint bajo condiciones de concurrencia moderada, validando tiempos de respuesta y tasa de errores.

REQUISITOS:
- K6 v1.1.0
- Sistema operativo: Windows, Linux o macOS
- Conexión a internet activa

INSTALACIÓN DE K6 EN WINDOWS:

Opción 1: Usando Scoop (recomendado)
1. Asegúrate de tener Scoop instalado. Si no lo tienes, instálalo ejecutando en PowerShell:
   Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
   irm get.scoop.sh | iex

2. Instala K6 con:
   scoop install k6

Opción 2: Usando Chocolatey
1. Instala Chocolatey si no lo tienes: https://chocolatey.org/install
2. Luego ejecuta:
   choco install k6

Opción 3: Manual
1. Descarga el binario desde: https://github.com/grafana/k6/releases
2. Extrae el archivo y agrega la carpeta al PATH de tu sistema. 

INSTRUCCIONES DE EJECUCIÓN:

1. Clonar el repositorio:
   git clone https://github.com/juniorDeveloper8/login-performance-k6.git

2. Ubicarse en la raíz del proyecto clonado:
   cd login-performance-k6

3. Ejecutar la prueba:
   k6 run scripts/login_test.js

ESTRUCTURA DEL PROYECTO:
- /data/users.csv → contiene las credenciales de prueba utilizadas en el test
- /scripts/login_test.js → script principal que ejecuta la prueba de carga
- readme.txt → instrucciones de instalación y ejecución
- conclusiones.txt → hallazgos técnicos y observaciones del ejercicio

Generación del resumen de resultados
- Si deseas guardar el resumen de la prueba en un archivo de texto para análisis posterior, puedes hacerlo ejecutando:
  k6 run scripts/login_test.js > textSummary.txt

NOTAS IMPORTANTES:
- El único usuario válido confirmado por la API es: mor_2314 / 83r5^_
- El endpoint probado fue: https://fakestoreapi.com/auth/login
- El servidor responde con código HTTP 201 (Created) en lugar de 200, lo cual fue ajustado en el script para validar correctamente las respuestas exitosas.
