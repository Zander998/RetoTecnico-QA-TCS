package org.tcsecuador.page;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {
    public static final Target PLACE_ORDER_BUTTON = Target.the("BOTON PLACE ORDER")
            .locatedBy("//button[contains(text(),'Place Order')]");
}
