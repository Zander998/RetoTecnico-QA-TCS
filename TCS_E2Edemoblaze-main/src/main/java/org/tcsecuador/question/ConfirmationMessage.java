package org.tcsecuador.question;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.tcsecuador.page.ConfirmationPage;

public class ConfirmationMessage {

    public static Question<String> text() {
        return Text.of(ConfirmationPage.CONFIRMATION_MESSAGE).asString();
    }

}
