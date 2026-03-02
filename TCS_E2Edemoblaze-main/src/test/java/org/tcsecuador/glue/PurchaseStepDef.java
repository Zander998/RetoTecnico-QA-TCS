package org.tcsecuador.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.tcsecuador.task.*;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class PurchaseStepDef {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el usuario accede al sitio DemoBlaze")
    public void elUsuarioAccedeAlSitioDemoBlaze() {
        theActorCalled("Alexander Narvaez").attemptsTo(
                NavigateTo.DemoBlazePage()
        );
    }

    @When("agrega los siguientes productos al carrito")
    public void agregaProductosAlCarrito(List<Map<String, String>> productos) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddListProductsToCartTask.fromList(productos)
        );
    }

    @And("visualiza el contenido del carrito")
    public void visualizaContenidoDelCarrito() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ViewCartTask.openCart()
        );
    }

    @Then("completa el formulario de compra con:")
    public void completeFormWith(DataTable table) {
        Map<String, String> data = table.asMaps(String.class, String.class).get(0);
        OnStage.theActorInTheSpotlight().attemptsTo(
                FillOrderFormTask.withData(
                        data.get("name"),
                        data.get("country"),
                        data.get("city"),
                        data.get("card"),
                        data.get("month"),
                        data.get("year")
                )
        );
    }

    @And("finaliza la compra")
    public void finalizaLaCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                FinishPurchaseTask.confirm()
        );
    }

    @Then("deberia ver el mensaje de confirmacion {string}")
    public void validaMensajeDeConfirmacion(String expectedMessage) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ValidateConfirmationMessageTask.isVisible()
        );
    }
}
