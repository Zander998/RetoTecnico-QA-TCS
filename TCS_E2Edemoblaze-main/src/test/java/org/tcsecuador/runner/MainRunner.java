package org.tcsecuador.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "org.tcsecuador.glue",
        tags = "@ProcesodeCompra"
)
public class MainRunner {
}
