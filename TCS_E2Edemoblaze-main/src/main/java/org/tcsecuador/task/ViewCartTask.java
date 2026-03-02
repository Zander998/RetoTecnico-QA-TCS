package org.tcsecuador.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.tcsecuador.page.DashboardPage;

public class ViewCartTask implements Task {
    public static ViewCartTask openCart() {
        return new ViewCartTask();
    }

    @Override
    @Step("{0} visualiza el contenido del carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DashboardPage.CART_BUTTON)
        );
    }
}
