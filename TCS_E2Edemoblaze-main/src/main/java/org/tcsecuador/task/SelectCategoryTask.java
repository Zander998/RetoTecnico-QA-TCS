package org.tcsecuador.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.tcsecuador.page.DashboardPage;

import static net.serenitybdd.screenplay.waits.WaitUntil.the;

public class SelectCategoryTask implements Task {
    private final String categoryName;

    private SelectCategoryTask(String categoryName) {
        this.categoryName = categoryName;
    }

    public static SelectCategoryTask selectCategory(String categoryName) {
        return new SelectCategoryTask(categoryName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DashboardPage.CATEGORY_BY_NAME.of(categoryName))
        );
    }
}
