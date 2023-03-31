package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

public class ElementsAction {

    private static final String osName = System.getProperty("os.name");

    public static void clearInput(SelenideElement element) {
        if (osName.toLowerCase().contains("mac")) {
            element.sendKeys(Keys.COMMAND + "A");
            element.sendKeys(Keys.BACK_SPACE);
        } else {
            element.sendKeys(Keys.CONTROL + "A");
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
}
