package org.tcsecuador.page;

import net.serenitybdd.screenplay.targets.Target;

public class PurchaseOrderModal {

    public static final Target NAME_FIELD = Target.the("NOMBRE")
            .locatedBy("#name");

    public static final Target COUNTRY_FIELD = Target.the("PAIS")
            .locatedBy("#country");

    public static final Target CITY_FIELD = Target.the("CIUDAD")
            .locatedBy("#city");

    public static final Target CARD_FIELD = Target.the("TARJETA DE CREDITO")
            .locatedBy("#card");

    public static final Target MONTH_FIELD = Target.the("MES")
            .locatedBy("#month");

    public static final Target YEAR_FIELD = Target.the("ANIO")
            .locatedBy("#year");

    public static final Target PURCHASE_BUTTON = Target.the("BOTON PURCHASE")
            .locatedBy("//button[contains(text(),'Purchase')]");

    public static final Target PLACE_ORDER_BUTTON = Target.the("BOTON PLACE ORDER")
            .locatedBy("//button[contains(text(),'Place Order')]");


}