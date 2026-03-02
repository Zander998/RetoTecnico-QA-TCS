package org.tcsecuador.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.tcsecuador.page.CartPage;
import org.tcsecuador.page.PurchaseOrderModal;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillOrderFormTask implements Task {

    private final String name;
    private final String country;
    private final String city;
    private final String card;
    private final String month;
    private final String year;

    public FillOrderFormTask(String name, String country, String city,
                             String card, String month, String year) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.card = card;
        this.month = month;
        this.year = year;
    }

    public static FillOrderFormTask withData(String name, String country, String city,
                                       String card, String month, String year) {
        return new FillOrderFormTask(name, country, city, card, month, year);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(CartPage.PLACE_ORDER_BUTTON, isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(CartPage.PLACE_ORDER_BUTTON),

                WaitUntil.the(PurchaseOrderModal.NAME_FIELD, isVisible())
                        .forNoMoreThan(10).seconds(),

                Enter.theValue(name).into(PurchaseOrderModal.NAME_FIELD),
                Enter.theValue(country).into(PurchaseOrderModal.COUNTRY_FIELD),
                Enter.theValue(city).into(PurchaseOrderModal.CITY_FIELD),
                Enter.theValue(card).into(PurchaseOrderModal.CARD_FIELD),
                Enter.theValue(month).into(PurchaseOrderModal.MONTH_FIELD),
                Enter.theValue(year).into(PurchaseOrderModal.YEAR_FIELD),

                Click.on(PurchaseOrderModal.PURCHASE_BUTTON)
        );
    }
}
