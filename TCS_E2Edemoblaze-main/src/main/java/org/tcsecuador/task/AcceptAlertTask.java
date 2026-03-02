package org.tcsecuador.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AcceptAlertTask implements Interaction {

    public static AcceptAlertTask onThePage() {
        return instrumented(AcceptAlertTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();

        // Espera hasta 5 segundos a que aparezca la alerta
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Aceptar alerta
        alert.accept();
    }
}
