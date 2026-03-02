package org.tcsecuador.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.tcsecuador.page.ConfirmationPage;

public class ValidateConfirmationMessageTask implements Task {

    public static ValidateConfirmationMessageTask isVisible() {
        return new ValidateConfirmationMessageTask();
    }

    @Override
    @Step("{0} valida el mensaje final de compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ConfirmationPage.CONFIRMATION_MESSAGE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(ConfirmationPage.CONFIRM_OK_BUTTON)
        );
    }
}
