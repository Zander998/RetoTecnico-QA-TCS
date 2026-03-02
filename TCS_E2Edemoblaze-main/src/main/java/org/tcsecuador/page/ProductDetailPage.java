package org.tcsecuador.page;

import net.serenitybdd.screenplay.targets.Target;

public class ProductDetailPage {


    public static final Target ADD_TO_CART_BUTTON = Target.the("Botón Add to cart")
            .locatedBy("a[onclick*='addToCart']");

    public static final Target HOME_LINK = Target.the("Botón Home")
            .locatedBy("//a[@href='index.html']");

}
