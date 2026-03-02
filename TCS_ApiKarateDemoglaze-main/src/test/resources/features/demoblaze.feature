
Feature: Prueba API Demoblaze - Signup y Login

  Background:
    * url 'https://api.demoblaze.com'

  @id:CasodePrueba1
  Scenario: Crear nuevo Usuario exitosamente
    # Username unico con timestamp para garantizar que siempre sea un usuario nuevo
    * def timestamp = java.lang.System.currentTimeMillis() + ''
    * def uniqueUser = 'testUser_' + timestamp
    Given path '/signup'
    And request { username: '#(uniqueUser)', password: 'Password123' }
    When method post
    Then status 200
    # En exito la API retorna "" (String vacío JSON). En error retorna objeto con errorMessage
    And match response == '#string'
    And print 'CASO 1 - ENTRADA  -> username:', uniqueUser, '| password: Password123'
    And print 'CASO 1 - SALIDA   -> HTTP Status: 200 | response:', response

  @id:CasodePrueba2
  Scenario: Intentar crear un usuario ya existente
    Given path '/signup'
    And request { username: 'nuevoUsuario1', password: 'password1234' }
    When method post
    Then status 200
    And match response == { errorMessage: 'This user already exist.' }
    And print 'CASO 2 - ENTRADA  -> username: nuevoUsuario1 | password: password1234'
    And print 'CASO 2 - SALIDA   -> HTTP Status: 200 | response:', response

  @id:CasodePrueba3
  Scenario: Login con usuario y contrasena correctos
    # Se crea un usuario nuevo con credenciales conocidas para garantizar login exitoso
    * def timestamp = java.lang.System.currentTimeMillis() + ''
    * def testUser = 'loginTest_' + timestamp
    * def testPass = 'TestPass123'
    Given path '/signup'
    And request { username: '#(testUser)', password: '#(testPass)' }
    When method post
    Then status 200
    # Ahora se autentica con las credenciales recien registradas
    Given path '/login'
    And request { username: '#(testUser)', password: '#(testPass)' }
    When method post
    Then status 200
    # En login exitoso la API retorna un token de autenticacion (String no vacio)
    And match response == '#string'
    And match response != ''
    And print 'CASO 3 - ENTRADA  -> username:', testUser, '| password:', testPass
    And print 'CASO 3 - SALIDA   -> HTTP Status: 200 | token recibido:', response

  @id:CasodePrueba4
  Scenario: Login con usuario y contrasena incorrectos
    # Se usa un usuario que existe (nuevoUsuario1) pero con contrasena incorrecta
    Given path '/login'
    And request { username: 'nuevoUsuario1', password: 'contrasenaIncorrecta999' }
    When method post
    Then status 200
    And match response == { errorMessage: 'Wrong password.' }
    And print 'CASO 4 - ENTRADA  -> username: nuevoUsuario1 | password: contrasenaIncorrecta999'
    And print 'CASO 4 - SALIDA   -> HTTP Status: 200 | response:', response
