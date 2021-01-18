package google;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class GoogleTest {

    @Test
    void shouldSerach() {
        System.setProperty("webdriver.chrome.driver", "/Users/user1234/Downloads/chromedriver");
        open("https://google.com/ncr");

        // вводим в строку браузера Selenide
        element(byName("q")).setValue("Selenide").pressEnter();

        // добавляем в коллекцию все результаты поиска
        // проверяем, что их 6 и более
        // на Первом нажимаем ссылку
        elements(byClassName("yuRUbf"))
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(6))
                .first().shouldHave(Condition.text("Selenide: concise UI tests in Java"))
                .find(byTagName("cite")).click();  // находим ссылку в Первом результате поиска и кликаем

        // ждем пока не откроется страница с данным Тайтлом
        Wait().until(titleIs("Selenide: concise UI tests in Java"));
    }
}
