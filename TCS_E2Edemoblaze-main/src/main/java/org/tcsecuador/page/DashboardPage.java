package org.tcsecuador.page;

import net.serenitybdd.screenplay.targets.Target;

public class DashboardPage {

    public static final Target CATEGORY_BY_NAME = Target.the("Categoría por nombre")
            .locatedBy("//a[@class='list-group-item' and normalize-space()='{0}']");

    public static final Target PRODUCT_BY_ID =
            Target.the("Producto por ID")
                    .locatedBy("//a[@href='prod.html?idp_={0}']");

    public static final Target CART_BUTTON = Target.the("BOTÓN DEL CARRITO DE COMPRAS")
            .locatedBy("//a[@href='cart.html']");
}
