package org.tcsecuador.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.tcsecuador.page.DashboardPage;
import org.tcsecuador.page.ProductCatalog;
import org.tcsecuador.page.ProductDetailPage;

public class AddProductToCartTask implements Task {

    private final String productName;

    public AddProductToCartTask(String productName) {
        this.productName = productName;
    }

    public static AddProductToCartTask withName(String productName) {
        return new AddProductToCartTask(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String productId = ProductCatalog.idFor(productName);

        actor.attemptsTo(
                Click.on(DashboardPage.PRODUCT_BY_ID.of(productId)),
                Click.on(ProductDetailPage.ADD_TO_CART_BUTTON),
                AcceptAlertTask.onThePage(),
                Click.on(ProductDetailPage.HOME_LINK)
        );
    }
}

