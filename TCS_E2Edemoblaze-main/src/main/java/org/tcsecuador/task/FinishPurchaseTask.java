package org.tcsecuador.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.tcsecuador.page.PurchaseOrderModal;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FinishPurchaseTask implements Task {
    public static FinishPurchaseTask confirm() {
        return new FinishPurchaseTask();
    }

    @Override
    @Step("{0} finaliza la compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PurchaseOrderModal.PURCHASE_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(PurchaseOrderModal.PURCHASE_BUTTON)
        );
    }
}
