@ProcesodeCompra
Feature: Flujo de compra en DemoBlaze

  Scenario Outline: Compra exitosa de productos en DemoBlaze
    Given el usuario accede al sitio DemoBlaze
    When agrega los siguientes productos al carrito
      | categoria | producto              |
      | Phones    | Samsung galaxy s6     |
      | Laptops   | MacBook air |

    And visualiza el contenido del carrito
    Then completa el formulario de compra con:
      | name      | country   | city   | card     | month | year |
      | <name>    | <country> | <city> | <card>   | <month>|<year> |

    And finaliza la compra
    Then deberia ver el mensaje de confirmacion "Thank you for your purchase!"

    Examples:
      | name         | country | city  | card      | month | year |
      | Alexander Narvaez    | Ecuador | Quito | 44443333  | 12    | 2025 |
