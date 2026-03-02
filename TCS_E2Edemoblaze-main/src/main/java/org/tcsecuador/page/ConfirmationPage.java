package org.tcsecuador.page;

import net.serenitybdd.screenplay.targets.Target;

public class ConfirmationPage {

    public static final Target CONFIRMATION_MESSAGE = Target.the("MENSAJE DE CONFIRMACION")
            .locatedBy("//h2[contains(text(),'Thank you for your purchase!')]");

    public static final Target CONFIRM_OK_BUTTON = Target.the("BOTON CONFIRMACION OK")
            .locatedBy("//button[text()='OK']");

}
